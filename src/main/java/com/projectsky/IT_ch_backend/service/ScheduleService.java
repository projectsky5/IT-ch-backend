package com.projectsky.IT_ch_backend.service;

import com.projectsky.IT_ch_backend.dto.schedule.ScheduleDto;
import com.projectsky.IT_ch_backend.dto.schedule.SchedulePatchDto;

public interface ScheduleService {

    ScheduleDto updateSchedule(Long courseId, SchedulePatchDto schedulePatchDto);
}
