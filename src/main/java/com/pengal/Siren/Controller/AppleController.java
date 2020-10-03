package com.pengal.Siren.Controller;

import com.pengal.Siren.Repositories.AppleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import static com.pengal.Siren.Repositories.AppleRepository.ITUNES_URL;

@RestController
@RequestMapping("/apple/")
public class AppleController {

    @Autowired AppleRepository appleRepository;

    @GetMapping("top100")
    public String top100() {
        return "{\"TODO\"}";
    }

    @GetMapping("search")
    public String search(@RequestParam String query) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        HttpEntity<?> httpEntity = new HttpEntity<Class<?>>(requestHeaders);

        String url = ITUNES_URL + "term=" + query;
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        return response.toString();
    }

    @PostMapping("podcast")
    public String createPodcast(@RequestBody Integer podcastId) {
        return "DONE";
    }
}
