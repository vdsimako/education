import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class SubscriptionBillingService {
    // Plans: BASIC = 9.99, PREMIUM = 19.99
    private final ConcurrentHashMap<String, SubscriptionDto> userSubscriptions;
    private final ConcurrentHashMap<String, ReentrantLock> userLocks = new ConcurrentHashMap<>();

    private static final String USER_ALREADY_HAVE_SUBSCRIPTION = "User already have subscription";
    private static final String USER_SUBSCRIPTION_NOT_FOUND = "User subscription not found";

    public SubscriptionBillingService(ConcurrentHashMap<String, SubscriptionDto> userSubscriptions) {
        this.userSubscriptions = userSubscriptions;
    }

    public void startSubscription(String userId,
                                  String plan,
                                  LocalDate startDate) {
        ReentrantLock lock = userLocks.computeIfAbsent(userId, k -> new ReentrantLock());
        lock.lock();
        try {
            if (userSubscriptions.containsKey(userId)) {
                throw new RuntimeException(USER_ALREADY_HAVE_SUBSCRIPTION);
            }
            LocalDate nextBillingDate = startDate.plus(1, ChronoUnit.MONTHS);
            SubscriptionDto subscriptionDto = new SubscriptionDto(userId, plan, startDate, nextBillingDate);
            userSubscriptions.put(userId, subscriptionDto);
        } finally {
            lock.unlock();
        }
    }

    public SubscriptionDto getSubscription(String userId) {
        if (!userSubscriptions.containsKey(userId)) {
            throw new RuntimeException(USER_SUBSCRIPTION_NOT_FOUND);
        }

        SubscriptionDto subscriptionDto = userSubscriptions.get(userId);

        return subscriptionDto;
    }
}
