package com.SpringBootProject.E_Learning.config;

import com.SpringBootProject.E_Learning.model.Provider;
import com.SpringBootProject.E_Learning.model.Role;
import com.SpringBootProject.E_Learning.model.User;
import com.SpringBootProject.E_Learning.repository.UserRepository;
import com.SpringBootProject.E_Learning.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {


    @Autowired
    private UserRepository userRepository;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String userEmail="";
        System.out.println("Authentication name: " + authentication.getName());
        System.out.println("Authorities: " + authentication.getAuthorities());
        if((authentication instanceof OAuth2AuthenticationToken oAuth2AuthenticationToken)) {

            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setPassword("{bcrypt}none");

            user.setRole(Role.ROLE_STUDENT);
            String authorizedClientRegistrationId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

            DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();


            if (authorizedClientRegistrationId.equalsIgnoreCase("Google")) {
                user.setEmail(oAuth2User.getAttribute("email"));

                user.setName(oAuth2User.getAttribute("name"));
                user.setProviderUserId(oAuth2User.getName());
                user.setProvider(Provider.GOOGLE);
            } else if (authorizedClientRegistrationId.equalsIgnoreCase("Github")) {
                String email = oAuth2User.getAttribute("email") != null ? oAuth2User.getAttribute("email")
                        : oAuth2User.getAttribute("login") + "@gmail.com";
                String name = oAuth2User.getAttribute("login");
                String providerUserId = oAuth2User.getName();

                user.setEmail(email);
                user.setName(name);
                user.setProviderUserId(providerUserId);
                user.setProvider(Provider.GITHUB);

            }

            userEmail=user.getEmail();
            User  user2=userRepository.findByEmail(userEmail).orElse(null);
            if(user2==null){
                userRepository.save(user);
                List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole().name()));
                Authentication newAuth = new UsernamePasswordAuthenticationToken(user, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(newAuth);
                new DefaultRedirectStrategy().sendRedirect(request,response,"/student/allCourses");
            }
            if (user2 != null) {
                List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user2.getRole().name()));
                Authentication newAuth = new UsernamePasswordAuthenticationToken(user2, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(newAuth);


                switch (user2.getRole().name()) {
                    case "ROLE_ADMIN" ->
                            new DefaultRedirectStrategy().sendRedirect(request, response, "/admin/instructors");
                    case "ROLE_INSTRUCTOR" ->
                            new DefaultRedirectStrategy().sendRedirect(request, response, "/instructor/courses");
                    case "ROLE_STUDENT" -> {

                        System.out.println("here");
                        new DefaultRedirectStrategy().sendRedirect(request, response, "/student/allCourses");
                    }
                }
            }
        }
        else if(authentication instanceof UsernamePasswordAuthenticationToken){
            userEmail= authentication.getName();
            User  user=userRepository.findByEmail(userEmail).orElse(null);
            assert user != null;
            if(user.getRole().name().equals("ROLE_ADMIN")){
                new DefaultRedirectStrategy().sendRedirect(request,response,"/admin/instructors");
            }
            else if(user.getRole().name().equals("ROLE_INSTRUCTOR")){
                new DefaultRedirectStrategy().sendRedirect(request,response,"/instructor/courses");
            }
            else{
                System.out.println("here");
                new DefaultRedirectStrategy().sendRedirect(request,response,"/student/allCourses");
            }
        }



    }

}
