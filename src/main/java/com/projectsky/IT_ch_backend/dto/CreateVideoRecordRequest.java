package com.projectsky.IT_ch_backend.dto;

public record CreateVideoRecordRequest(
        String title,
        String refToVideo
) {
}
