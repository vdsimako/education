package ge.vdsimako;

import java.math.BigDecimal;

public class MoneyTransferService {

    public static final String INVALID_TRANSFER_OPERATION_ERROR = "Invalid transfer operation";
    public static final String ENOUGH_AMOUNT_ERROR = "No enough amount on account";
    public static final String TRANSFER_AMOUNT_IS_NEGATIVE_OR_ZERO_ERROR = "Invalid amount for transfer";

    public void transferMoney(Account from,
                              Account to,
                              BigDecimal amount) {

        Account firstLock = getFirstLock(from, to);
        Account secondLock = getSecondLock(from, to);

        synchronized (firstLock) {
            synchronized (secondLock) {
                if (amount.compareTo(BigDecimal.ZERO) < 1) {
                    throw new RuntimeException(TRANSFER_AMOUNT_IS_NEGATIVE_OR_ZERO_ERROR);
                }

                if (from.getId().equals(to.getId())) {
                    throw new RuntimeException(INVALID_TRANSFER_OPERATION_ERROR);
                }

                if (from.getBalance().compareTo(amount) < 0) {
                    throw new RuntimeException(ENOUGH_AMOUNT_ERROR);
                }

                BigDecimal newBalance = from.getBalance().subtract(amount);
                from.setBalance(newBalance);

                newBalance = to.getBalance().add(amount);
                to.setBalance(newBalance);
            }
        }
    }

    private static Account getFirstLock(Account from, Account to) {
        return from.getId().compareTo(to.getId()) < 0 ? from : to;
    }

    private static Account getSecondLock(Account from, Account to) {
        return from.getId().compareTo(to.getId()) < 0 ? to : from;
    }
}
