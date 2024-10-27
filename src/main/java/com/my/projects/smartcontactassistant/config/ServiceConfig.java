package com.my.projects.smartcontactassistant.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ServiceConfig {

    @Value("${openai.api.key}")
    private String openaiApiKey;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
