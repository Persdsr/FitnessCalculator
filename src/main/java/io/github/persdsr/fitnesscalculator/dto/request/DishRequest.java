package io.github.persdsr.fitnesscalculator.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DishRequest {

    @NotBlank(message = "The name is required")
    @Size(min = 2, max = 70, message = "The name must contain from 2 to 70 characters")
    private String name;

    @NotBlank(message = "The calories is required")
    @Min(value = 0, message = "The calories can't be less then 0")
    @Max(value = 4000, message = "The calories can't be more than 4000")
    private float calories;

    @NotBlank(message = "The proteins is required")
    @Min(value = 0, message = "The proteins can't be less then 0")
    @Max(value = 200, message = "The proteins can't be more than 200")
    private float proteins;

    @NotBlank(message = "The fats is required")
    @Min(value = 0, message = "The fats can't be less then 0")
    @Max(value = 200, message = "The fats can't be more than 200")
    private float fats;

    @NotBlank(message = "The carbohydrates is required")
    @Min(value = 0, message = "The carbohydrates can't be less then 0")
    @Max(value = 300, message = "The carbohydrates can't be more than 300")
    private float carbohydrates;

}
