package com.projectsky.IT_ch_backend.service;

import com.projectsky.IT_ch_backend.dto.*;
import com.projectsky.IT_ch_backend.enums.Frequency;
import com.projectsky.IT_ch_backend.enums.Role;
import com.projectsky.IT_ch_backend.enums.Weekday;
import com.projectsky.IT_ch_backend.mapper.CourseCreateRequestMapper;
import com.projectsky.IT_ch_backend.mapper.CourseMapper;
import com.projectsky.IT_ch_backend.mapper.CourseShortMapper;
import com.projectsky.IT_ch_backend.mapper.ScheduleMapper;
import com.projectsky.IT_ch_backend.model.*;
import com.projectsky.IT_ch_backend.repository.CourseRepository;
import com.projectsky.IT_ch_backend.repository.CourseUserRepository;
import com.projectsky.IT_ch_backend.repository.UserRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseUserRepository courseUserRepository;
    private final CourseMapper courseMapper;
    private final ScheduleMapper scheduleMapper;
    private final UserRepository userRepository;
    private final CourseCreateRequestMapper courseCreateRequestMapper;
    private final CourseShortMapper courseShortMapper;

    public CourseServiceImpl(CourseRepository courseRepository,
                             CourseMapper courseMapper,
                             ScheduleMapper scheduleMapper,
                             CourseUserRepository courseUserRepository,
                             UserRepository userRepository,
                             CourseCreateRequestMapper courseCreateRequestMapper, CourseShortMapper courseShortMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.scheduleMapper = scheduleMapper;
        this.courseUserRepository = courseUserRepository;
        this.userRepository = userRepository;
        this.courseCreateRequestMapper = courseCreateRequestMapper;
        this.courseShortMapper = courseShortMapper;
    }

    @Override
    public CourseDto getCourseById(Long courseId, Long currentUserId) throws ChangeSetPersister.NotFoundException {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found")); // notfoundex

        // Поиск текущего пользователя в курсе

        CourseUser courseUser = course.getCourseUsers().stream()
                .filter(cu -> cu.getUser().getId().equals(currentUserId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Вы не состоите в этом курсе"));

        DurationRange duration = new DurationRange(
                course.getStartModule(),
                course.getEndModule()
        );

        String courseRole = courseUser.getCourseRole().name();
        String teacherName = course.getCreator().getFullName();

        ScheduleDto scheduleDto = scheduleMapper.toDto(course.getSchedule());

        return new CourseDto(
                course.getCourseName(),
                course.getLocation(),
                course.getRefToChat(),
                course.getRefToGrades(),
                duration,
                courseRole,
                teacherName,
                scheduleDto
        );

    }

    @Override
    public void createCourse(CourseCreateRequest request, Long currentUserId) {
        User user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new RuntimeException("<UNK> <UNK> <UNK> <UNK> <UNK> <UNK>"));

        Course course = courseCreateRequestMapper.toEntity(request, user);
        courseRepository.save(course);

        CourseUser courseUser = new CourseUser(
                new CourseUserId(course.getId(), user.getId()),
                course,
                user,
                Role.TEACHER
        );

        courseUserRepository.save(courseUser);
    }

    @Override
    public List<CourseShortDto> getAllShortCoursesForUser(Long userId) {
        return courseUserRepository.findByUserId(userId)
                .stream()
                .map(courseShortMapper::toDto)
                .toList();
    }
}
