package com.SpringBootProject.E_Learning.controller;

import com.SpringBootProject.E_Learning.model.User;
import com.SpringBootProject.E_Learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/")
    public String any(){
        return "redirect:/home";
    }
    @RequestMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/register")
    public  String register(Model model){
        model.addAttribute("user",new User());
        return "registration";
    }

    @PostMapping("/register")
    public String registering(User user){
        userService.saveUser(user);
        return "redirect:/login";
    }


}
