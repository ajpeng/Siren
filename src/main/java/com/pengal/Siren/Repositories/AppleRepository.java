package com.pengal.Siren.Repositories;

import com.pengal.Siren.Model.ApplePodcast;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public interface AppleRepository extends CrudRepository<ApplePodcast, Long> {
    final static String ITUNES_URL = "https://itunes.apple.com/search?";
}
