package com.projectsky.IT_ch_backend.service;

import com.projectsky.IT_ch_backend.dto.AvailableUserDto;
import com.projectsky.IT_ch_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<AvailableUserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new AvailableUserDto(
                        user.getId(),
                        user.getFullName(),
                        user.getRole().name()
                ))
                .toList();
    }
}
