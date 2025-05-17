package com.projectsky.IT_ch_backend.service;

import com.projectsky.IT_ch_backend.dto.CourseCreateRequest;
import com.projectsky.IT_ch_backend.dto.CourseDto;
import com.projectsky.IT_ch_backend.dto.CourseParticipantDto;
import com.projectsky.IT_ch_backend.dto.CourseShortDto;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface CourseService {
    CourseDto getCourseById(Long courseId, Long courseUserId) throws ChangeSetPersister.NotFoundException;

    void createCourse(CourseCreateRequest request, Long currentUserId);

    List<CourseShortDto> getAllShortCoursesForUser(Long userId);

    void addParticipants(Long courseId, List<Long> userIds);

    List<CourseParticipantDto> getParticipants(Long courseId);

}
