package com.example.springbootchallenge;

import com.example.springbootchallenge.service.Impl.WelcomeGreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;


@EnableCaching
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SpringbootchallengeApplication implements CommandLineRunner {

	@Autowired
	private CacheManager cacheManager;

	@Autowired
	private ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootchallengeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
