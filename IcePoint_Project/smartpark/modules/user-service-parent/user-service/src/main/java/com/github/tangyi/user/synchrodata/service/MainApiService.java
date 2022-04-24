package com.github.tangyi.user.synchrodata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public abstract class MainApiService {

    @Autowired
    protected RestTemplate restTemplate;

    protected String exchange(String uri, String json, String token, HttpMethod method) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<String>(json == null ? "parameters" : json, headers);

        ResponseEntity<String> result = restTemplate.exchange(uri, method, entity, String.class);
        return result.getBody();
    }
}
