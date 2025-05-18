package com.projectsky.IT_ch_backend.mapper;

import com.projectsky.IT_ch_backend.dto.homework.PatchHomeworkRequest;
import com.projectsky.IT_ch_backend.model.Homework;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface HomeworkPatchMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchHomework(@MappingTarget Homework homework, PatchHomeworkRequest request);
}
