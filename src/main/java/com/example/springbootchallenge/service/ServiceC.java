package com.example.springbootchallenge.service;


import com.example.springbootchallenge.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ServiceC {
    private final ServiceA serviceA;

    @Autowired
    private AppProperties appProperties;

    public ServiceC(@Lazy ServiceA serviceA) {
        this.serviceA = serviceA;
    }
    public void executeC() {
        appProperties.getThreadPoolSize();
        System.out.println("ServiceC is executing...");
        serviceA.executeA();
    }
}

