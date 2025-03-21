package io.github.persdsr.fitnesscalculator.service;


import io.github.persdsr.fitnesscalculator.dto.response.MealDTO;
import io.github.persdsr.fitnesscalculator.entity.*;
import io.github.persdsr.fitnesscalculator.exception.exception.UserNotFoundException;
import io.github.persdsr.fitnesscalculator.repository.DishRepository;
import io.github.persdsr.fitnesscalculator.repository.MealRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MealServiceUnitTest {

    @Mock
    private UserService userService;

    @Mock
    private DishRepository dishRepository;

    @Mock
    private MealRepository mealRepository;

    @InjectMocks
    private MealService mealService;

    @Test
    void testCreateMeal_ReturnsSuccess() {
        Long userId = 1L;
        List<Long> dishIds = List.of(1L, 2L);

        UserEntity user = new UserEntity();
        user.setId(userId);

        DishEntity dish1 = new DishEntity();
        dish1.setId(1L);
        DishEntity dish2 = new DishEntity();
        dish2.setId(2L);
        List<DishEntity> dishes = List.of(dish1, dish2);

        MealEntity expectedMeal = new MealEntity();
        expectedMeal.setId(1L);
        expectedMeal.setOwner(user);
        expectedMeal.setDishes(dishes);

        when(userService.getUserById(userId)).thenReturn(user);
        when(dishRepository.findAllById(dishIds)).thenReturn(dishes);
        when(mealRepository.save(any(MealEntity.class))).thenReturn(expectedMeal);

        MealDTO mealResponse = mealService.createMeal(userId, dishIds);

        assertEquals(user.getName(), mealResponse.getOwnerName());
        assertEquals(mealResponse.getDishes().get(0).getName(), expectedMeal.getDishes().get(0).getName());
        verify(mealRepository, times(1)).save(any(MealEntity.class));
    }

    @Test
    void testCreateMeal_UserNotFound_ThrowsException() {
        Long userId = 1L;
        List<Long> dishIds = List.of(1L, 2L);

        when(userService.getUserById(userId)).thenThrow(new UserNotFoundException(userId));

        assertThrows(UserNotFoundException.class, () -> mealService.createMeal(userId, dishIds));

        verify(mealRepository, never()).save(any());
        verify(dishRepository, never()).saveAll(any());
    }



}