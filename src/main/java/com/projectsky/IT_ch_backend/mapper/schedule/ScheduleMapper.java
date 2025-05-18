package com.projectsky.IT_ch_backend.mapper.schedule;

import com.projectsky.IT_ch_backend.dto.schedule.ScheduleDto;
import com.projectsky.IT_ch_backend.enums.Frequency;
import com.projectsky.IT_ch_backend.enums.Weekday;
import com.projectsky.IT_ch_backend.model.Schedule;
import org.mapstruct.Mapper;

import java.time.LocalTime;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    default ScheduleDto toDto(Schedule schedule){
        return new ScheduleDto(
                schedule.getDayOfWeek().getDisplay(),
                schedule.getAcademicHours(),
                schedule.getStartTime().toString(),
                schedule.getFrequency().getDisplay()
        );
    }
    default Schedule toEntity(ScheduleDto dto){
        return new Schedule(
                null,
                Frequency.fromDisplay(dto.frequency()),
                dto.academicHours(),
                Weekday.fromDisplay(dto.dayOfWeek()),
                LocalTime.parse(dto.startTime())
        );
    }
}
