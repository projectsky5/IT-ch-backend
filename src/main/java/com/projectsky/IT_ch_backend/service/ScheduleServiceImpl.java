package com.projectsky.IT_ch_backend.service;

import com.projectsky.IT_ch_backend.dto.schedule.ScheduleDto;
import com.projectsky.IT_ch_backend.dto.schedule.SchedulePatchDto;
import com.projectsky.IT_ch_backend.mapper.schedule.ScheduleMapper;
import com.projectsky.IT_ch_backend.mapper.schedule.SchedulePatchMapper;
import com.projectsky.IT_ch_backend.model.Course;
import com.projectsky.IT_ch_backend.model.Schedule;
import com.projectsky.IT_ch_backend.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final CourseRepository courseRepository;
    private final SchedulePatchMapper schedulePatchMapper;
    private final ScheduleMapper scheduleMapper;

    public ScheduleServiceImpl(CourseRepository courseRepository, SchedulePatchMapper schedulePatchMapper, ScheduleMapper scheduleMapper) {
        this.courseRepository = courseRepository;
        this.schedulePatchMapper = schedulePatchMapper;
        this.scheduleMapper = scheduleMapper;
    }

    @Override
    @Transactional
    public ScheduleDto updateSchedule(Long courseId, SchedulePatchDto request) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Schedule schedule = course.getSchedule();
        schedulePatchMapper.patch(schedule, request);

        return scheduleMapper.toDto(schedule);
    }
}
