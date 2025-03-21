package io.github.persdsr.fitnesscalculator.entity;

import io.github.persdsr.fitnesscalculator.enums.Goal;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;


@Entity(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 70, message = "The name must contain from 2 to 70 characters")
    private String name;

    @Email
    @NotBlank(message = "The email is required")
    private String email;

    @Min(value = 7, message = "The age can't be less then 7")
    @Max(value = 127, message = "The age can't be more then 127")
    private byte age;

    @Min(value = 15, message = "The weight can't be less then 15")
    @Max(value = 300, message = "The weight can't be more then 300")
    private float weight;

    @Min(value = 80, message = "The height can't be less then 80")
    @Max(value = 300, message = "The height can't be more then 300")
    private float height;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Goal is required")
    private Goal goal;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MealEntity> meals;

}
