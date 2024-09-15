package com.desafio.backend.infra.env;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Autowired
    private Dotenv dotenv;

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .username(dotenv.get("DB_USERNAME"))
                .password(dotenv.get("DB_PASSWORD"))
                .url(dotenv.get("DB_URL"))
                .build();
    }
}
