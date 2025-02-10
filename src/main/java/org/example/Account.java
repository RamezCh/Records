package org.example;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {
    protected String number;
    protected BigDecimal balance;
    protected Client client;

    public Account(String number, BigDecimal balance, Client client) {
        this.number = number;
        this.balance = balance;
        this.client = client;
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
