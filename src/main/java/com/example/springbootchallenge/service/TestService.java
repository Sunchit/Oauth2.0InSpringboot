package com.example.springbootchallenge.service;

import com.example.springbootchallenge.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class TestService {
    private static final Logger logger = Logger.getLogger(TestService.class.getName());
    private final ExternalService externalService;

    @Autowired
    private AppProperties appProperties;

    public String getDBUrl() {
        logger.info("DB url is " + appProperties.getDatasource().getUrl());
        return appProperties.getDatasource().getUrl();
    }
    @Autowired
    public TestService(ExternalService externalService) {
        this.externalService = externalService;
    }


}

