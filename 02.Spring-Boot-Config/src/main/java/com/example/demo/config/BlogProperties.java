package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "mrbird.blog")
public class BlogProperties {
    private String name;
    private String title;
    private String wholeTitle;
}
