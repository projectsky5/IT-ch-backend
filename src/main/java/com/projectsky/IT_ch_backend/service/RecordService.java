package com.projectsky.IT_ch_backend.service;

import com.projectsky.IT_ch_backend.dto.CreateVideoRecordRequest;
import com.projectsky.IT_ch_backend.dto.VideoRecordDto;

import java.util.List;

public interface RecordService {
    List<VideoRecordDto> getRecordsByCourseId(Long courseId);

    void addRecordToCourse(Long courseId, CreateVideoRecordRequest recordRequest);
}
