package io.github.persdsr.fitnesscalculator.service;

import io.github.persdsr.fitnesscalculator.dto.request.DishRequest;
import io.github.persdsr.fitnesscalculator.dto.response.DishDTO;
import io.github.persdsr.fitnesscalculator.entity.DishEntity;
import io.github.persdsr.fitnesscalculator.exception.exception.DishNotFoundException;
import io.github.persdsr.fitnesscalculator.repository.DishRepository;
import org.springframework.stereotype.Service;

@Service
public class DishService {

    private final DishRepository dishRepository;

    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public DishDTO createDish(DishRequest dishRequest) {
        DishEntity dishEntity = new DishEntity();
        dishEntity.setName(dishRequest.getName());
        dishEntity.setCalories(dishRequest.getCalories());
        dishEntity.setFats(dishRequest.getFats());
        dishEntity.setCarbohydrates(dishRequest.getCarbohydrates());
        dishEntity.setProteins(dishRequest.getProteins());

        return DishDTO.toModel(dishRepository.save(dishEntity));
    }

}
