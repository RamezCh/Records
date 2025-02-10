package org.example;

public record Zoo(Animal[] animals) {
    public int calculateTotalFoodRequirement() {
        int totalFoodRequirement = 0;
        for(Animal animal : animals) {
            totalFoodRequirement += animal.species().requirementInGramsPerDay();
        }
        return totalFoodRequirement;
    }
}
