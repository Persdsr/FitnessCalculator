package io.github.persdsr.fitnesscalculator.service;

import io.github.persdsr.fitnesscalculator.dto.request.DishRequest;
import io.github.persdsr.fitnesscalculator.dto.response.DishDTO;
import io.github.persdsr.fitnesscalculator.entity.DishEntity;
import io.github.persdsr.fitnesscalculator.repository.DishRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DishServiceUnitTest {

    @Mock
    DishRepository dishRepository;

    @InjectMocks
    DishService dishService;

    @Test
    void testCreateDish_ReturnsSuccess() {
        DishRequest dishRequest = DishRequest.builder()
                .name("Name")
                .calories(500)
                .proteins(30)
                .fats(20)
                .carbohydrates(40)
                .build();

        DishEntity expectedDishEntity = new DishEntity();
        expectedDishEntity.setName("Name");
        expectedDishEntity.setCalories(500);
        expectedDishEntity.setProteins(30);
        expectedDishEntity.setFats(20);
        expectedDishEntity.setCarbohydrates(40);

        when(dishRepository.save(any(DishEntity.class))).thenReturn(expectedDishEntity);

        DishDTO dishResponse = dishService.createDish(dishRequest);

        assertEquals(dishResponse.getName(), dishRequest.getName());
        verify(dishRepository, times(1)).save(any(DishEntity.class));

    }

}