package com.SpringBootProject.E_Learning.repository;

import com.SpringBootProject.E_Learning.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson,String> {
}
