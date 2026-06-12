import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class SubscriptionBillingServiceTest {
    private final ConcurrentHashMap<String, SubscriptionDto> userSubscriptionsMock = new ConcurrentHashMap<>();
    private final SubscriptionBillingService subscriptionBillingService = new SubscriptionBillingService(userSubscriptionsMock);

    @Test
    void startSubscription() {
        String userId = "1";
        String plan = "BASIC";
        LocalDate startDate = LocalDate.now().plus(1, ChronoUnit.DAYS);
        LocalDate nextBillingDate = startDate.plus(1, ChronoUnit.MONTHS);

        SubscriptionDto expectedSubscriptionDto = new SubscriptionDto(userId, plan, startDate, nextBillingDate);

        subscriptionBillingService.startSubscription(userId, plan, startDate);

        SubscriptionDto actualSubscriptionDto = userSubscriptionsMock.get(userId);

        assertEquals(expectedSubscriptionDto, actualSubscriptionDto);
    }

    @Test
    void startSubscription_userAlreadyCreatedSubscription() {
        String userId = "1";
        String plan = "BASIC";
        LocalDate startDate = LocalDate.now().plus(1, ChronoUnit.DAYS);
        LocalDate nextBillingDate = startDate.plus(1, ChronoUnit.MONTHS);

        SubscriptionDto expectedSubscriptionDto = new SubscriptionDto(userId, plan, startDate, nextBillingDate);

        userSubscriptionsMock.put(userId, expectedSubscriptionDto);

        assertThrows(RuntimeException.class, () -> subscriptionBillingService.startSubscription(userId, plan, startDate));
    }

    @Test
    void getSubscription_returnSubscription() {
        String userId = "2";
        String plan = "PREMIUM";
        LocalDate startDate = LocalDate.now().plus(1, ChronoUnit.DAYS);
        LocalDate nextBillingDate = startDate.plus(1, ChronoUnit.MONTHS);

        SubscriptionDto expectedSubscription = new SubscriptionDto(userId, plan, startDate, nextBillingDate);

        userSubscriptionsMock.put(userId, expectedSubscription);

        SubscriptionDto actualSubscription = subscriptionBillingService.getSubscription(userId);

        assertEquals(expectedSubscription, actualSubscription);
    }

    @Test
    void getSubscription_returnNotFound() {
        String userIdNotFound = "1";

        assertThrows(RuntimeException.class, () -> subscriptionBillingService.getSubscription(userIdNotFound));
    }

    @Test
    void startSubscription_throwsCorrectMessage_whenUserAlreadyHasSubscription() {
        String userId = "1";
        String plan = "BASIC";
        LocalDate startDate = LocalDate.now().plus(1, ChronoUnit.DAYS);
        LocalDate nextBillingDate = startDate.plus(1, ChronoUnit.MONTHS);

        userSubscriptionsMock.put(userId, new SubscriptionDto(userId, plan, startDate, nextBillingDate));

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> subscriptionBillingService.startSubscription(userId, plan, startDate));

        assertEquals("User already have subscription", ex.getMessage());
    }

    @Test
    void getSubscription_throwsCorrectMessage_whenUserNotFound() {
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> subscriptionBillingService.getSubscription("nonexistent"));

        assertEquals("User subscription not found", ex.getMessage());
    }

    @Test
    void startSubscription_withTodayStartDate_setsNextBillingDateOneMonthLater() {
        String userId = "3";
        LocalDate today = LocalDate.now();

        subscriptionBillingService.startSubscription(userId, "BASIC", today);

        SubscriptionDto result = userSubscriptionsMock.get(userId);
        assertEquals(today.plus(1, ChronoUnit.MONTHS), result.getNextBillingDate());
    }

    @Test
    void startSubscription_concurrent_onlyOneSucceeds() throws InterruptedException {
        String userId = "concurrent-user";
        String plan = "PREMIUM";
        LocalDate startDate = LocalDate.now();
        int threadCount = 10;

        CountDownLatch startLatch = new CountDownLatch(1);
        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger errorCount = new AtomicInteger(0);

        try (ExecutorService executor = Executors.newFixedThreadPool(threadCount)) {
            for (int i = 0; i < threadCount; i++) {
                executor.submit(() -> {
                    try {
                        startLatch.await();
                        subscriptionBillingService.startSubscription(userId, plan, startDate);
                        successCount.incrementAndGet();
                    } catch (RuntimeException e) {
                        errorCount.incrementAndGet();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
            startLatch.countDown();
        }

        assertEquals(1, successCount.get(), "exactly one thread should create the subscription");
        assertEquals(threadCount - 1, errorCount.get(), "all other threads should fail");
        assertEquals(1, userSubscriptionsMock.size());
    }
}