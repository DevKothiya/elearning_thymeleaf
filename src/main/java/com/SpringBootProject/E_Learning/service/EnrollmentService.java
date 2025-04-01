package com.SpringBootProject.E_Learning.service;

import com.SpringBootProject.E_Learning.model.Course;
import com.SpringBootProject.E_Learning.model.User;
import org.springframework.ui.Model;

import java.util.List;

public interface EnrollmentService {
    void enroll(String id, Model model);

    List<Course> getCourseByStudent(User user);
}
