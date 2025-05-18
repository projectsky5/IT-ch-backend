package com.projectsky.IT_ch_backend.service;

import com.projectsky.IT_ch_backend.dto.homework.CreateHomeworkRequest;
import com.projectsky.IT_ch_backend.dto.homework.HomeworkDtoForStudent;
import com.projectsky.IT_ch_backend.dto.homework.HomeworkDtoForTeacher;
import com.projectsky.IT_ch_backend.dto.homework.PatchHomeworkRequest;
import org.springframework.stereotype.Service;

import java.util.List;

public interface HomeworkService {

    List<HomeworkDtoForStudent> getHomeworksForStudent(Long courseId);
    List<HomeworkDtoForTeacher> getHomeworksForTeacher(Long courseId);

    void addHomework(Long courseId, CreateHomeworkRequest request);

    void deleteHomework(Long homeworkId);

    HomeworkDtoForTeacher updateHomework(Long homeworkId, PatchHomeworkRequest request);
}
