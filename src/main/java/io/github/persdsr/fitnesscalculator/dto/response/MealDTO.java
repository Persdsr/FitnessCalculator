package io.github.persdsr.fitnesscalculator.dto.response;

import io.github.persdsr.fitnesscalculator.entity.MealEntity;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class MealDTO {
    private String ownerName;
    private List<DishDTO> dishes;

    public static MealDTO toModel(MealEntity mealEntity) {
        MealDTO mealDTO = new MealDTO();
        mealDTO.setOwnerName(mealEntity.getOwner().getName());
        mealDTO.setDishes(mealEntity.getDishes().stream()
                .map(DishDTO::toModel)
                .toList()
        );

        return mealDTO;
    }
}
