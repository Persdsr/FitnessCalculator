package io.github.persdsr.fitnesscalculator.repository;

import io.github.persdsr.fitnesscalculator.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
