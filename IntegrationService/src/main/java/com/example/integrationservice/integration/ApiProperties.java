package com.example.integrationservice.integration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "custom.api")
@Component
public class ApiProperties {
    private String url;
}