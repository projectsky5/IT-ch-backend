package com.projectsky.IT_ch_backend.controller;

import com.projectsky.IT_ch_backend.dto.course.*;
import com.projectsky.IT_ch_backend.service.CourseService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CourseShortDto>> getCoursesForUser(
            @RequestParam Long userId
    ) {
        return ResponseEntity.ok(courseService.getAllShortCoursesForUser(userId));
    }

    @GetMapping("/{courseId}/{userId}")
    public ResponseEntity<CourseOnlyDto> getCourseById(@PathVariable Long courseId,
                                                   @PathVariable Long userId) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(courseService.getCourseById(courseId, userId));
    }

    @GetMapping("/{courseId}/participants")
    public ResponseEntity<List<CourseParticipantDto>> getCourseParticipants(
            @PathVariable Long courseId
    ) {
        return ResponseEntity.ok(courseService.getParticipants(courseId));
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createCourse(
            @RequestBody @Validated CourseCreateRequest request
    ){
        courseService.createCourse(request, 1L);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{courseId}/participants")
    public ResponseEntity<Void> addParticipantToCourse(
            @PathVariable Long courseId,
            @RequestBody AddParticipantsRequest request
            ) {
        courseService.addParticipants(courseId, request.userIds());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{courseId}/patch")
    public ResponseEntity<CourseOnlyDto> updateCourse(
            @PathVariable Long courseId,
            @RequestBody CoursePatchRequest request
    ) {
        CourseOnlyDto updatedCourse = courseService.updateCourse(courseId, request);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{courseId}/delete")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
