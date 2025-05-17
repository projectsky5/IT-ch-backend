package com.projectsky.IT_ch_backend.model;

import com.projectsky.IT_ch_backend.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "course_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseUser {

    @EmbeddedId
    private CourseUserId id = new CourseUserId();

    @ManyToOne
    @MapsId("courseId")
    private Course course;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @Enumerated(EnumType.STRING)
    private Role courseRole;
}
