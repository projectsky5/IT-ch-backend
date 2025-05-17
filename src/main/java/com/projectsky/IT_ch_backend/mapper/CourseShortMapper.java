package com.projectsky.IT_ch_backend.mapper;

import com.projectsky.IT_ch_backend.dto.CourseShortDto;
import com.projectsky.IT_ch_backend.model.CourseUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseShortMapper {

    @Mapping(source = "course.id", target = "id")
    @Mapping(source = "course.courseName", target = "courseName")
    @Mapping(source = "course.creator.fullName", target = "teacherName")
    @Mapping(source = "courseRole", target = "courseRole")
    CourseShortDto toDto(CourseUser courseUser);
}
