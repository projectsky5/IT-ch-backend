package com.projectsky.IT_ch_backend.controller;

import com.projectsky.IT_ch_backend.dto.deadline.DeadlineDto;
import com.projectsky.IT_ch_backend.dto.deadline.UpdateCompletionRequest;
import com.projectsky.IT_ch_backend.service.DeadlineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class DeadlineController {

    private final DeadlineService deadlineService;

    public DeadlineController(DeadlineService deadlineService) {
        this.deadlineService = deadlineService;
    }

    @GetMapping("/{userId}/deadlines")
    public ResponseEntity<List<DeadlineDto>> getAllDeadlines(@PathVariable Long userId) {
        return ResponseEntity.ok(deadlineService.getDeadlinesForUser(userId));
    }

    @PatchMapping("/{userId}/deadlines/{homeworkId}")
    public ResponseEntity<Void> updateCompletionStatus(@PathVariable Long userId,
                                                       @PathVariable Long homeworkId,
                                                       @RequestBody UpdateCompletionRequest request) {
        deadlineService.updateCompletionStatus(userId, homeworkId, request.completed());
        return ResponseEntity.ok().build();
    }
}
