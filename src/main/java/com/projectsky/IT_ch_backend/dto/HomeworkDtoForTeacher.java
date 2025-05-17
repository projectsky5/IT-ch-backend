package com.projectsky.IT_ch_backend.dto;

import java.time.LocalDate;

public record HomeworkDtoForTeacher(
        Long id,
        String title,
        LocalDate deadline,
        String refToHomework,
        String refToSolutionView
) {
}
