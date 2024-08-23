package com.praveen.order_service.WEBCLIENT_CONFIG;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ConfigWebClient {
    @Bean
    public WebClient webclient(){
        return WebClient.builder().build();
    }
}
