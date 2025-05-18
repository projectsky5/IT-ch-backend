package com.projectsky.IT_ch_backend.service;

import com.projectsky.IT_ch_backend.dto.deadline.DeadlineDto;

import java.util.List;

public interface DeadlineService {
    List<DeadlineDto> getDeadlinesForUser(Long userId);

    void updateCompletionStatus(Long userId, Long homeworkId, boolean completed);
}
