package com.SpringBootProject.E_Learning.service.serviceImpl;

import com.SpringBootProject.E_Learning.model.Provider;
import com.SpringBootProject.E_Learning.model.Role;
import com.SpringBootProject.E_Learning.model.User;
import com.SpringBootProject.E_Learning.repository.UserRepository;
import com.SpringBootProject.E_Learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        System.out.println("User id='"+user.getId()+"'");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setId(UUID.randomUUID().toString());
        user.setProvider(Provider.SELF);
        user.setRole(Role.ROLE_STUDENT);
        userRepository.save(user);
        return user;
    }
    @Override
    public User saveInstructor(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getId()==null) {
            user.setId(UUID.randomUUID().toString());
        }
        user.setRole(Role.ROLE_INSTRUCTOR);
        userRepository.save(user);
        return user;
    }

    @Override
    public User getUserByEmail(String email) {

        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public List<User> getInstructors() {
        return userRepository.findByRole(Role.ROLE_INSTRUCTOR);
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getLoggedInUser() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        return (User) authentication.getPrincipal();
    }

    @Override
    public void deleteUser(String id) {
         userRepository.deleteById(id);
    }
}
