package com.example.springbootchallenge.service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class ExternalService {

    private int attempt = 1;

    @Retryable(
            value = {RuntimeException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000))
    public String fetchData() {
        // mock RuntimeException behavior
        System.out.println("Attempt: " + attempt);
        attempt++;
        if (attempt <= 2) {
            throw new RuntimeException("Service Unavailable");
        }
        return "Data from external service";
    }
}
