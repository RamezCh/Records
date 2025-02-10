package org.example;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Owner lionOwner = new Owner("Lowner", 35, "lion town, 10315 Lion country");
        System.out.println(lionOwner);
        Species pantheraLeo = new Species("Panthera Leo", "Meat", 15000);
        System.out.println(pantheraLeo);
        Animal lion = new Animal("Lion", "Lion", pantheraLeo, 1, lionOwner);
        System.out.println(lion);
        System.out.println(lion.name());
        System.out.println(lion.species());
        Animal lion2 = new Animal("Lion", "Lion", pantheraLeo, 1, lionOwner);
        Animal lion3 = new Animal("Lion", "Lion", pantheraLeo, 2, lionOwner);
        System.out.println(lion.equals(lion2));
        System.out.println(lion.equals(lion3));
        System.out.println(lion == lion2);
        Animal[] animals = {lion, lion2, lion3};
        Zoo zoo = new Zoo(animals);
        System.out.println(zoo.calculateTotalFoodRequirement());

        BankService bankService = new BankService();

        AccountHolder alice = new AccountHolder("Alice", "Smith", 101);
        AccountHolder bob = new AccountHolder("Bob", "Johnson", 102);

        Set<AccountHolder> jointHolders = new HashSet<>();
        jointHolders.add(alice);
        jointHolders.add(bob);

        String jointAccountNumber = bankService.openAccount(jointHolders);
        Account jointAccount = bankService.findById(jointAccountNumber);
        if (jointAccount != null) {
            jointAccount.deposit(new BigDecimal("0.03"));
        }

        System.out.println("Joint Account before split: " + jointAccount);

        List<String> newAccounts = bankService.split(jointAccountNumber);
        System.out.println("New individual accounts: " + newAccounts);

        for (String accNumber : newAccounts) {
            System.out.println("New Account " + accNumber + ": " + bankService.findById(accNumber));
        }
    }
}
