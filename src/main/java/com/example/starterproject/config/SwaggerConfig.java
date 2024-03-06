package com.example.starterproject.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI(){
        OpenAPI openAPI = new OpenAPI();

        Info info = new Info();
        info.setTitle("starter-project");
        info.setDescription("project for start");
        info.setVersion("v1");

        openAPI.info(info);
        return openAPI;
    }
}
