package com.projectsky.IT_ch_backend.controller;

import com.projectsky.IT_ch_backend.dto.video_record.CreateVideoRecordRequest;
import com.projectsky.IT_ch_backend.dto.video_record.PatchVideoRecordRequest;
import com.projectsky.IT_ch_backend.dto.video_record.VideoRecordDto;
import com.projectsky.IT_ch_backend.service.RecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")
public class VideoRecordController {

    private final RecordService recordService;

    public VideoRecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("{courseId}/course/get")
    public ResponseEntity<List<VideoRecordDto>> getRecords(
            @PathVariable Long courseId
    ) {
        return ResponseEntity.ok(recordService.getRecordsByCourseId(courseId));
    }

    @PostMapping("{courseId}/course/add")
    public ResponseEntity<Void> addRecord(
            @PathVariable Long courseId,
            @RequestBody CreateVideoRecordRequest request
            ) {
        recordService.addRecordToCourse(courseId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{recordId}/patch")
    public ResponseEntity<VideoRecordDto> patchVideoRecord(
            @PathVariable Long recordId,
            @RequestBody PatchVideoRecordRequest request
    ) {
        return ResponseEntity.ok(recordService.updateRecordById(recordId, request));
    }

    @DeleteMapping("/{recordId}/delete")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long recordId) {
        recordService.deleteRecordById(recordId);
        return ResponseEntity.noContent().build();
    }
}
