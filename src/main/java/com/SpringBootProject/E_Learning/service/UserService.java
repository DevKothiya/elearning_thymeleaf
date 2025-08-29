package com.SpringBootProject.E_Learning.service;

import com.SpringBootProject.E_Learning.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    User saveInstructor(User user);

    User getUserByEmail(String email);

    List<User> getInstructors();

    User getUserById(String id);

    User getLoggedInUser();

    void deleteUser(String id);
}
