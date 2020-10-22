package com.pengal.Siren.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*")
                        .allowedOrigins("http://localhost:3000");
            }
        };
    }
    String hostName;

    @Value("${DB_USER:#{environment.DB_USER}}") String databaseUser;
    @Value("${DB_PASS:#{environment.DB_PASS}}") String databasePass;
    @Value("${DB_USER:#{environment.DB_USER}}") String databaseHost;
    @Value("${DB_PORT:#{environment.DB_PORT}}") String databasePort;
    @Value("${DB_NAME:#{environment.DB_NAME}}") String databaseName;
    @Value("${DB_SCHEMA:#{environment.DB_SCHEMA}}") String databaseSchema;
}
