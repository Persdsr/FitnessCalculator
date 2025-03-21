package io.github.persdsr.fitnesscalculator.repository;

import io.github.persdsr.fitnesscalculator.entity.MealEntity;
import io.github.persdsr.fitnesscalculator.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository extends JpaRepository<MealEntity, Integer> {
    List<MealEntity> findByOwnerAndDate(UserEntity user, LocalDate date);
    List<MealEntity> findByOwnerAndDateBetween(UserEntity user, LocalDate startDate, LocalDate endDate);
}
