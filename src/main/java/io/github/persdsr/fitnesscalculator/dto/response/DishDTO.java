package io.github.persdsr.fitnesscalculator.dto.response;

import io.github.persdsr.fitnesscalculator.entity.DishEntity;
import lombok.Data;

@Data
public class DishDTO {

    private String name;

    private float calories;

    private float proteins;

    private float fats;

    private float carbohydrates;

    public static DishDTO toModel(DishEntity dishEntity) {
        DishDTO dishDTO = new DishDTO();
        dishDTO.setName(dishEntity.getName());
        dishDTO.setCalories(dishEntity.getCalories());
        dishDTO.setProteins(dishEntity.getProteins());
        dishDTO.setFats(dishEntity.getFats());
        dishDTO.setCarbohydrates(dishEntity.getCarbohydrates());
        return dishDTO;
    }
}
