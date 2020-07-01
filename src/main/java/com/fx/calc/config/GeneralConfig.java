package com.fx.calc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;


/**
 * This helps us make external API requests
 *
 * @author efe ariaroo
 */
@Configuration
public class GeneralConfig {
    @Bean
    @Primary
    public RestTemplate restTesmplate() {
        return new RestTemplate();
    }
}
