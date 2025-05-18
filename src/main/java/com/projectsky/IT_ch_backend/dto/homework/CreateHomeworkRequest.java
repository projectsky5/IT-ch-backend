package com.projectsky.IT_ch_backend.dto.homework;

import java.time.LocalDate;

public record CreateHomeworkRequest(
        String title,
        LocalDate deadline,
        String refToHomework,
        String refToSubmitForm,
        String refToSolutionView
) {
}
