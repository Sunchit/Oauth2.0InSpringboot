package com.example.springbootchallenge.Interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle
            (HttpServletRequest request,
             HttpServletResponse response, Object handler) {
        System.out.println
                ("Incoming Request: " + request.getMethod() + " " + request.getRequestURI());
        return true; // Return true to allow the request to proceed
    }

    @Override
    public void postHandle
            (HttpServletRequest request, HttpServletResponse response,
             Object handler, ModelAndView modelAndView) {
        System.out.println("Request Processed");
    }

    @Override
    public void afterCompletion
            (HttpServletRequest request, HttpServletResponse response,
             Object handler, Exception ex) {
        System.out.println("Request Completed with Status: "
                + response.getStatus());
    }
}