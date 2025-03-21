package io.github.persdsr.fitnesscalculator.controller;

import io.github.persdsr.fitnesscalculator.dto.request.SignUpRequest;
import io.github.persdsr.fitnesscalculator.dto.response.UserDTO;
import io.github.persdsr.fitnesscalculator.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        return new ResponseEntity<>(userService.registerUser(signUpRequest), HttpStatus.CREATED);
    }

}
