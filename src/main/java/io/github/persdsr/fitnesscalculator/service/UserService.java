package io.github.persdsr.fitnesscalculator.service;

import io.github.persdsr.fitnesscalculator.dto.request.SignUpRequest;
import io.github.persdsr.fitnesscalculator.dto.response.UserDTO;
import io.github.persdsr.fitnesscalculator.entity.UserEntity;
import io.github.persdsr.fitnesscalculator.exception.exception.UserNotFoundException;
import io.github.persdsr.fitnesscalculator.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO registerUser(SignUpRequest signUpRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(signUpRequest.getEmail());
        userEntity.setName(signUpRequest.getName());
        userEntity.setAge(signUpRequest.getAge());
        userEntity.setHeight(signUpRequest.getHeight());
        userEntity.setWeight(signUpRequest.getWeight());
        userEntity.setGoal(signUpRequest.getGoal());

        return UserDTO.toModel(userRepository.save(userEntity));
    }

    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(id)
        );
    }
}
