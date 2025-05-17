package com.projectsky.IT_ch_backend.dto;

import java.time.LocalDate;

public record DeadlineDto(
        Long homeworkId,
        String title,
        LocalDate deadline,
        String courseName,
        boolean completed
) {
}
