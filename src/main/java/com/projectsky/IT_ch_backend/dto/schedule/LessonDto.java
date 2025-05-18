package com.projectsky.IT_ch_backend.dto.schedule;

public record LessonDto(
        String courseName,
        String location,
        String startTime,
        String endTime
) {
}
