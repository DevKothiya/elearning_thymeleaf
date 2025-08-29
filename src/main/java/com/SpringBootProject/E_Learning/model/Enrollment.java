package com.SpringBootProject.E_Learning.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Enrollment {
    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="course_id",nullable = false)
    private Course course;

    private double progress=0.0;
    @Column(nullable = false)
    private LocalDateTime enrollmentDate = LocalDateTime.now();



}
