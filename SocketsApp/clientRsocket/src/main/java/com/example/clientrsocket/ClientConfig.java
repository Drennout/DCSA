package com.example.clientrsocket;

import io.rsocket.SocketAcceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.codec.CharSequenceEncoder;
import org.springframework.core.codec.StringDecoder;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.messaging.rsocket.annotation.support.RSocketMessageHandler;
import org.springframework.util.MimeTypeUtils;
import reactor.util.retry.Retry;

import java.time.Duration;

@Configuration
public class ClientConfig {
//    @Bean
//    public RSocketStrategies rsocketStrategies() {
//        return RSocketStrategies.builder()
//                .decoder(StringDecoder.textPlainOnly())
//                .encoder(CharSequenceEncoder.allMimeTypes())
//                .dataBufferFactory(new DefaultDataBufferFactory(true))
//                .build();
//    }
//
//    @Bean
//    public RSocketRequester getRSocketRequester() {
//        RSocketRequester.Builder builder = RSocketRequester.builder();
//        SocketAcceptor responder = RSocketMessageHandler.responder(rsocketStrategies());
//
//        return builder
//                .rsocketConnector(connector -> connector.acceptor(responder))
//                .dataMimeType(MimeTypeUtils.APPLICATION_JSON)
//                .tcp("localhost", 7000);
//    }
}
