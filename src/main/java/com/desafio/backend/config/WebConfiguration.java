package com.desafio.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@Configuration
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class WebConfiguration{

//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configurer
//                .defaultContentType(MediaType.APPLICATION_JSON)  // Define JSON como o formato padrão
//                .mediaType("xml", MediaType.APPLICATION_XML)
//                .mediaType("json", MediaType.APPLICATION_JSON);
//    }
}
