package com.projectsky.IT_ch_backend.controller;

import com.projectsky.IT_ch_backend.dto.CreateVideoRecordRequest;
import com.projectsky.IT_ch_backend.dto.VideoRecordDto;
import com.projectsky.IT_ch_backend.service.RecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses/{courseId}/records")
public class VideoRecordController {

    private final RecordService recordService;

    public VideoRecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping
    public ResponseEntity<List<VideoRecordDto>> getRecords(
            @PathVariable Long courseId
    ) {
        return ResponseEntity.ok(recordService.getRecordsByCourseId(courseId));
    }

    @PostMapping
    public ResponseEntity<Void> addRecord(
            @PathVariable Long courseId,
            @RequestBody CreateVideoRecordRequest request
            ) {
        recordService.addRecordToCourse(courseId, request);
        return ResponseEntity.ok().build();
    }
}
