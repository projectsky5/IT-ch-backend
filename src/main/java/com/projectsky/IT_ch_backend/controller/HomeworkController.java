package com.projectsky.IT_ch_backend.controller;

import com.projectsky.IT_ch_backend.dto.homework.CreateHomeworkRequest;
import com.projectsky.IT_ch_backend.dto.homework.HomeworkDtoForStudent;
import com.projectsky.IT_ch_backend.dto.homework.HomeworkDtoForTeacher;
import com.projectsky.IT_ch_backend.dto.homework.PatchHomeworkRequest;
import com.projectsky.IT_ch_backend.service.HomeworkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/homework")
public class HomeworkController {

    private final HomeworkService homeworkService;

    public HomeworkController(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }

    @GetMapping("/{courseId}/student")
    public ResponseEntity<List<HomeworkDtoForStudent>> getHomeworksForStudent(
            @PathVariable Long courseId
    ) {
        return ResponseEntity.ok(homeworkService.getHomeworksForStudent(courseId));
    }

    @GetMapping("/{courseId}/teacher")
    public ResponseEntity<List<HomeworkDtoForTeacher>> getHomeworksForTeacher(
            @PathVariable Long courseId
    ) {
        return ResponseEntity.ok(homeworkService.getHomeworksForTeacher(courseId));
    }

    @PostMapping("/{courseId}/create")
    public ResponseEntity<Void> createHomework(
            @PathVariable Long courseId,
            @RequestBody CreateHomeworkRequest request
    ) {
        homeworkService.addHomework(courseId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{homeworkId}/patch")
    public ResponseEntity<HomeworkDtoForTeacher> updateHomework(
            @PathVariable Long homeworkId,
            @RequestBody PatchHomeworkRequest request
    ) {
        return ResponseEntity.ok(homeworkService.updateHomework(homeworkId, request));
    }

    @DeleteMapping("/{homeworkId}/delete")
    public ResponseEntity<Void> deleteHomework(@PathVariable Long homeworkId) {
        homeworkService.deleteHomework(homeworkId);
        return ResponseEntity.noContent().build();
    }
}
