package com.projectsky.IT_ch_backend.mapper.course;

import com.projectsky.IT_ch_backend.dto.course.CourseShortDto;
import com.projectsky.IT_ch_backend.dto.course.DurationRange;
import com.projectsky.IT_ch_backend.model.Course;
import com.projectsky.IT_ch_backend.model.CourseUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseShortMapper {

    @Mapping(source = "course.id", target = "id")
    @Mapping(source = "course.courseName", target = "courseName")
    @Mapping(source = "course.creator.fullName", target = "teacherName")
    @Mapping(source = "courseRole", target = "courseRole")
    @Mapping(target = "duration", expression = "java(toDurationRange(courseUser.getCourse()))")
    CourseShortDto toDto(CourseUser courseUser);

    default DurationRange toDurationRange(Course course) {
        return new DurationRange(course.getStartModule(), course.getEndModule());
    }
}
