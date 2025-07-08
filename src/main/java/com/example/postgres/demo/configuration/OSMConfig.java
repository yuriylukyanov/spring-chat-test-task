package com.example.postgres.demo.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("open-street-map")
@Data
public class OSMConfig {
    private String basicUrl;
}
