package io.github.persdsr.fitnesscalculator.service;

import io.github.persdsr.fitnesscalculator.dto.response.*;
import io.github.persdsr.fitnesscalculator.entity.DishEntity;
import io.github.persdsr.fitnesscalculator.entity.MealEntity;
import io.github.persdsr.fitnesscalculator.entity.UserEntity;
import io.github.persdsr.fitnesscalculator.enums.Goal;
import io.github.persdsr.fitnesscalculator.repository.DishRepository;
import io.github.persdsr.fitnesscalculator.repository.MealRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MealService {

    private final MealRepository mealRepository;
    private final UserService userService;
    private final DishRepository dishRepository;

    public MealDTO createMeal(Long userId, List<Long> dishIds) {
        UserEntity user = userService.getUserById(userId);
        List<DishEntity> dishes = dishRepository.findAllById(dishIds);

        MealEntity meal = new MealEntity();
        meal.setOwner(user);
        meal.getDishes().addAll(dishes);
        meal.setDate(LocalDate.now());

        mealRepository.save(meal);

        dishes.stream()
                .forEach(dish -> {
                            dish.getMeals().add(meal);
                        });


        dishRepository.saveAll(dishes);

        return MealDTO.toModel(meal);
    }

    public DailyReportDTO getDailyReport(Long userId, LocalDate date) {
        UserEntity user = userService.getUserById(userId);

        if (date == null) {
            date = LocalDate.now();
        }

        List<MealDTO> meals = mealRepository.findByOwnerAndDate(user, date).stream().map(MealDTO::toModel).toList();

        double totalCalories = meals.stream()
                .flatMap(meal -> meal.getDishes().stream())
                .mapToDouble(DishDTO::getCalories)
                .sum();

        return new DailyReportDTO(date, totalCalories, meals);
    }

    public CalorieCheckQuotaDTO checkDailyCalories(Long userId, LocalDate date) {
        UserEntity user = userService.getUserById(userId);

        if (date == null) {
            date = LocalDate.now();
        }

        DailyReportDTO report = getDailyReport(userId, date);

        double dailyCalorieIntake = calculateDailyCalorieIntake(
                user.getGoal(),
                user.getWeight(),
                user.getHeight(),
                user.getAge()
                );

        double totalCalories = report.getTotalCalories();
        boolean isDailyQuotaComplete = totalCalories >= dailyCalorieIntake;

        return new CalorieCheckQuotaDTO(date, totalCalories, dailyCalorieIntake, isDailyQuotaComplete);
    }

    public List<UserMealsHistoryDTO> getUserMealsHistory(Long userId, LocalDate startDate, LocalDate endDate) {
        UserEntity user = userService.getUserById(userId);

        if (startDate == null) {
            startDate = LocalDate.now().minusDays(7);
        }
        if (endDate == null) {
            endDate = LocalDate.now();
        }

        List<MealEntity> meals = mealRepository.findByOwnerAndDateBetween(user, startDate, endDate);

       Map<LocalDate, List<MealDTO>> mealsByDate = meals.stream()
               .collect(Collectors.groupingBy(MealEntity::getDate, Collectors.mapping(MealDTO::toModel, Collectors.toList())));

        return mealsByDate.entrySet().stream()
                .map(entry -> new UserMealsHistoryDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public double calculateDailyCalorieIntake(Goal goal, float weight, float height, byte age) {
        double bmr = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        return switch (goal) {
            case WEIGHT_LOSS -> bmr * 0.8;
            case WEIGHT_GAIN -> bmr * 1.2;
            default -> bmr;
        };
    }

}
