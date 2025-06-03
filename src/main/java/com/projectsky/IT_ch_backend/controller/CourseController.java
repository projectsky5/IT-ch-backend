package com.projectsky.IT_ch_backend.controller;

import com.projectsky.IT_ch_backend.dto.course.*;
import com.projectsky.IT_ch_backend.enums.Role;
import com.projectsky.IT_ch_backend.service.CourseService;
import com.projectsky.IT_ch_backend.service.CurrentUserService;
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
    private final CurrentUserService currentUserService;

    public CourseController(CourseService courseService,
                            CurrentUserService currentUserService) {
        this.courseService = courseService;
        this.currentUserService = currentUserService;
    }

    @GetMapping
    public ResponseEntity<List<CourseShortDto>> getCoursesForUser() {
        Long userId = currentUserService.getCurrentUser().getId();
        return ResponseEntity.ok(courseService.getAllShortCoursesForUser(userId));
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseOnlyDto> getCourseById(@PathVariable Long courseId
                                                   ) throws ChangeSetPersister.NotFoundException {
        Long userId = currentUserService.getCurrentUser().getId();
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
        Long userId = currentUserService.getCurrentUser().getId();
        courseService.createCourse(request, userId);
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

    @PatchMapping("participant/{userId}")
    public ResponseEntity<List<CourseParticipantDto>> updateParticipantRole(
            @PathVariable Long userId,
            @RequestBody PatchRoleDto roleDto){
        List<CourseParticipantDto> updatedRole = courseService.updateCourseRole(userId, roleDto);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/{courseId}/delete/{userId}")
    public ResponseEntity<Void> deleteUserFromCourse(
            @PathVariable Long courseId,
            @PathVariable Long userId
    ) {
        /* добавил */ Long currentUserId = currentUserService.getCurrentUser().getId();
        courseService.deleteUserFromCourse(courseId, userId, currentUserId); /*Добавил в параметр*/
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{courseId}/delete")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
