package com.projectsky.IT_ch_backend.controller;

import com.projectsky.IT_ch_backend.dto.deadline.DeadlineDto;
import com.projectsky.IT_ch_backend.dto.deadline.UpdateCompletionRequest;
import com.projectsky.IT_ch_backend.service.CurrentUserService;
import com.projectsky.IT_ch_backend.service.DeadlineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deadlines")
public class DeadlineController {

    private final DeadlineService deadlineService;
    private final CurrentUserService currentUserService;

    public DeadlineController(DeadlineService deadlineService,
                              CurrentUserService currentUserService) {
        this.deadlineService = deadlineService;
        this.currentUserService = currentUserService;
    }

    @GetMapping
    public ResponseEntity<List<DeadlineDto>> getAllDeadlines() {
        Long userId = currentUserService.getCurrentUser().getId();
        return ResponseEntity.ok(deadlineService.getDeadlinesForUser(userId));
    }

    @PatchMapping("/{homeworkId}")
    public ResponseEntity<Void> updateCompletionStatus(
                                                       @PathVariable Long homeworkId,
                                                       @RequestBody UpdateCompletionRequest request
    ) {
        Long userId = currentUserService.getCurrentUser().getId();
        deadlineService.updateCompletionStatus(userId, homeworkId, request.completed());
        return ResponseEntity.ok().build();
    }
}
