package com.projectsky.IT_ch_backend.dto.schedule;

import java.util.List;

public record ScheduleDayDto(
        String date,
        List<LessonDto> lessons
) {
}
