package com.sams.inventorymanagement.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        // Define the SecurityScheme for JWT
        SecurityScheme jwtSecurityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");

        // Create the OpenAPI object with the defined SecurityScheme
        return new OpenAPI()
                .components(new io.swagger.v3.oas.models.Components().addSecuritySchemes("JWT", jwtSecurityScheme))
                .info(new Info()
                        .title("Inventory Management Project OpenAPI Docs")
                        .version("1.0.0")
                        .description(""))
                .addServersItem(new Server().url("/"))
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList("JWT", "Authorization")
                                .addList("api/users")); // Apply JWT security only to the /api/users route
    }
}
