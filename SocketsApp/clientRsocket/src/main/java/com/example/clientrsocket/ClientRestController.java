package com.example.clientrsocket;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientRestController {

    @Autowired
    private RSocketRequester rSocketRequester;

    @GetMapping("/req-resp")
    public String reqResp(@RequestParam String author) {
        return rSocketRequester
                .route("request-response")
                .data(author)
                .retrieveMono(String.class)
                .block();
    }
}
