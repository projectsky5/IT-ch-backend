package com.projectsky.IT_ch_backend.model;

import com.projectsky.IT_ch_backend.enums.Frequency;
import com.projectsky.IT_ch_backend.enums.Weekday;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "schedules")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    private Integer academicHours;

    @Enumerated(EnumType.STRING)
    private Weekday dayOfWeek;

    private LocalTime startTime;

    private LocalDate startDate;

}