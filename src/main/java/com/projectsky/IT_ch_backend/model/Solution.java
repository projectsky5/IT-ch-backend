package com.projectsky.IT_ch_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "solutions")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Solution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "homework_id")
    private Homework homework;

    private String title;
    private String refToGoogleForm;

}
