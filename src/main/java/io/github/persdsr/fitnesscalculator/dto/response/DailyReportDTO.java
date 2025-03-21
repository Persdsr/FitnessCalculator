package io.github.persdsr.fitnesscalculator.dto.response;

import io.github.persdsr.fitnesscalculator.entity.MealEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyReportDTO {
    private LocalDate date;
    private double totalCalories;
    private List<MealDTO> meals;



}