package com.projectsky.IT_ch_backend.repository;

import com.projectsky.IT_ch_backend.model.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework, Long> {
    List<Homework> findByCourse_Id(Long courseId);
}
