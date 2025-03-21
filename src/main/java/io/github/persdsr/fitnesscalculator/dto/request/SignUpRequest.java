package io.github.persdsr.fitnesscalculator.dto.request;

import io.github.persdsr.fitnesscalculator.enums.Goal;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpRequest {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 70, message = "The name must contain from 2 to 70 characters")
    private String name;

    @Email
    @NotBlank(message = "The email is required")
    private String email;

    @NotNull(message = "The age is required")
    @Min(value = 7, message = "The age can't be less then 7")
    @Max(value = 127, message = "The age can't be more then 127")
    private byte age;

    @NotNull(message = "The weight is required")
    @Min(value = 15, message = "The weight can't be less then 15")
    @Max(value = 300, message = "The weight can't be more then 300")
    private float weight;

    @NotNull(message = "The height is required")
    @Min(value = 80, message = "The height can't be less then 80")
    @Max(value = 300, message = "The height can't be more then 300")
    private float height;

    @NotNull(message = "Goal is required")
    private Goal goal;
}
