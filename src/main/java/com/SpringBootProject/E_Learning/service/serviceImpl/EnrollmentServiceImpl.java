package com.SpringBootProject.E_Learning.service.serviceImpl;

import com.SpringBootProject.E_Learning.model.Course;
import com.SpringBootProject.E_Learning.model.Enrollment;
import com.SpringBootProject.E_Learning.model.User;
import com.SpringBootProject.E_Learning.repository.EnrollmentRepository;
import com.SpringBootProject.E_Learning.service.CourseService;
import com.SpringBootProject.E_Learning.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private CourseService courseService;

    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Override
    public void enroll(String courseId, Model model) {
        User user=(User) model.getAttribute("loggedInUser");
        List<Course> courses=getCourseByStudent(user);
        for(Course c:courses){
            if(c.getId().equals(courseId)){
                return;
            }
        }
        Enrollment enrollment=new Enrollment();
        enrollment.setId(UUID.randomUUID().toString());
        enrollment.setCourse(courseService.getCourse(courseId));
        enrollment.setUser((User) model.getAttribute("loggedInUser"));
        enrollmentRepository.save(enrollment);
    }

    @Override
    public List<Course> getCourseByStudent(User user) {
        List<Course> courses=new ArrayList<>();
        for(Enrollment e:enrollmentRepository.findByUser(user)){
            courses.add(e.getCourse());
        }
        return courses;
    }
}
