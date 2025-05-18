package com.projectsky.IT_ch_backend.dto.course;


import com.projectsky.IT_ch_backend.dto.schedule.ScheduleDto;

public record CourseDto(
        String courseName,
        String location,
        String refToChat,
        String refToGrades,
        DurationRange duration,

        String courseRole,
        String teacherName,

        ScheduleDto schedule
) {
}
