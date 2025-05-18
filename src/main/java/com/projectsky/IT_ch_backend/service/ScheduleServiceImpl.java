package com.projectsky.IT_ch_backend.service;

import com.projectsky.IT_ch_backend.dto.schedule.LessonDto;
import com.projectsky.IT_ch_backend.dto.schedule.ScheduleDayDto;
import com.projectsky.IT_ch_backend.dto.schedule.ScheduleDto;
import com.projectsky.IT_ch_backend.dto.schedule.SchedulePatchDto;
import com.projectsky.IT_ch_backend.mapper.schedule.ScheduleMapper;
import com.projectsky.IT_ch_backend.mapper.schedule.SchedulePatchMapper;
import com.projectsky.IT_ch_backend.model.Course;
import com.projectsky.IT_ch_backend.model.CourseUser;
import com.projectsky.IT_ch_backend.model.Schedule;
import com.projectsky.IT_ch_backend.repository.CourseRepository;
import com.projectsky.IT_ch_backend.repository.CourseUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final CourseRepository courseRepository;
    private final SchedulePatchMapper schedulePatchMapper;
    private final ScheduleMapper scheduleMapper;
    private final CourseUserRepository courseUserRepository;

    public ScheduleServiceImpl(CourseRepository courseRepository, SchedulePatchMapper schedulePatchMapper, ScheduleMapper scheduleMapper, CourseUserRepository courseUserRepository) {
        this.courseRepository = courseRepository;
        this.schedulePatchMapper = schedulePatchMapper;
        this.scheduleMapper = scheduleMapper;
        this.courseUserRepository = courseUserRepository;
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

    @Override
    public List<ScheduleDayDto> getScheduleForUser(Long userId) {
        LocalDate endDate = LocalDate.now().plusWeeks(12);
        Map<String, List<LessonDto>> lessonsByDate = new TreeMap<>();

        List<CourseUser> courseUsers = courseUserRepository.findByUserId(userId);

        for(CourseUser courseUser : courseUsers) {
            Course course = courseUser.getCourse();
            Schedule schedule = course.getSchedule();

            if (schedule == null || schedule.getStartDate() == null) continue;

            LocalDate startDate = schedule.getStartDate();
            DayOfWeek lessonDay = DayOfWeek.valueOf(schedule.getDayOfWeek().name());
            LocalTime baseTime = schedule.getStartTime();
            int hours = schedule.getAcademicHours();
            Duration pairDuration = Duration.ofMinutes(80);
            Duration breakDuration = Duration.ofMinutes(10);

            while(!startDate.isAfter(endDate)){
                if(startDate.getDayOfWeek() == lessonDay){
                    for (int i = 0; i < hours; i++) {
                        LocalTime start = baseTime.plus(breakDuration.plus(pairDuration).multipliedBy(i));
                        LocalTime end = start.plus(pairDuration);

                        String key = startDate.toString();
                        lessonsByDate.computeIfAbsent(key, k -> new ArrayList<>())
                                .add(new LessonDto(
                                        course.getCourseName(),
                                        course.getLocation(),
                                        start.toString(),
                                        end.toString()
                                ));
                    }
                    startDate = switch(schedule.getFrequency()){
                        case ONCE_A_WEEK -> startDate.plusWeeks(1);
                        case ONCE_EVERY_TWO_WEEKS -> startDate.plusWeeks(2);
                    };
                } else {
                    startDate = startDate.plusDays(1);
                }
            }
        }

        return lessonsByDate.entrySet().stream()
                .map(e -> new ScheduleDayDto(e.getKey(), e.getValue()))
                .toList();

    }
}
