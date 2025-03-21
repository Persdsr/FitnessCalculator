package io.github.persdsr.fitnesscalculator.controller;

import io.github.persdsr.fitnesscalculator.dto.response.CalorieCheckQuotaDTO;
import io.github.persdsr.fitnesscalculator.dto.response.DailyReportDTO;
import io.github.persdsr.fitnesscalculator.dto.response.MealDTO;
import io.github.persdsr.fitnesscalculator.dto.response.UserMealsHistoryDTO;
import io.github.persdsr.fitnesscalculator.service.MealService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/meal")
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @PostMapping
    public ResponseEntity<MealDTO> createMeal(@RequestParam Long userId, @RequestParam List<Long> dishIds) {
        return new ResponseEntity<>(mealService.createMeal(userId, dishIds), HttpStatus.CREATED);
    }

    @GetMapping("/daily-report")
    public ResponseEntity<DailyReportDTO> getDailyReport(
            @RequestParam Long userId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return new ResponseEntity<>(mealService.getDailyReport(userId, date), HttpStatus.OK);
    }

    @GetMapping("/check-calories")
    public ResponseEntity<CalorieCheckQuotaDTO> checkDailyCalories(
            @RequestParam Long userId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return new ResponseEntity<>(mealService.checkDailyCalories(userId, date), HttpStatus.OK);
    }

    @GetMapping("/meal-history")
    public ResponseEntity<List<UserMealsHistoryDTO>> getUserMealHistory(@RequestParam Long userId,
                                                                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return new ResponseEntity<>(mealService.getUserMealsHistory(userId, startDate, endDate), HttpStatus.OK);
    }

}
