package com.example.springbootchallenge.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/api/test")
    public String testRateLimiting() {
        return "Request successful!";
    }
}