package com.projectsky.IT_ch_backend.dto.homework;

import java.time.LocalDate;

public record HomeworkDtoForTeacher(
        Long id,
        String title,
        LocalDate deadline,
        String refToHomework,
        String refToSolutionView
) {
}
