package com.SpringBootProject.E_Learning.service;

import com.SpringBootProject.E_Learning.model.Course;

public interface EmailService {

     void sendEmailForEnrollingInCourse(String to, Course course);
}
