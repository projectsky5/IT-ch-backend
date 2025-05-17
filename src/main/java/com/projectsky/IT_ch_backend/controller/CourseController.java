package com.projectsky.IT_ch_backend.controller;

import com.projectsky.IT_ch_backend.dto.CourseCreateRequest;
import com.projectsky.IT_ch_backend.dto.CourseDto;
import com.projectsky.IT_ch_backend.dto.CourseShortDto;
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
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long courseId,
                                                   @PathVariable Long userId) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(courseService.getCourseById(courseId, userId));
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createCourse(
            @RequestBody @Validated CourseCreateRequest request
    ){
        courseService.createCourse(request, 1L);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
