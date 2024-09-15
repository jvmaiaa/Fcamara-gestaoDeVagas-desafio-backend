package com.desafio.backend.infra.env;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigVerifier {

    @Bean
    public Dotenv dotenv() {
        return Dotenv.configure()
                .filename(".env.properties")
                .load();
    }
}
