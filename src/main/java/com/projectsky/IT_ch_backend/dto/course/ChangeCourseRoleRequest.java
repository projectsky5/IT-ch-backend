package com.projectsky.IT_ch_backend.dto.course;

import com.projectsky.IT_ch_backend.enums.Role;

public record ChangeCourseRoleRequest(
        Role newRole
) {
}
