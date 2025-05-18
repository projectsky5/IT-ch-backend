package com.projectsky.IT_ch_backend.dto.video_record;

public record CreateVideoRecordRequest(
        String title,
        String refToVideo
) {
}
