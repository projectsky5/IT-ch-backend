package com.projectsky.IT_ch_backend.service;

import com.projectsky.IT_ch_backend.dto.CreateHomeworkRequest;
import com.projectsky.IT_ch_backend.dto.HomeworkDtoForStudent;
import com.projectsky.IT_ch_backend.dto.HomeworkDtoForTeacher;
import com.projectsky.IT_ch_backend.enums.Role;
import com.projectsky.IT_ch_backend.mapper.HomeworkMapper;
import com.projectsky.IT_ch_backend.model.Course;
import com.projectsky.IT_ch_backend.model.CourseUser;
import com.projectsky.IT_ch_backend.model.Homework;
import com.projectsky.IT_ch_backend.repository.CourseRepository;
import com.projectsky.IT_ch_backend.repository.CourseUserRepository;
import com.projectsky.IT_ch_backend.repository.HomeworkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HomeworkServiceImpl implements HomeworkService {

    private final HomeworkRepository homeworkRepository;
    private final CourseUserRepository courseUserRepository;
    private final HomeworkMapper homeworkMapper;
    private final CourseRepository courseRepository;

    public HomeworkServiceImpl(HomeworkRepository homeworkRepository,
                               CourseUserRepository courseUserRepository,
                               HomeworkMapper homeworkMapper,
                               CourseRepository courseRepository) {
        this.homeworkRepository = homeworkRepository;
        this.courseUserRepository = courseUserRepository;
        this.homeworkMapper = homeworkMapper;
        this.courseRepository = courseRepository;
    }


    @Override
    public List<HomeworkDtoForStudent> getHomeworksForStudent(Long courseId) {
        return homeworkRepository.findByCourse_Id(courseId).stream()
                .map(homeworkMapper::toStudentDto)
                .toList();
    }

    @Override
    public List<HomeworkDtoForTeacher> getHomeworksForTeacher(Long courseId) {
        return homeworkRepository.findByCourse_Id(courseId).stream()
                .map(homeworkMapper::toTeacherDto)
                .toList();
    }

    @Override
    @Transactional
    public void addHomework(Long courseId, CreateHomeworkRequest request) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Homework homework = homeworkMapper.toEntity(request, course);

        homeworkRepository.save(homework);
    }
}
