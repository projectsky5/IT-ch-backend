package com.projectsky.IT_ch_backend.controller;

import com.projectsky.IT_ch_backend.dto.schedule.ScheduleDayDto;
import com.projectsky.IT_ch_backend.dto.schedule.ScheduleDto;
import com.projectsky.IT_ch_backend.dto.schedule.SchedulePatchDto;
import com.projectsky.IT_ch_backend.service.CurrentUserService;
import com.projectsky.IT_ch_backend.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final CurrentUserService currentUserService;

    public ScheduleController(ScheduleService scheduleService,
                              CurrentUserService currentUserService) {
        this.scheduleService = scheduleService;
        this.currentUserService = currentUserService;
    }

    @GetMapping("/{userId}/user")
    public ResponseEntity<List<ScheduleDayDto>> getUserSchedule() {
        Long userId = currentUserService.getCurrentUser().getId();
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
