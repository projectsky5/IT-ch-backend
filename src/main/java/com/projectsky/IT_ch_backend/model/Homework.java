package com.projectsky.IT_ch_backend.model;

import com.projectsky.IT_ch_backend.enums.Completion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "homeworks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Homework {

    @Id
    private Long id;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "course_id")
    private Course course;

    private String title;
    private LocalDate deadline;
    private String refToSolution;

    @OneToMany(mappedBy = "homework", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Solution> solutions;

    private Completion completion; // enumType

}

