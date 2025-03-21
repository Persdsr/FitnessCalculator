package io.github.persdsr.fitnesscalculator.controller;

import io.github.persdsr.fitnesscalculator.dto.request.DishRequest;
import io.github.persdsr.fitnesscalculator.dto.response.DishDTO;
import io.github.persdsr.fitnesscalculator.service.DishService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dish")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping
    public ResponseEntity<DishDTO> createDish(@Valid @RequestBody DishRequest dish) {
        return new ResponseEntity<>(dishService.createDish(dish), HttpStatus.CREATED);
    }

}
