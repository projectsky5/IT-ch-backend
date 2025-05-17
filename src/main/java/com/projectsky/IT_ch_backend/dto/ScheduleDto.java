package com.projectsky.IT_ch_backend.dto;

import java.time.LocalTime;

public record ScheduleDto(
        String dayOfWeek,
        Integer academicHours,
        String startTime,
        String frequency
) {
}
