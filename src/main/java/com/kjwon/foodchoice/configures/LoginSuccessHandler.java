package com.kjwon.foodchoice.configures;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) {
        System.out.println("login success!");
    }
}