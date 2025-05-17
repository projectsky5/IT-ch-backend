package com.projectsky.IT_ch_backend.model;

import com.projectsky.IT_ch_backend.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String imageUrl;

    private String email;
    private String password; //hash

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseUser> courseUsers;

    @Enumerated(EnumType.STRING)
    private Role role;
}
