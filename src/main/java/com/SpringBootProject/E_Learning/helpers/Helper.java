package com.SpringBootProject.E_Learning.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class Helper {

    public static String getEmailOfLoggedInUser(Authentication authentication){
        if(authentication instanceof OAuth2AuthenticationToken){
            OAuth2AuthenticationToken oAuth2AuthenticationToken=(OAuth2AuthenticationToken) authentication;
            String clientId=oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
            OAuth2User oAuth2User=(OAuth2User) authentication.getPrincipal();
            if(clientId.equalsIgnoreCase("Google")){
                return oAuth2User.getAttribute("email");
            }
            if(clientId.equalsIgnoreCase("Github")){
                return oAuth2User.getAttribute("email")!=null? oAuth2User.getAttribute("email")
                        : oAuth2User.getAttribute("login")+"@gmail.com";

            }




        }
        return authentication.getName();
    }
}
