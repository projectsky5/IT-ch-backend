package com.projectsky.IT_ch_backend.service;

import com.projectsky.IT_ch_backend.dto.deadline.DeadlineDto;
import com.projectsky.IT_ch_backend.mapper.DeadlineMapper;
import com.projectsky.IT_ch_backend.model.CourseUser;
import com.projectsky.IT_ch_backend.model.Homework;
import com.projectsky.IT_ch_backend.model.HomeworkCompletion;
import com.projectsky.IT_ch_backend.model.User;
import com.projectsky.IT_ch_backend.repository.CourseUserRepository;
import com.projectsky.IT_ch_backend.repository.HomeworkCompletionRepository;
import com.projectsky.IT_ch_backend.repository.HomeworkRepository;
import com.projectsky.IT_ch_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeadlineServiceImpl implements DeadlineService {

    private final CourseUserRepository courseUserRepository;
    private final HomeworkCompletionRepository homeworkCompletionRepository;
    private final HomeworkRepository homeworkRepository;
    private final DeadlineMapper deadlineMapper;
    private final UserRepository userRepository;

    public DeadlineServiceImpl(CourseUserRepository courseUserRepository, HomeworkCompletionRepository homeworkCompletionRepository,
                               DeadlineMapper deadlineMapper,
                               UserRepository userRepository,
                               HomeworkRepository homeworkRepository) {
        this.courseUserRepository = courseUserRepository;
        this.homeworkCompletionRepository = homeworkCompletionRepository;
        this.deadlineMapper = deadlineMapper;
        this.userRepository = userRepository;
        this.homeworkRepository = homeworkRepository;
    }

    @Override
    public List<DeadlineDto> getDeadlinesForUser(Long userId) {
        List<CourseUser> courseUsers = courseUserRepository.findAllByUserId(userId);

        return courseUsers.stream()
                .flatMap(cu -> cu.getCourse().getHomeworks().stream())
                .map(homework -> {
                    boolean completed = homeworkCompletionRepository
                            .existsByUser_IdAndHomework_Id(userId, homework.getId());
                    return deadlineMapper.toDto(homework, completed);
                })
                .toList();
    }

    @Override
    public void updateCompletionStatus(Long userId, Long homeworkId, boolean completed) {
        int updated = homeworkCompletionRepository.updateCompletedStatus(userId, homeworkId, completed);

        if(updated == 0) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            Homework homework = homeworkRepository.findById(homeworkId)
                    .orElseThrow(() -> new RuntimeException("Homework not found"));

            HomeworkCompletion newEntry = new HomeworkCompletion(user, homework, completed);
            homeworkCompletionRepository.save(newEntry);
        }
    }
}
