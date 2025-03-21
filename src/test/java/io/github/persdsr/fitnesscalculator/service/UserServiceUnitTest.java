package io.github.persdsr.fitnesscalculator.service;

import io.github.persdsr.fitnesscalculator.dto.request.SignUpRequest;
import io.github.persdsr.fitnesscalculator.dto.response.UserDTO;
import io.github.persdsr.fitnesscalculator.entity.UserEntity;
import io.github.persdsr.fitnesscalculator.enums.Goal;
import io.github.persdsr.fitnesscalculator.exception.exception.UserNotFoundException;
import io.github.persdsr.fitnesscalculator.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void testRegisterUser_CreateUser_ReturnsSuccessResponse() {
        SignUpRequest userRequest = SignUpRequest.builder()
                .name("name")
                .email("mail@mail.ru")
                .goal(Goal.WEIGHT_GAIN)
                .age((byte) 18)
                .height(180)
                .weight(68)
                .build();

        UserEntity savedUser = new UserEntity();
        savedUser.setName(userRequest.getName());
        savedUser.setEmail(userRequest.getEmail());
        savedUser.setGoal(userRequest.getGoal());
        savedUser.setAge(userRequest.getAge());
        savedUser.setHeight(userRequest.getHeight());
        savedUser.setWeight(userRequest.getWeight());

        when(userRepository.save(any(UserEntity.class))).thenReturn(savedUser);

        UserDTO userResponse = userService.registerUser(userRequest);

        assertEquals(userResponse.getEmail(), userRequest.getEmail());

        verify(userRepository, times(1)).save(any(UserEntity.class));
        assertEquals("name", savedUser.getName());
    }

    @Test
    void testGetUserById_UserExists_ReturnsUser() {
        Long userId = 1L;
        UserEntity expectedUser = new UserEntity();
        expectedUser.setId(userId);
        expectedUser.setName("name");

        when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));

        UserEntity actualUser = userService.getUserById(userId);

        assertEquals(expectedUser, actualUser);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testGetUserById_UserNotFound_ThrowsUserNotFoundException() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userService.getUserById(userId);
        });

        assertEquals("User with id " + userId + " not found", exception.getMessage());
        verify(userRepository, times(1)).findById(userId);
    }
}
