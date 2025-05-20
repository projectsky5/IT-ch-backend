package com.projectsky.IT_ch_backend.dto.auth;

import com.projectsky.IT_ch_backend.enums.Role;

public record UserCreateRequest(
        String email,
        String fullName,
        Role role
) {
}
