package com.pengal.Siren.Repositories;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class AppleRepository {
    final static String ITUNES_URL = "https://itunes.apple.com/search?";

    public String getAppleResponse(String params){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        HttpEntity<?> httpEntity = new HttpEntity<Class<?>>(requestHeaders);

        String url = ITUNES_URL + "term=" + params;
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        return response.toString();
    }
}
