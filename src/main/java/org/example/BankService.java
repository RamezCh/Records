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

    private Account findById(String ID) {
        for (Account account : accounts) {
            if (account.getNumber().equals(ID)) {
                return account;
            }
        }
        return null;
    }

    public void transferMoney(BigDecimal amount, String fromAccID, String toAccID) {
        Account fromAcc = findById(fromAccID);
        Account toAcc = findById(toAccID);
        if (fromAcc == null || toAcc == null) {
            System.out.println("Account ID invalid, please check the account number carefully!");
            return;
        }
        if(fromAcc.getBalance().compareTo(amount) < 0) {
            System.out.println("Insufficient Balance! Add more funds or reduce transfer amount!");
            return;
        }
        fromAcc.withdraw(amount);
        toAcc.deposit(amount);
        System.out.println("Transfer successful! " + amount + " transferred from " + fromAccID + " to " + toAccID);
    }
}
