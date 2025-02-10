package org.example;

public class Main {
    public static void main(String[] args) {
        Zoo lion = new Zoo("Lion", "Lion", " Panthera leo", 1);
        System.out.println(lion);
        System.out.println(lion.name());
        System.out.println(lion.species());
    }
}