package com.jackpot.producer.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI producerOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Jackpot Producer API")
                        .description("API for sending bets to a Kafka topic")
                        .version("1.0.0"));
    }
}
