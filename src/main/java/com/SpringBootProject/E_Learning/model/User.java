package com.SpringBootProject.E_Learning.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity(name="user")
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    private String id;

    private String name;
    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime createdAt=LocalDateTime.now();

    private LocalDateTime updatedAt=LocalDateTime.now();

    @PreUpdate
    public void setUpdatedAt(){
        this.updatedAt=LocalDateTime.now();
    }

    @OneToMany(mappedBy = "user")
    private List<Enrollment> enrollments=new ArrayList<>();

    @OneToMany(mappedBy = "instructor")
    private List<Course> createdCourses=new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private Provider provider;

    private String providerUserId;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<String > roleList=new ArrayList<>();
        roleList.add(role.name());
        return roleList.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername(){
        return  this.email;
    }
    @Override
    public String getPassword(){
        return  this.password;
    }
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}

