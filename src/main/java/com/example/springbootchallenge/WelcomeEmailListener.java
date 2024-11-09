package com.example.springbootchallenge;

import com.example.springbootchallenge.rest.dto.UserRegisteredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class WelcomeEmailListener {

    @EventListener
    public void handleUserRegisteredEvent(UserRegisteredEvent event) {
        System.out.println("Sending welcome " +
                "email to user: " + event.getUserId());
        // Logic to send welcome email
    }
}