package com.SpringBootProject.E_Learning.controller;

import com.SpringBootProject.E_Learning.model.Course;
import com.SpringBootProject.E_Learning.model.User;
import com.SpringBootProject.E_Learning.service.CourseService;
import com.SpringBootProject.E_Learning.service.EmailService;
import com.SpringBootProject.E_Learning.service.EnrollmentService;

import com.SpringBootProject.E_Learning.service.PaymentService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private EmailService emailService;
    @GetMapping("/allCourses")
    public String viewCourse(Model model){
        model.addAttribute("courses",courseService.getAllCourses());
        return "/student/allCourses";
    }

    @PostMapping("/courses/viewDetails")
    public String viewDetailsOfCourse(@RequestParam("courseId") String courseId, Model model){
        Course course=courseService.getCourse(courseId);
        model.addAttribute("course",course);
        return "/student/courseDetails";

    }
    @GetMapping("/myCourses")
    public String viewMyCourse(Model model){
        List<Course> courses=enrollmentService.getCourseByStudent((User) model.getAttribute("loggedInUser"));
        System.out.println(courses);
        model.addAttribute("courses",courses);
        return "/student/myCourses";
    }
    @PostMapping("/courses/buy")
    public String enrollInCourse(@RequestParam("courseId") String courseId,Model model) throws StripeException {
        User user=(User) model.getAttribute("loggedInUser");
        Course course=courseService.getCourse(courseId);
        String payment=paymentService.checkoutSession(courseId,course.getPrice());


        return "redirect:"+payment;
    }

    @GetMapping("/payment/success")
    public String paymentSuccess(@RequestParam String courseId,Model model,RedirectAttributes redirectAttributes){
        enrollmentService.enroll(courseId,model);
        User user= (User) model.getAttribute("loggedInUser");
        Course course=courseService.getCourse(courseId);
        assert user != null;
        emailService.sendEmailForEnrollingInCourse(user.getEmail(), course);
        redirectAttributes.addFlashAttribute("message", "Payment successful! You are now enrolled in the course.");
        redirectAttributes.addFlashAttribute("alertType", "success");
        return "redirect:/student/courses/"+courseId+"/lessons";
    }

    @GetMapping("/payment/cancel")
    public  String paymentCancel(RedirectAttributes redirectAttributes){

        redirectAttributes.addFlashAttribute("message", "Payment was canceled. Please try again.");
        redirectAttributes.addFlashAttribute("alertType", "warning");
        return "redirect:/student/allCourses";
    }

    @GetMapping("/courses/{id}/lessons")
    public String learnLesson(@PathVariable("id") String id,Model model){

        model.addAttribute("course",courseService.getCourse(id));
        return "/student/courseLearning";
    }





}
