package io.github.persdsr.fitnesscalculator.exception.exception;

public class DishNotFoundException extends RuntimeException {
    public DishNotFoundException(Long dishId) {
        super("Dish with id " + dishId + " not found");
    }
}
