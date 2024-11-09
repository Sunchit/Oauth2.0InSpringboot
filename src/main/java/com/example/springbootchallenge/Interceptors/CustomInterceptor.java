package com.example.springbootchallenge.Interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CustomInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        // Pre-processing before reaching the controller
        System.out.println("Spring Interceptor: Pre-handle logic");
        return true;  // Continue to the controller
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // Post-processing after the controller logic
        System.out.println("Spring Interceptor: After-completion logic");
    }
}