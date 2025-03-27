package org.example.productservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Product Management Service API",
                version = "1.0.0"
        ),
        servers = {
                @Server(url = "http://localhost:8082", description = "Local Development Server")
        }
)
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi brandApi() {
        return GroupedOpenApi.builder()
                .group("Brands")
                .pathsToMatch("/api/brands/**")
                .build();
    }

    @Bean
    public GroupedOpenApi categoryApi() {
        return GroupedOpenApi.builder()
                .group("Categories")
                .pathsToMatch("/api/categories/**")
                .build();
    }

    @Bean
    public GroupedOpenApi productApi() {
        return GroupedOpenApi.builder()
                .group("Products")
                .pathsToMatch("/api/products/**")
                .build();
    }

    @Bean
    public GroupedOpenApi reviewApi() {
        return GroupedOpenApi.builder()
                .group("Reviews")
                .pathsToMatch("/api/reviews/**")
                .build();
    }

    @Bean
    public GroupedOpenApi dailySaleApi() {
        return GroupedOpenApi.builder()
                .group("Daily Sales")
                .pathsToMatch("/api/daily-sales/**")
                .build();
    }

    @Bean
    public GroupedOpenApi salesSummaryApi() {
        return GroupedOpenApi.builder()
                .group("Sales Summaries")
                .pathsToMatch("/api/sales-summary/**")
                .build();
    }
}
