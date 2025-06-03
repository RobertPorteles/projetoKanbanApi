package br.com.kanbanapi.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API Kanban - Controle de Tarefas")
                .description("Documentação da API do projeto Kanban desenvolvida com Spring Boot")
                .version("1.0.0")
                .contact(new Contact()
                    .name("Robert Porteles")));
    }
}
