package org.example;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {
    private String number;
    private BigDecimal balance;
    private Client client;

    public Account(String number, BigDecimal balance, Client client) {
        this.number = number;
        this.balance = balance;
        this.client = client;
    }

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if(balance.compareTo(amount) > 0) {
        balance = balance.subtract(amount);
        }
    }

    public String getNumber() {
        return number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Client getClient() {
        return client;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(number, account.number) && Objects.equals(balance, account.balance) && Objects.equals(client, account.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, balance, client);
    }

    @Override
    public String toString() {
        return "Account{" +
                "number='" + number + '\'' +
                ", balance=" + balance +
                ", client=" + client +
                '}';
    }
}
