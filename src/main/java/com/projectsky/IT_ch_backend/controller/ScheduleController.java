package com.projectsky.IT_ch_backend.controller;

import com.projectsky.IT_ch_backend.dto.schedule.ScheduleDayDto;
import com.projectsky.IT_ch_backend.dto.schedule.ScheduleDto;
import com.projectsky.IT_ch_backend.dto.schedule.SchedulePatchDto;
import com.projectsky.IT_ch_backend.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/{userId}/user")
    public ResponseEntity<List<ScheduleDayDto>> getUserSchedule(
            @PathVariable Long userId
    ) {
        List<ScheduleDayDto> schedule = scheduleService.getScheduleForUser(userId);
        return ResponseEntity.ok(schedule);
    }

    @PatchMapping("/{courseId}/course/patch")
    public ResponseEntity<ScheduleDto> updateSchedule(@PathVariable Long courseId,
                                                      @RequestBody SchedulePatchDto request) {
        ScheduleDto updatedSchedule = scheduleService.updateSchedule(courseId, request);
        return ResponseEntity.ok(updatedSchedule);
    }

}
