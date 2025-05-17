package com.projectsky.IT_ch_backend.mapper;

import com.projectsky.IT_ch_backend.dto.CourseDto;
import com.projectsky.IT_ch_backend.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ScheduleMapper.class, UserMapper.class})
public interface CourseMapper {
    CourseDto toDto(Course course);
    Course toEntity(CourseDto courseDto);
}
