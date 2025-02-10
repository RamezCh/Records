package org.example;

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
    }
}