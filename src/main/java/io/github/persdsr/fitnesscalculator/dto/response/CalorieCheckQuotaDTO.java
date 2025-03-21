package io.github.persdsr.fitnesscalculator.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CalorieCheckQuotaDTO {
    private LocalDate date;

    private double totalCalories;

    private double dailyCalorieIntake;

    private boolean isDailyQuotaComplete;
}