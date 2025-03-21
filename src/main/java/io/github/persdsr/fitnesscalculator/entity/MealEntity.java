package io.github.persdsr.fitnesscalculator.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "meal")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MealEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    @NotNull(message = "Owner is required")
    private UserEntity owner;

    @ManyToMany(mappedBy = "meals", fetch = FetchType.EAGER)
    private List<DishEntity> dishes = new ArrayList<>();

    private LocalDate date;

}
