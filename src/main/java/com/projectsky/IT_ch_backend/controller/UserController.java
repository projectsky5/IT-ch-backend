package com.projectsky.IT_ch_backend.controller;

import com.projectsky.IT_ch_backend.dto.user.AvailableUserDto;
import com.projectsky.IT_ch_backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<AvailableUserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
