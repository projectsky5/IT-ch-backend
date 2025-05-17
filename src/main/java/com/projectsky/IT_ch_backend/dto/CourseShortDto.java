package com.projectsky.IT_ch_backend.dto;

public record CourseShortDto(
        Long id,
        String courseName,
        String teacherName,
        String courseRole
) {
}
