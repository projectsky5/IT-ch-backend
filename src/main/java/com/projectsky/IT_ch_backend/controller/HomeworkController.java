package com.projectsky.IT_ch_backend.controller;

import com.projectsky.IT_ch_backend.dto.CreateHomeworkRequest;
import com.projectsky.IT_ch_backend.dto.HomeworkDtoForStudent;
import com.projectsky.IT_ch_backend.dto.HomeworkDtoForTeacher;
import com.projectsky.IT_ch_backend.service.HomeworkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course/{courseId}/homeworks")
public class HomeworkController {

    private final HomeworkService homeworkService;

    public HomeworkController(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }

    @GetMapping("/student")
    public ResponseEntity<List<HomeworkDtoForStudent>> getHomeworksForStudent(
            @PathVariable Long courseId
    ) {
        return ResponseEntity.ok(homeworkService.getHomeworksForStudent(courseId));
    }

    @GetMapping("/teacher")
    public ResponseEntity<List<HomeworkDtoForTeacher>> getHomeworksForTeacher(
            @PathVariable Long courseId
    ) {
        return ResponseEntity.ok(homeworkService.getHomeworksForTeacher(courseId));
    }

    @PostMapping
    public ResponseEntity<Void> createHomework(
            @PathVariable Long courseId,
            @RequestBody CreateHomeworkRequest request
    ) {
        homeworkService.addHomework(courseId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
