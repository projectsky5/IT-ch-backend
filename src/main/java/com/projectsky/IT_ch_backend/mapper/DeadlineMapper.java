package com.projectsky.IT_ch_backend.mapper;

import com.projectsky.IT_ch_backend.dto.DeadlineDto;
import com.projectsky.IT_ch_backend.model.Homework;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DeadlineMapper {

    @Mapping(source = "homework.id", target = "homeworkId")
    @Mapping(source = "homework.title", target = "title")
    @Mapping(source = "homework.deadline", target = "deadline")
    @Mapping(source = "homework.course.courseName", target = "courseName")
    @Mapping(source = "completed", target = "completed")
    DeadlineDto toDto(Homework homework, boolean completed);


}
