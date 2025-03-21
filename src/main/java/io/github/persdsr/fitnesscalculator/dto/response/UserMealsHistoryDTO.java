package io.github.persdsr.fitnesscalculator.dto.response;

import io.github.persdsr.fitnesscalculator.entity.MealEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class UserMealsHistoryDTO {
    private LocalDate date;
    private List<MealDTO> meals;

}
