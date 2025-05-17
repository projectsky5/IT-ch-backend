package com.projectsky.IT_ch_backend.dto;

public record CourseCreateRequest(
        String courseName,
        String location,
        DurationRange duration,
        String refToChat,
        String refToGrades,
        ScheduleDto schedule
) {
}
