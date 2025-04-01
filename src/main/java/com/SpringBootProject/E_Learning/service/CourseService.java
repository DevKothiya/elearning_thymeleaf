package com.SpringBootProject.E_Learning.service;

import com.SpringBootProject.E_Learning.model.Course;
import com.SpringBootProject.E_Learning.model.User;
import org.springframework.ui.Model;

import java.util.List;

public interface CourseService  {

    List<Course> getAllCourseByInstructor(User user);

    void deleteCourse(String id);

    Course getCourse(String id);

    List<Course> getAllCourses();

    Course addCourse(Course course, Model model);
}
