package com.projectsky.IT_ch_backend.service;

import com.projectsky.IT_ch_backend.dto.schedule.ScheduleDayDto;
import com.projectsky.IT_ch_backend.dto.schedule.ScheduleDto;
import com.projectsky.IT_ch_backend.dto.schedule.SchedulePatchDto;

import java.util.List;

public interface ScheduleService {

    ScheduleDto updateSchedule(Long courseId, SchedulePatchDto schedulePatchDto);

    List<ScheduleDayDto> getScheduleForUser(Long userId);
}
