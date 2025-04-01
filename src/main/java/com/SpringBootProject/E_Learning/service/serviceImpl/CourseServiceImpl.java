package com.SpringBootProject.E_Learning.service.serviceImpl;

import com.SpringBootProject.E_Learning.model.Course;
import com.SpringBootProject.E_Learning.model.User;
import com.SpringBootProject.E_Learning.repository.CourseRepository;
import com.SpringBootProject.E_Learning.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getAllCourseByInstructor(User user) {
        return courseRepository.findByInstructor(user);
    }

    @Override
    public void deleteCourse(String id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Course getCourse(String id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public Course addCourse(Course course, Model model) {
        course.getLessons().forEach(lesson -> lesson.setCourse(course));
        System.out.println(" here "+ course.getId());
        if(course.getId()==null) {
            course.setId(UUID.randomUUID().toString());
        }
        course.setInstructor((User) model.getAttribute("loggedInUser"));
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }
}
