package com.projectsky.IT_ch_backend.model;

import com.projectsky.IT_ch_backend.enums.Completion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "homeworks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    private String title;
    private LocalDate deadline;

    private String refToSolution;
    private String refToSubmitFrom;
    private String refToSolutionView;

}

