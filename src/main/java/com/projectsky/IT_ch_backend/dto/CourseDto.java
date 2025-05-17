package com.projectsky.IT_ch_backend.dto;


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
