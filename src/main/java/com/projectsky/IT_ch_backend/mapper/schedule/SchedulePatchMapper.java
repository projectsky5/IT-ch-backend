package com.projectsky.IT_ch_backend.mapper.schedule;

import com.projectsky.IT_ch_backend.dto.schedule.SchedulePatchDto;
import com.projectsky.IT_ch_backend.model.Schedule;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;



@Mapper(componentModel = "spring")
public interface SchedulePatchMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patch(@MappingTarget Schedule schedule, SchedulePatchDto dto);
}
