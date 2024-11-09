package com.example.springbootchallenge.service.Impl;

import com.example.springbootchallenge.service.intf.GreetingIntf;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;



@Service
@Profile("local")
public class WelcomeGreetingService implements GreetingIntf {

    public WelcomeGreetingService(){
        System.out.println("Creating bean for Welcome Greeting Service");
    }
    @Override
    public String greet() {
        return "Hello SpringBoot!";
    }
}
