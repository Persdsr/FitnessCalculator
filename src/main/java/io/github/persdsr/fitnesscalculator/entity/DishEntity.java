package io.github.persdsr.fitnesscalculator.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "dish")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DishEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "meal_dish",
            joinColumns = @JoinColumn(name = "meal_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private List<MealEntity> meals = new ArrayList<>();

}
