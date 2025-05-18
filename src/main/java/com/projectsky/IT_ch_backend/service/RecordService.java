package com.projectsky.IT_ch_backend.service;

import com.projectsky.IT_ch_backend.dto.video_record.CreateVideoRecordRequest;
import com.projectsky.IT_ch_backend.dto.video_record.PatchVideoRecordRequest;
import com.projectsky.IT_ch_backend.dto.video_record.VideoRecordDto;

import java.util.List;

public interface RecordService {
    List<VideoRecordDto> getRecordsByCourseId(Long courseId);

    void addRecordToCourse(Long courseId, CreateVideoRecordRequest recordRequest);

    void deleteRecordById(Long recordId);

    VideoRecordDto updateRecordById(Long recordId, PatchVideoRecordRequest recordRequest);
}
