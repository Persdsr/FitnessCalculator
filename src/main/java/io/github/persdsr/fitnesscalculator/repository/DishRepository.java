package io.github.persdsr.fitnesscalculator.repository;

import io.github.persdsr.fitnesscalculator.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<DishEntity, Long> {
    List<DishEntity> findAllByIdIn(List<Long> id);
}
