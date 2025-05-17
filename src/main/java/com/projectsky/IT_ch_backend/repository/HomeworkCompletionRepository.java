package com.projectsky.IT_ch_backend.repository;

import com.projectsky.IT_ch_backend.model.HomeworkCompletion;
import com.projectsky.IT_ch_backend.model.HomeworkCompletionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface HomeworkCompletionRepository extends JpaRepository<HomeworkCompletion, HomeworkCompletionId> {
    boolean existsByUser_IdAndHomework_Id(Long userId, Long homeworkId);
    Optional<HomeworkCompletion> findByUser_IdAndHomework_Id(Long userId, Long homeworkId);
    @Modifying
    @Transactional
    @Query("""
        UPDATE HomeworkCompletion hc
        SET hc.completed = :completed
        WHERE hc.user.id = :userId AND hc.homework.id = :homeworkId
    """)
    int updateCompletedStatus(
            @Param("userId") Long userId,
            @Param("homeworkId") Long homeworkId,
            @Param("completed") boolean completed
    );
}
