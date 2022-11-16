package com.example.clientrsocket;

import io.rsocket.SocketAcceptor;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.messaging.rsocket.annotation.support.RSocketMessageHandler;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class ClientRestController {
    private final RSocketRequester rSocketRequester;

    @Autowired
    public ClientRestController(RSocketRequester.Builder builder,
                              @Qualifier("rSocketStrategies") RSocketStrategies strategies) {
        SocketAcceptor responder = RSocketMessageHandler.responder(strategies, new ClientHandler());

        this.rSocketRequester = builder
                .setupRoute("shell-client")
                .dataMimeType(MimeTypeUtils.APPLICATION_JSON)
                .tcp("localhost", 7000);

    }

    @GetMapping("/request-response")
    public String reqResp(@RequestParam String author) {
        return rSocketRequester
                .route("request-response")
                .data(author)
                .retrieveMono(String.class)
                .block();
    }

// {
//     "id": "5234132",
//     "cat": ["book", "hardcover"],
//     "name": "SDFGSDFGDSFDSf",
//     "author": "Author 1"
// }
    @PostMapping("fire-and-forget")
    public String fireAndForget(@RequestBody BookPayload bookPayload) {
        rSocketRequester
                .route("fire-and-forget")
                .data(new BookPayload(bookPayload.getId(), bookPayload.getCat(), bookPayload.getName(), bookPayload.getAuthor()))
                .send()
                .block();
        return """
                {
                    "status":"ok"
                }
                """;
    }
}

class ClientHandler {

    @MessageMapping("client-status")
    public Flux<String> statusUpdate(String status) {
        return Flux.interval(Duration.ofSeconds(5)).map(index -> String.valueOf(Runtime.getRuntime().freeMemory()));
    }
}