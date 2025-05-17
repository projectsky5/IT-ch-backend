package com.projectsky.IT_ch_backend.repository;

import com.projectsky.IT_ch_backend.model.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework, Long> {
}
