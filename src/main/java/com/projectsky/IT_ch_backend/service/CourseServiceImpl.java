package com.projectsky.IT_ch_backend.service;

import com.projectsky.IT_ch_backend.dto.course.*;
import com.projectsky.IT_ch_backend.enums.Role;
import com.projectsky.IT_ch_backend.mapper.course.*;
import com.projectsky.IT_ch_backend.model.*;
import com.projectsky.IT_ch_backend.repository.CourseRepository;
import com.projectsky.IT_ch_backend.repository.CourseUserRepository;
import com.projectsky.IT_ch_backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseUserRepository courseUserRepository;
    private final UserRepository userRepository;
    private final CourseCreateRequestMapper courseCreateRequestMapper;
    private final CourseShortMapper courseShortMapper;
    private final CoursePatchMapper coursePatchMapper;
    private final CourseOnlyMapper courseOnlyMapper;

    public CourseServiceImpl(CourseRepository courseRepository,
                             CourseUserRepository courseUserRepository,
                             UserRepository userRepository,
                             CourseCreateRequestMapper courseCreateRequestMapper,
                             CourseShortMapper courseShortMapper,
                             CoursePatchMapper coursePatchMapper,
                             CourseOnlyMapper courseOnlyMapper) {
        this.courseRepository = courseRepository;
        this.courseUserRepository = courseUserRepository;
        this.userRepository = userRepository;
        this.courseCreateRequestMapper = courseCreateRequestMapper;
        this.courseShortMapper = courseShortMapper;
        this.coursePatchMapper = coursePatchMapper;
        this.courseOnlyMapper = courseOnlyMapper;
    }

    @Override
    public CourseOnlyDto getCourseById(Long courseId, Long currentUserId) throws ChangeSetPersister.NotFoundException {
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

        return new CourseOnlyDto(
                course.getCourseName(),
                course.getLocation(),
                course.getRefToChat(),
                course.getRefToGrades(),
                duration,
                courseRole,
                teacherName
        );

    }

    @Override
    @Transactional
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

    @Override
    @Transactional
    public void addParticipants(Long courseId, List<Long> userIds) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        List<User> users = userRepository.findAllById(userIds);

        List<CourseUser> courseUsers = users.stream()
                .map(user -> new CourseUser(
                        new CourseUserId(course.getId(), user.getId()),
                        course,
                        user,
                        user.getRole()
                ))
                .toList();

        courseUserRepository.saveAll(courseUsers);
    }

    @Override
    public List<CourseParticipantDto> getParticipants(Long courseId) {
        return courseUserRepository.findByCourse_Id(courseId)
                .stream()
                .map(cu -> new CourseParticipantDto(
                        cu.getUser().getId(),
                        cu.getUser().getFullName(),
                        cu.getCourseRole().name()
                ))
                .toList();
    }

    @Override
    @Transactional
    public void deleteCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        courseRepository.delete(course);
    }

    @Override
    @Transactional
    public CourseOnlyDto updateCourse(Long courseId, CoursePatchRequest request) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        coursePatchMapper.patchCourse(course, request);

        DurationRange duration = request.getDuration();

        if(duration != null){
            course.setStartModule(duration.start());
            course.setEndModule(duration.end());
        }

        return courseOnlyMapper.toDto(course);
    }

    @Override
    @Transactional
    public List<CourseParticipantDto> updateCourseRole(Long courseId, Long userId, Role newRole) {
        CourseUser courseUser = courseUserRepository
                .findByCourse_IdAndUser_id(courseId, userId)
                .orElseThrow(() -> new RuntimeException("<UNK> <UNK> <UNK> <UNK> <UNK>"));

        courseUser.setCourseRole(newRole);

        return getParticipants(courseId);
    }
}
