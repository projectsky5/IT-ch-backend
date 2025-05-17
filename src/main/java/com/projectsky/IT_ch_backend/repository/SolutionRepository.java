package com.projectsky.IT_ch_backend.repository;

import com.projectsky.IT_ch_backend.model.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolutionRepository extends JpaRepository<Solution, Long> {
}

