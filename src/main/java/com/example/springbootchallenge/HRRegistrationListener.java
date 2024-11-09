package com.example.springbootchallenge;

import com.example.springbootchallenge.rest.dto.UserRegisteredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HRRegistrationListener {

    @EventListener
    public void handleUserRegisteredEvent(UserRegisteredEvent event) {
        System.out.println("Logging registration for user," +
                " please proceed with Background verification " + event.getUserId());
        // Logic to log user registration
    }
}