package org.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class BankService {
    private Set<Account> accounts = new HashSet<>();
    private static int countAccs = 0;
    private static LocalDate lastOpenedDate = LocalDate.now();

    public BankService() {
    }

    public String openAccount(Client client) {
        LocalDate currentDate = LocalDate.now();
        int day = currentDate.getDayOfMonth();
        int month = currentDate.getMonthValue();

        if (!currentDate.equals(lastOpenedDate)) {
            countAccs = 0;
            lastOpenedDate = currentDate;
        }

        countAccs++;
        String accountNumber = day + "" + month + "" + countAccs;
        Account account = new Account(accountNumber, new BigDecimal(0), client);
        accounts.add(account);
        return accountNumber;
    }
}
