package org.example;

import java.math.BigDecimal;
import java.util.Set;
import java.util.Objects;

public class Account {
    private String number;
    private BigDecimal balance;
    private Set<AccountHolder> accountHolders;

    public Account(String number, BigDecimal balance, Set<AccountHolder> accountHolders) {
        this.number = number;
        this.balance = balance;
        this.accountHolders = accountHolders;
    }

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if (balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount);
        }
    }

    public String getNumber() {
        return number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Set<AccountHolder> getAccountHolders() {
        return accountHolders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(number, account.number) && Objects.equals(balance, account.balance) && Objects.equals(accountHolders, account.accountHolders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, balance, accountHolders);
    }

    @Override
    public String toString() {
        return "Account{" +
                "number='" + number + '\'' +
                ", balance=" + balance +
                ", accountHolders=" + accountHolders +
                '}';
    }
}