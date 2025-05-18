package com.projectsky.IT_ch_backend.mapper.course;

import com.projectsky.IT_ch_backend.dto.course.CoursePatchRequest;
import com.projectsky.IT_ch_backend.model.Course;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CoursePatchMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchCourse(@MappingTarget Course course, CoursePatchRequest request);
}
