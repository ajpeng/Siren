package com.pengal.Siren.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pengal.Siren.Exception.ResourceNotFoundException;
import com.pengal.Siren.Model.ApplePodcast;
import com.pengal.Siren.Model.ApplePodcastWrapper;
import com.pengal.Siren.Repositories.AppleRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.pengal.Siren.Repositories.AppleRepository.ITUNES_URL;

@RestController
@RequestMapping("/apple")
public class AppleController {

    @Autowired
    private AppleRepository appleRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private Logger logger;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/search")
    public String search(@RequestParam String query) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeaders = new HttpHeaders();
        HttpEntity<?> httpEntity = new HttpEntity<Class<?>>(requestHeaders);

        String url = ITUNES_URL + "term=" + query;
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        return response.toString();
    }

    @GetMapping(value="/async_news")
    public String asyncUpdateNews() {
        String url = ITUNES_URL + "term=" + "news";
        ApplePodcastWrapper response = webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(ApplePodcastWrapper.class).block();
        if (response != null) {
            return response.toString();
        } else {
            return null;
        }
    }

    @GetMapping(value="/getCollectionById/{id}")
    public Long getCollectionId(@PathVariable("id") Long id) throws JsonProcessingException, ResourceNotFoundException {
        String url = ITUNES_URL + "collectionId=" + id.toString();
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        HttpEntity<String> response = restTemplate.getForEntity(url, String.class);
        ApplePodcastWrapper applePodcastWrapper = objectMapper.readValue(response.getBody(), ApplePodcastWrapper.class);
        List<ApplePodcast> applePodcastList;
        if (applePodcastWrapper != null) {
            applePodcastList = applePodcastWrapper.getResults();
            appleRepository.saveAll(applePodcastList);
        } else {
            return null;
        }

        if (applePodcastList.size() > 0) {
            return applePodcastList.get(0).getId();
        } else {
            throw new ResourceNotFoundException("Could not find collectionId with " + id);
        }
    }

    @GetMapping(value="/getCollectionByName/{name}")
    public Long getCollectionByName(@PathVariable("name") String name) throws JsonProcessingException, ResourceNotFoundException {
        String url = ITUNES_URL + "collectionName=" + name;
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        HttpEntity<String> response = restTemplate.getForEntity(url, String.class);
        ApplePodcastWrapper applePodcastWrapper = objectMapper.readValue(response.getBody(), ApplePodcastWrapper.class);
        List<ApplePodcast> applePodcastList;

        if (applePodcastWrapper != null) {
            applePodcastList = applePodcastWrapper.getResults();
            appleRepository.saveAll(applePodcastList);
        } else {
            return null;
        }

        if (applePodcastList.size() > 0) {
            return applePodcastList.get(0).getId();
        } else {
            throw new ResourceNotFoundException("Could not find collectionName with " + name);
        }
    }

    @RequestMapping(value="/news")
    public String updateNews() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = ITUNES_URL + "term=" + "news";
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        HttpEntity<String> response = restTemplate.getForEntity(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        ApplePodcastWrapper applePodcastWrapper = objectMapper.readValue(response.getBody(), ApplePodcastWrapper.class);

        if (applePodcastWrapper != null) {
            logger.debug("updateNews found " + applePodcastWrapper.getResultCount() + " results");
            List<ApplePodcast> applePodcastList = applePodcastWrapper.getResults();
            appleRepository.saveAll(applePodcastList);
            return applePodcastWrapper.getResultCount() + "";
        } else {
            return null;
        }
    }

    @PostMapping("/podcast/save")
    public ApplePodcast createPodcast(@PathVariable(value = "id") Long podcastTrackId, @RequestBody ApplePodcast podcast) {
        return this.appleRepository.save(podcast);
    }

    @GetMapping("/podcast/{id}")
    public ResponseEntity<ApplePodcast> getPodcastDetails(@PathVariable(value = "id") Long podcastTrackId ) throws ResourceNotFoundException {
        ApplePodcast podcast = appleRepository.findById(podcastTrackId).orElseThrow(() ->
            new ResourceNotFoundException("Podcast not found for this id :: " + podcastTrackId)
        );
        return ResponseEntity.ok().body(podcast);
    }

    @PostMapping("/podcast")
    public String createPodcast(@RequestBody Integer podcastId) {
        return "DONE";
    }
}
