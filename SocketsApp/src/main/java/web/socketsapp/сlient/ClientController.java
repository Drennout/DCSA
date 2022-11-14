package web.socketsapp.сlient;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import web.socketsapp.server.controller.payload.FreeTablePayload;

@RestController
public class ClientController {

    @Autowired
    RSocketRequester rSocketRequester;

    @GetMapping("/freeTables")
    public Publisher<FreeTablePayload> freeTables() {
        return rSocketRequester
                .route("freeTables")
                .retrieveMono(FreeTablePayload.class);
    }
}
