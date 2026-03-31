package com.sambie.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import serpapi.SerpApi;

@Configuration
public class SerpApiConfig {
    @Bean
    public SerpApi serpApi(@Value("${serpapi.api-key}") String apiKey) {
        Map<String, String> auth = new HashMap<>();
        auth.put("api_key", apiKey);

        return new SerpApi(auth);
    }
}
