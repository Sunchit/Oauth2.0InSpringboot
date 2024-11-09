package com.example.springbootchallenge;

import com.example.springbootchallenge.service.Impl.WelcomeGreetingService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class AppConfig {

    @Bean
    @ConditionalOnProperty(name = "welcome.greeting.enabled",
            havingValue = "true")
    public WelcomeGreetingService welcomeGreetingService() {
        return null;
    }


}

