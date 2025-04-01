package com.SpringBootProject.E_Learning.controller;


import com.SpringBootProject.E_Learning.model.Course;
import com.SpringBootProject.E_Learning.model.User;
import com.SpringBootProject.E_Learning.service.CourseService;
import com.SpringBootProject.E_Learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/instructor")
public class InstructorController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public String getCourses(Model model){
        List<Course> courses=courseService.getAllCourseByInstructor((User) model.getAttribute("loggedInUser"));
        model.addAttribute("courses",courses);
        return "/instructor/courses";
    }

    @GetMapping("/courses/addCourse")
    public String createCourse(Model model){
        model.addAttribute("course",new Course());
        return "/instructor/courseForm";
    }
    @PostMapping("/courses/addCourse")
    public String creatingCourse(@ModelAttribute Course course, Model model){
        courseService.addCourse(course,model);
        return "redirect:/instructor/courses";
    }

    @GetMapping("/courses/update/{id}")
    public String updateCourse(@PathVariable("id") String id,Model model){
        model.addAttribute("course",courseService.getCourse(id));
        return "instructor/updateCourse";
    }


    @GetMapping("/courses/delete/{id}")
    public String deleteCourses(@PathVariable("id") String id){
        courseService.deleteCourse(id);
        return "redirect:/instructor/courses";
    }
}
