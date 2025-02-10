package org.example;

public record Animal(String ID, String name, Species species, int age, Owner owner) {
    public Animal withUpdatedID(String ID) {
        return new Animal(ID, name, species, age, owner);
    }
    public Animal withUpdatedName(String name) {
        return new Animal(ID, name, species, age, owner);
    }
    public Animal withUpdatedSpecies(Species species) {
        return new Animal(ID, name, species, age, owner);
    }
    public Animal withUpdatedAge(int age) {
        return new Animal(ID, name, species, age, owner);
    }
    public Animal withUpdatedOwner(Owner owner) {
        return new Animal(ID, name, species, age, owner);
    }
}
