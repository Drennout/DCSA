package com.example.demo.client;

import com.example.demo.model.UserDTO;
import io.rsocket.SocketAcceptor;
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
import java.util.List;

@RestController
@RequestMapping("/rest")
public class ClientController {
    private final RSocketRequester rSocketRequester;

    @Autowired
    public ClientController(RSocketRequester.Builder builder,
                                @Qualifier("rSocketStrategies") RSocketStrategies strategies) {
        SocketAcceptor responder = RSocketMessageHandler.responder(strategies, new ClientHandler());

        this.rSocketRequester = builder
                .setupRoute("shell-client")
                .dataMimeType(MimeTypeUtils.APPLICATION_JSON)
                .tcp("localhost", 7000);

    }

    @PostMapping("/add")
    public String reqResp(@RequestBody UserDTO userDTO) {

        return rSocketRequester
                .route("add")
                .data(userDTO)
                .retrieveMono(String.class)
                .block();
    }

    @GetMapping("/delete")
    public String fireAndForget(@RequestParam Long id) {
        rSocketRequester
                .route("delete")
                .data(id)
                .send()
                .block();
        return """
                {
                    "status":"success"
                }
                """;
    }

    @GetMapping("/all")
    public List<UserDTO> requestStream() {
        return rSocketRequester
                .route("all")
                .retrieveFlux(UserDTO.class)
                .collectList()
                .block();
    }
    @GetMapping("/user/id/{id}")
    public String getById(@PathVariable Long id) {
        return rSocketRequester
                .route("getById")
                .data(id)
                .retrieveMono(String.class)
                .block();
    }
    @GetMapping("/user/age/{age}")
    public List<UserDTO> getByAge(@PathVariable Integer age) {
        return rSocketRequester
                .route("getByAge")
                .data(age)
                .retrieveFlux(UserDTO.class)
                .collectList()
                .block();
    }
}

class ClientHandler {

    @MessageMapping("/client-status")
    public Flux<String> statusUpdate(String status) {
        return Flux.interval(Duration.ofSeconds(5)).map(index -> String.valueOf(Runtime.getRuntime().freeMemory()));
    }
}