package com.projectsky.IT_ch_backend.service;

import com.projectsky.IT_ch_backend.dto.course.*;
import com.projectsky.IT_ch_backend.enums.Role;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface CourseService {
    CourseOnlyDto getCourseById(Long courseId, Long courseUserId) throws ChangeSetPersister.NotFoundException;

    void createCourse(CourseCreateRequest request, Long currentUserId);

    List<CourseShortDto> getAllShortCoursesForUser(Long userId);

    void addParticipants(Long courseId, List<Long> userIds);

    List<CourseParticipantDto> getParticipants(Long courseId);

    void deleteCourse(Long courseId);

    void deleteUserFromCourse(Long courseId, Long userId, Long currentUserId); // добавил в параметр currentUserId

    CourseOnlyDto updateCourse(Long courseId, CoursePatchRequest request);

    List<CourseParticipantDto> updateCourseRole(Long userId, PatchRoleDto roleDto);

}
