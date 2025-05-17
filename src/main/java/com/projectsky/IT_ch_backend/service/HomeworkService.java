package com.projectsky.IT_ch_backend.service;

import com.projectsky.IT_ch_backend.dto.CreateHomeworkRequest;
import com.projectsky.IT_ch_backend.dto.HomeworkDtoForStudent;
import com.projectsky.IT_ch_backend.dto.HomeworkDtoForTeacher;

import java.util.List;

public interface HomeworkService {

    List<HomeworkDtoForStudent> getHomeworksForStudent(Long courseId);
    List<HomeworkDtoForTeacher> getHomeworksForTeacher(Long courseId);

    void addHomework(Long courseId, CreateHomeworkRequest request);
}
