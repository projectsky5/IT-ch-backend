package com.projectsky.IT_ch_backend.controller;

import com.projectsky.IT_ch_backend.dto.auth.UserCreateRequest;
import com.projectsky.IT_ch_backend.model.User;
import com.projectsky.IT_ch_backend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/internal")
public class AuthIntegrationController {

    private final UserRepository userRepository;

    public AuthIntegrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUserFromAuth(
            @RequestBody UserCreateRequest request
            ) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            return ResponseEntity.ok("User already exists");
        }

        User user = new User();
        user.setEmail(request.email());
        user.setFullName(request.fullName());
        user.setRole(request.role());

        userRepository.save(user);

        return ResponseEntity.ok("User created");
    }
}
