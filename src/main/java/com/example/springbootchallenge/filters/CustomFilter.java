package com.example.springbootchallenge.filters;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;

public class CustomFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Filter initialization (optional)
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Pre-processing: Run logic before request hits Spring components
        System.out.println("Jakarta Filter is executing before Spring...");

        // Continue the filter chain (go to the next filter/interceptor/controller)
        chain.doFilter(request, response);

        // Post-processing: Run logic after Spring processes the request
        System.out.println("Jakarta Filter is executing after Spring...");
    }

    @Override
    public void destroy() {
        // Cleanup (optional)
    }
}