package com.projectsky.IT_ch_backend.dto;

public record CourseParticipantDto(
        Long userId,
        String fullName,
        String courseRole
) {
}
