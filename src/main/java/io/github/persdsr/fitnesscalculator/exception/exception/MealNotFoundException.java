package io.github.persdsr.fitnesscalculator.exception.exception;

public class MealNotFoundException extends RuntimeException {
    public MealNotFoundException(Long mealId) {
        super("Meal with id " + mealId + " not found");
    }
}
