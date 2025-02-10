package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BankServiceTest {

    private BankService bankService;
    private Set<AccountHolder> holders;

    @BeforeEach
    public void setUp() {
        bankService = new BankService();
        holders = Set.of(new AccountHolder("Holder1", "holder1", 123),
                new AccountHolder("Holder2", "holder2", 124));
    }

    @Test
    public void testTransferMoney_Success() {
        // Arrange
        String fromAccountId = bankService.openAccount(holders);
        String toAccountId = bankService.openAccount(Set.of(new AccountHolder("Recipient", "recipient", 125)));

        Account fromAccount = bankService.findById(fromAccountId);
        Account toAccount = bankService.findById(toAccountId);

        fromAccount.deposit(new BigDecimal("100.00"));

        // Act
        bankService.transferMoney(new BigDecimal("50.00"), fromAccountId, toAccountId);

        // Assert
        assertEquals(new BigDecimal("50.00"), fromAccount.getBalance());
        assertEquals(new BigDecimal("50.00"), toAccount.getBalance());
    }

    @Test
    public void testTransferMoney_InsufficientBalance() {
        // Arrange
        String fromAccountId = bankService.openAccount(holders);
        String toAccountId = bankService.openAccount(Set.of(new AccountHolder("Recipient", "recipient", 125)));

        Account fromAccount = bankService.findById(fromAccountId);
        Account toAccount = bankService.findById(toAccountId);

        fromAccount.deposit(new BigDecimal("30.00"));

        // Act
        bankService.transferMoney(new BigDecimal("50.00"), fromAccountId, toAccountId);

        // Assert
        assertEquals(new BigDecimal("30.00"), fromAccount.getBalance());  // Balance shouldn't have changed
        assertEquals(new BigDecimal("0"), toAccount.getBalance());     // Balance shouldn't have changed
    }

    @Test
    public void testTransferMoney_InvalidAccount() {
        // Act & Assert
        bankService.transferMoney(new BigDecimal("50.00"), "invalidAccountId1", "invalidAccountId2");
    }

    @Test
    public void testSplit_Success() {
        // Arrange
        String jointAccountId = bankService.openAccount(holders);
        Account jointAccount = bankService.findById(jointAccountId);
        jointAccount.deposit(new BigDecimal("100.00"));

        // Act
        List<String> newAccountNumbers = bankService.split(jointAccountId);

        // Assert
        assertEquals(2, newAccountNumbers.size());
        for (String accountId : newAccountNumbers) {
            Account newAccount = bankService.findById(accountId);
            assertNotNull(newAccount);
            assertEquals(new BigDecimal("50.00"), newAccount.getBalance());
        }
    }

    @Test
    public void testSplit_InsufficientHolders() {
        // Arrange
        String singleAccountId = bankService.openAccount(Set.of(new AccountHolder("Single", "single", 126)));

        // Act
        List<String> newAccountNumbers = bankService.split(singleAccountId);

        // Assert
        assertTrue(newAccountNumbers.isEmpty());
    }

    @Test
    public void testSplit_NonExistentAccount() {
        // Act
        List<String> newAccountNumbers = bankService.split("nonExistentAccountId");

        // Assert
        assertTrue(newAccountNumbers.isEmpty());
    }
}
