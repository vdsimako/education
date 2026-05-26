package ge.vdsimako;

import static ge.vdsimako.MoneyTransferService.ENOUGH_AMOUNT_ERROR;
import static ge.vdsimako.MoneyTransferService.INVALID_TRANSFER_OPERATION_ERROR;
import static ge.vdsimako.MoneyTransferService.TRANSFER_AMOUNT_IS_NEGATIVE_OR_ZERO_ERROR;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MoneyTransferServiceTest {
    private final MoneyTransferService service = new MoneyTransferService();

    private static final BigDecimal TEST_AMOUNT = new BigDecimal(100);
    private static final BigDecimal TEST_AMOUNT_BIGGER = new BigDecimal(200);
    private static final BigDecimal TEST_AMOUNT_ZERO = BigDecimal.ZERO;
    private static final BigDecimal TEST_AMOUNT_NEGATIVE = new BigDecimal(-1);

    public static Stream<Arguments> invalidData() {
        return Stream.of(
                Arguments.of(
                        new Account(1, TEST_AMOUNT),
                        new Account(1, TEST_AMOUNT),
                        TEST_AMOUNT,
                        new RuntimeException(INVALID_TRANSFER_OPERATION_ERROR)
                ),
                Arguments.of(
                        new Account(1, TEST_AMOUNT),
                        new Account(2, TEST_AMOUNT),
                        TEST_AMOUNT_BIGGER,
                        new RuntimeException(ENOUGH_AMOUNT_ERROR)
                ),
                Arguments.of(
                        new Account(1, TEST_AMOUNT),
                        new Account(2, TEST_AMOUNT),
                        TEST_AMOUNT_ZERO,
                        new RuntimeException(TRANSFER_AMOUNT_IS_NEGATIVE_OR_ZERO_ERROR)
                ),
                Arguments.of(
                        new Account(1, TEST_AMOUNT),
                        new Account(2, TEST_AMOUNT),
                        TEST_AMOUNT_NEGATIVE,
                        new RuntimeException(TRANSFER_AMOUNT_IS_NEGATIVE_OR_ZERO_ERROR)
                )
        );
    }

    public static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of(
                        new Account(1, TEST_AMOUNT),
                        new Account(2, TEST_AMOUNT),
                        TEST_AMOUNT
                )
        );
    }

    @ParameterizedTest
    @MethodSource("invalidData")
    public void testInvalidTransfer(Account from,
                                    Account to,
                                    BigDecimal amount,
                                    RuntimeException expected) {
        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> service.transferMoney(from, to, amount));
        assertEquals(expected.getMessage(), runtimeException.getMessage());
    }

    @ParameterizedTest
    @MethodSource("data")
    public void testTransfer(Account from,
                             Account to,
                             BigDecimal amount) {
        service.transferMoney(from, to, amount);

        assertEquals(BigDecimal.ZERO, from.getBalance());
        assertEquals(new BigDecimal(200), to.getBalance());
    }

    @Test
    @Timeout(5)
    void shouldNotDeadlockWhenTransfersGoInOppositeDirections() throws Exception {
        Account account1 = new Account(1, BigDecimal.valueOf(1000));
        Account account2 = new Account(2, BigDecimal.valueOf(1000));

        try (ExecutorService executor = Executors.newFixedThreadPool(2)) {
            Future<?> first = executor.submit(() -> {
                for (int i = 0; i < 500; i++) {
                    service.transferMoney(account1, account2, BigDecimal.ONE);
                }
            });

            Future<?> second = executor.submit(() -> {
                for (int i = 0; i < 500; i++) {
                    service.transferMoney(account2, account1, BigDecimal.ONE);
                }
            });

            first.get(3, TimeUnit.SECONDS);
            second.get(3, TimeUnit.SECONDS);

            assertEquals(BigDecimal.valueOf(1000), account1.getBalance());
            assertEquals(BigDecimal.valueOf(1000), account2.getBalance());
        }
    }

    @Test
    @Timeout(5)
    void shouldNotOverdraftSourceAccountUnderConcurrency() throws Exception {
        Account from = new Account(1, BigDecimal.valueOf(100));
        Account to = new Account(2, BigDecimal.ZERO);

        int taskCount = 1000;

        CountDownLatch start = new CountDownLatch(1);

        AtomicInteger successfulTransfers = new AtomicInteger();
        AtomicInteger failedTransfers = new AtomicInteger();

        List<Future<?>> futures = new ArrayList<>();

        try (ExecutorService executor = Executors.newFixedThreadPool(16)) {
            for (int i = 0; i < taskCount; i++) {
                futures.add(executor.submit(() -> {
                    try {
                        start.await();
                        service.transferMoney(from, to, BigDecimal.ONE);
                        successfulTransfers.incrementAndGet();
                    } catch (RuntimeException e) {
                        failedTransfers.incrementAndGet();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }));
            }

            start.countDown();

            for (Future<?> future : futures) {
                future.get(3, TimeUnit.SECONDS);
            }

            assertEquals(100, successfulTransfers.get());
            assertEquals(900, failedTransfers.get());

            assertEquals(BigDecimal.ZERO, from.getBalance());
            assertEquals(BigDecimal.valueOf(100), to.getBalance());
        }
    }

}
