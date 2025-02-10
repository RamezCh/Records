package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

public class BankService {
    private Set<Account> accounts = new HashSet<>();
    private static int countAccs = 0;
    private static LocalDate lastOpenedDate = LocalDate.now();

    public BankService() {
    }

    public String openAccount(Set<AccountHolder> holders) {
        LocalDate currentDate = LocalDate.now();
        int day = currentDate.getDayOfMonth();
        int month = currentDate.getMonthValue();

        if (!currentDate.equals(lastOpenedDate)) {
            countAccs = 0;
            lastOpenedDate = currentDate;
        }

        countAccs++;
        String accountNumber = day + "" + month + "" + countAccs;
        Account account = new Account(accountNumber, BigDecimal.ZERO, holders);
        accounts.add(account);
        return accountNumber;
    }

    public Account findById(String ID) {
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
        if (fromAcc.getBalance().compareTo(amount) < 0) {
            System.out.println("Insufficient Balance! Add more funds or reduce transfer amount!");
            return;
        }
        fromAcc.withdraw(amount);
        toAcc.deposit(amount);
        System.out.println("Transfer successful! " + amount + " transferred from " + fromAccID + " to " + toAccID);
    }

    public List<String> split(String accountNumber) {
        Account jointAccount = findById(accountNumber);
        if (jointAccount == null || jointAccount.getAccountHolders().size() < 2) {
            System.out.println("Account does not exist or is not a joint account.");
            return Collections.emptyList();
        }

        Set<AccountHolder> holders = jointAccount.getAccountHolders();
        int numHolders = holders.size();
        BigDecimal totalBalance = jointAccount.getBalance();
        BigDecimal equalShare = totalBalance.divide(BigDecimal.valueOf(numHolders), 2, RoundingMode.FLOOR);
        BigDecimal remainder = totalBalance.subtract(equalShare.multiply(new BigDecimal(numHolders)));

        List<String> newAccountNumbers = new ArrayList<>();
        Iterator<AccountHolder> iterator = holders.iterator();

        for (int i = 0; i < numHolders; i++) {
            AccountHolder holder = iterator.next();
            BigDecimal allocatedAmount = equalShare;

            if (remainder.compareTo(BigDecimal.ZERO) > 0) {
                allocatedAmount = allocatedAmount.add(new BigDecimal("0.01"));
                remainder = remainder.subtract(new BigDecimal("0.01"));
            }

            Set<AccountHolder> singleHolderSet = new HashSet<>();
            singleHolderSet.add(holder);
            String newAccNumber = openAccount(singleHolderSet);
            Account newAccount = findById(newAccNumber);
            if (newAccount != null) {
                newAccount.deposit(allocatedAmount);
                newAccountNumbers.add(newAccNumber);
            }
        }

        accounts.remove(jointAccount);
        return newAccountNumbers;
    }
}