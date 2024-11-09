package com.example.springbootchallenge;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    // In-memory database configuration for the 'dev' profile
    @Bean
    @Profile({"dev", "local"})
    public DataSource devDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:devdb;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    // MySQL or other external database configuration for 'prod' profile
    @Bean
    @Profile({"prod", "stage"})
    public DataSource prodAndStageDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:/<domain>/productiondb");
        dataSource.setUsername("root");
        dataSource.setPassword("password");
        return dataSource;
    }
}
