package io.github.persdsr.fitnesscalculator.dto.request;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DishRequest {

    @NotBlank(message = "The name is required")
    @Size(min = 2, max = 70, message = "The name must contain from 2 to 70 characters")
    private String name;

    @NotNull(message = "The calories is required")
    @Positive(message = "The calories can't be less then 0")
    @Max(value = 4000, message = "The calories can't be more than 4000")
    private float calories;

    @NotNull(message = "The proteins is required")
    @Positive(message = "The proteins can't be less then 0")
    @Max(value = 200, message = "The proteins can't be more than 200")
    private float proteins;

    @NotNull(message = "The fats is required")
    @Positive(message = "The fats can't be less then 0")
    @Min(value = 0, message = "The fats can't be less then 0")
    @Max(value = 200, message = "The fats can't be more than 200")
    private float fats;

    @NotNull(message = "carbohydrates is required")
    @Positive(message = "The carbohydrates can't be less then 0")
    @Max(value = 300, message = "The carbohydrates can't be more than 300")
    private float carbohydrates;

}
