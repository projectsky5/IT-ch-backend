package com.projectsky.IT_ch_backend.mapper.course;

import com.projectsky.IT_ch_backend.dto.course.CourseOnlyDto;
import com.projectsky.IT_ch_backend.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseOnlyMapper {
    CourseOnlyDto toDto(Course course);
}
