package com.fx.calc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GeneralConfig {
    @Bean
    @Primary
    public RestTemplate restTesmplate() {
        return new RestTemplate();
    }
}
