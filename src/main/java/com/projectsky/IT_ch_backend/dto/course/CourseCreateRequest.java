package com.projectsky.IT_ch_backend.dto.course;

import com.projectsky.IT_ch_backend.dto.schedule.ScheduleDto;

public record CourseCreateRequest(
        String courseName,
        String location,
        DurationRange duration,
        String refToChat,
        String refToGrades,
        ScheduleDto schedule
) {
}
