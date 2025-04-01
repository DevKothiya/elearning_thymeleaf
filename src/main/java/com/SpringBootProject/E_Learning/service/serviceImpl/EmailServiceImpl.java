package com.SpringBootProject.E_Learning.service.serviceImpl;

import com.SpringBootProject.E_Learning.helpers.Helper;
import com.SpringBootProject.E_Learning.model.Course;
import com.SpringBootProject.E_Learning.model.User;
import com.SpringBootProject.E_Learning.service.EmailService;
import com.SpringBootProject.E_Learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    private UserService userService;

    @Override
    public void sendEmailForEnrollingInCourse(String to, Course course) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject("Successful enrollment in "+course.getTitle());
        User user= userService.getLoggedInUser();
        assert user != null;
        mailMessage.setText("Dear "+user.getName()+", You have successfully enrolled in course named "+course.getTitle()+" worth of "+course.getPrice());
        javaMailSender.send(mailMessage);
    }
}
