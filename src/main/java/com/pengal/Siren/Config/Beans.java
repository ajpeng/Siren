package com.pengal.Siren.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import java.nio.charset.StandardCharsets;

@Configuration
public class Beans {
    @Autowired
    Config config;

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

//    @Bean
//    @Primary
//    RestTemplate restTemplate() {
//        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
//        httpRequestFactory.setConnectionRequestTimeout(10000);
//        httpRequestFactory.setReadTimeout(10000);
//
//        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
//        //TODO ADD INTERCEPTOR
//        return restTemplate;
//    }

    @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }



    @Bean
    Logger logger() {
        return LoggerFactory.getLogger(Beans.class);
    }

}
