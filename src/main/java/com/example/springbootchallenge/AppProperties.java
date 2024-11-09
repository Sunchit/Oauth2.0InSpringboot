package com.example.springbootchallenge;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties(prefix = "app.config")
@Validated
public class AppProperties {
    private DatasourceProperties datasource;
    private Integer threadPoolSize;

    // Getter and Setter

    @NotNull(message = "Thread pool size must not be null")
    @Min(value = 5, message = "Thread pool size must be at least 1")
    @Max(value = 100, message = "Thread pool size must not exceed 100")
    public Integer getThreadPoolSize() {
        return threadPoolSize;
    }

    public void setThreadPoolSize(Integer threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    // Getters and setters for name, version, timeout

    public static class DatasourceProperties {
        private String url;
        private String username;
        private String password;

        // Getters and setters

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public DatasourceProperties getDatasource() {
        return datasource;
    }

    public void setDatasource(DatasourceProperties datasource) {
        this.datasource = datasource;
    }
}
