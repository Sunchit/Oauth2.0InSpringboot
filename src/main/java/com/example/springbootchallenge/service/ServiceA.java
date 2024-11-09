package com.example.springbootchallenge.service;

import org.springframework.stereotype.Service;

@Service
public class ServiceA {
    private final ServiceB serviceB;
    public ServiceA(ServiceB serviceB) {
        this.serviceB = serviceB;
    }
    public void executeA() {
        System.out.println("ServiceA is executing...");
        serviceB.executeB();
    }
}

