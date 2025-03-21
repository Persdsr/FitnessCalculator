package io.github.persdsr.fitnesscalculator.dto.response;

import io.github.persdsr.fitnesscalculator.entity.UserEntity;
import io.github.persdsr.fitnesscalculator.enums.Goal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class UserDTO {

    private int id;

    private String name;

    private String email;

    private byte age;

    private float weight;

    private float height;

    private Goal goal;

    public static UserDTO toModel(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userDTO.getId());
        userDTO.setName(userEntity.getName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setAge(userEntity.getAge());
        userDTO.setWeight(userEntity.getWeight());
        userDTO.setHeight(userEntity.getHeight());
        userDTO.setGoal(userEntity.getGoal());

        return userDTO;
    }
}
