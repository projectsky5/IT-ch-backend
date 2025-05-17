package com.projectsky.IT_ch_backend.repository;

import com.projectsky.IT_ch_backend.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
