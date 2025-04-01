package com.SpringBootProject.E_Learning.repository;

import com.SpringBootProject.E_Learning.model.Course;
import com.SpringBootProject.E_Learning.model.Enrollment;
import com.SpringBootProject.E_Learning.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,String> {
    List<Enrollment> findByUser(User user);
}
