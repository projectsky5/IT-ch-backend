package com.projectsky.IT_ch_backend.dto.course;

import java.util.List;

public record AddParticipantsRequest(
        List<Long> userIds
) {
}
