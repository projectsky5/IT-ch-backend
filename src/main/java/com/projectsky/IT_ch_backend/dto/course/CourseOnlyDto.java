package com.projectsky.IT_ch_backend.dto.course;

public record CourseOnlyDto(
        String courseName,
        String location,
        String refToChat,
        String refToGrades,
        DurationRange duration,
        String courseRole,
        String teacherName
) {
}
