package com.projectsky.IT_ch_backend.dto.schedule;

public record ScheduleDto(
        String dayOfWeek,
        Integer academicHours,
        String startTime,
        String frequency
) {
}
