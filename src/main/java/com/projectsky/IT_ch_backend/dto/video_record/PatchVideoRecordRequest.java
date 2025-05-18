package com.projectsky.IT_ch_backend.dto.video_record;

import java.util.Optional;

public class PatchVideoRecordRequest {
    private Optional<String> title = Optional.empty();
    private Optional<String> refToVideo = Optional.empty();

    public Optional<String> getTitle() {
        return title;
    }

    public void setTitle(Optional<String> title) {
        this.title = title;
    }

    public Optional<String> getRefToVideo() {
        return refToVideo;
    }

    public void setRefToVideo(Optional<String> refToVideo) {
        this.refToVideo = refToVideo;
    }
}
