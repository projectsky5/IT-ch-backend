package com.projectsky.IT_ch_backend.service;

import com.projectsky.IT_ch_backend.dto.AvailableUserDto;

import java.util.List;

public interface UserService {

    List<AvailableUserDto> getAllUsers();
}
