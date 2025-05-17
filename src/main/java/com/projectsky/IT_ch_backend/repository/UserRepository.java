package com.projectsky.IT_ch_backend.repository;

import com.projectsky.IT_ch_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
