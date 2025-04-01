package com.SpringBootProject.E_Learning.repository;

import com.SpringBootProject.E_Learning.model.Role;
import com.SpringBootProject.E_Learning.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByEmail(String email);

    List<User> findByRole(Role role);
}
