package com.SpringBootProject.E_Learning.model;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    private String id= UUID.randomUUID().toString();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="course_id",nullable = false)
    private Course course;

    private String title;

    private String content;

    private long duration;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

}
