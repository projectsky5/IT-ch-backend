package com.projectsky.IT_ch_backend.dto;

import java.time.LocalDate;

public record HomeworkDtoForStudent(
        Long id,
        String title,
        LocalDate deadline,
        String refToHomework,
        String refToSubmitForm
) {
}
