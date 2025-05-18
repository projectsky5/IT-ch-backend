package com.projectsky.IT_ch_backend.mapper;

import com.projectsky.IT_ch_backend.dto.homework.CreateHomeworkRequest;
import com.projectsky.IT_ch_backend.dto.homework.HomeworkDtoForStudent;
import com.projectsky.IT_ch_backend.dto.homework.HomeworkDtoForTeacher;
import com.projectsky.IT_ch_backend.model.Course;
import com.projectsky.IT_ch_backend.model.Homework;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface HomeworkMapper {

    HomeworkDtoForStudent toStudentDto(Homework homework);
    HomeworkDtoForTeacher toTeacherDto(Homework homework);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "course", ignore = true)
    Homework toEntity(CreateHomeworkRequest request, @Context Course course);

    @AfterMapping
    default void setCourse(@MappingTarget Homework homework, @Context Course course) {
        homework.setCourse(course);
    }
}
