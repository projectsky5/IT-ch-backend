package com.projectsky.IT_ch_backend.mapper;

import com.projectsky.IT_ch_backend.dto.CourseCreateRequest;
import com.projectsky.IT_ch_backend.model.Course;
import com.projectsky.IT_ch_backend.model.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {ScheduleMapper.class})
public interface CourseCreateRequestMapper {

    @Mapping(source = "courseName", target = "courseName")
    @Mapping(target = "creator", ignore = true)
    @Mapping(source = "duration.start", target = "startModule")
    @Mapping(source = "duration.end", target = "endModule")
    @Mapping(source = "schedule", target = "schedule")
    Course toEntity(CourseCreateRequest request, @Context User creator);

    @AfterMapping
    default void setCreator(@MappingTarget Course course, @Context User creator){
        course.setCreator(creator);
    }
}
