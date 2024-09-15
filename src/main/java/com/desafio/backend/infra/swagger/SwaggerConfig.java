package com.desafio.backend.infra.swagger;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Api de gerência de estacionamentos.")
                        .description("API que busca realizar todo o controle de entrada e saída de veículos em estacionamentos e " +
                                "suas respectivas divisões.")
                        .version("v1")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")
                        ))
                .externalDocs(new ExternalDocumentation()
                        .description("LinkedIn")
                        .url("https://www.linkedin.com/in/joão-víctor-maia-4b9961265/"))
                .tags(Arrays.asList(
                        new Tag().name("Empresa").description("Operações disponíveis para Empresa."),
                        new Tag().name("Endereco").description("Operações disponívei para Endereço."),
                        new Tag().name("Relatorio").description("Operações disponívei para Relatório."),
                        new Tag().name("Veiculo").description("Operações disponívei para Veículo.")
                ));
    }
}
