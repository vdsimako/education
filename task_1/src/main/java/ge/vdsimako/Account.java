package ge.vdsimako;


import java.math.BigDecimal;

public class Account {

    private final Integer id;
    private BigDecimal balance;

    public Account(Integer id,
                   BigDecimal initialBalance) {
        this.id = id;
        this.balance = initialBalance;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getId() {
        return this.id;
    }

}