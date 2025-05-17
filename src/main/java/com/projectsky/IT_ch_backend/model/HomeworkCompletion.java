package com.projectsky.IT_ch_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "homework_completions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(HomeworkCompletionId.class)
public class HomeworkCompletion {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "homework_id")
    private Homework homework;

    private boolean completed;
}
