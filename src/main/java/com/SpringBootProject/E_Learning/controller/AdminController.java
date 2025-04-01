package com.SpringBootProject.E_Learning.controller;

import com.SpringBootProject.E_Learning.model.User;
import com.SpringBootProject.E_Learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/instructors/addInstructor")
    public String addInstructor(Model model){
        model.addAttribute("user",new User());
        return "/admin/instructorForm";

    }
    @PostMapping("/instructors/addInstructor")
    public String addingInstructor(User user){
        userService.saveInstructor(user);
        return "redirect:/admin/instructors";
    }

    @GetMapping("/instructors")
    public String allInstructor(Model model){
        List<User> users=userService.getInstructors();

        model.addAttribute("instructors",users);
        return "/admin/instructors";
    }

    @GetMapping("/instructors/update/{id}")
    public String updateInstructor(@PathVariable("id") String id,Model model){
        model.addAttribute("user",userService.getUserById(id));
        return "/admin/instructorForm";
    }

    @GetMapping("/instructors/delete/{id}")
    public String deleteInstructor(@PathVariable("id") String id){
        userService.deleteUser(id);
        return "redirect:/admin/instructors";
    }


}
