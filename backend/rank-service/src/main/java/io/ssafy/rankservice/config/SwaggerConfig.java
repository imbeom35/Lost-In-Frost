//package io.ssafy.rankservice.config;
//
//
//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.info.Info;
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.security.SecurityRequirement;
//import io.swagger.v3.oas.models.security.SecurityScheme;
//import io.swagger.v3.oas.models.servers.Server;
//import lombok.RequiredArgsConstructor;
//import org.springdoc.core.models.GroupedOpenApi;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Arrays;
//
//
//@RequiredArgsConstructor
//@Configuration
//@OpenAPIDefinition(
//        info = @Info(title = "Lost in Frost API Document", description = "RANK SERVICE API 명세서", version = "v1")
//)
//public class SwaggerConfig {
//
//    @Bean
//    public GroupedOpenApi groupedOpenApi() {
//        return GroupedOpenApi.builder()
//                .group("RANK-SERVICE")
//                .pathsToMatch("/api/**")
//                .packagesToScan("io.ssafy").build();
//    }
//
//}
