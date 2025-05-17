package com.projectsky.IT_ch_backend.repository;

import com.projectsky.IT_ch_backend.model.CourseUser;
import com.projectsky.IT_ch_backend.model.CourseUserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseUserRepository extends JpaRepository<CourseUser, CourseUserId> {
    List<CourseUser> findByCourse_Id(Long courseId);

    List<CourseUser> findByUserId(Long userId);

    Optional<CourseUser> findByCourse_IdAndUser_id(Long courseId, Long userId);

}
