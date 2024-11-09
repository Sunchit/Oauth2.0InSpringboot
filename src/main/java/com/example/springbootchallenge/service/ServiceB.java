package com.example.springbootchallenge.service;


import org.springframework.stereotype.Service;

@Service
public class ServiceB {
    private final ServiceC serviceC;
    public ServiceB(ServiceC serviceC) {
        this.serviceC = serviceC;
    }
    public void executeB() {
        System.out.println("ServiceB is executing...");
        serviceC.executeC();
    }
}
