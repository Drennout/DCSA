package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.UserDTO;
import com.example.demo.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class RsocketController {
    @Autowired
    private UserRepository repository;

    //request response
    @MessageMapping("add")
    Mono<String> book(final UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setAge(userDTO.getAge());
        repository.save(user);
        return Mono.just("success");
    }

    //fire and forget
    @MessageMapping("delete")
    public Mono<Void> deleteReservation(final Long id) {
        repository.delete(repository.findById(id).get());
        return Mono.empty();
    }

    //request stream
    @MessageMapping("all")
    Flux<UserDTO> getAllFlights() {
        return Flux
                .fromIterable(repository.findAll())
                .map(u -> new UserDTO(u.getId(), u.getUsername(), u.getPassword(), u.getAge()));
    }

    @MessageMapping("getById")
    Mono<UserDTO> getById(final Long id) {
        return Mono.just(repository.findById(id).get())
                .map(u -> new UserDTO(u.getId(), u.getUsername(), u.getPassword(), u.getAge()));
    }

    @MessageMapping("getByAge")
    Flux<UserDTO> getByAge(final Integer age) {
        return Flux
                .fromIterable(repository.findAll().stream().filter(u -> u.getAge() >= age).
                        map(u -> new UserDTO(u.getId(), u.getUsername(), u.getPassword(), u.getAge())).toList());
    }


    //chanel
//    @MessageMapping("channel")
//    Flux<TicketDTO> getTickets(final Flux<Integer> fluxFlightsId) {
//        Flux<Integer> loggedFluxFlightsId = fluxFlightsId
//                .doOnCancel(() -> log.warn("The client cancelled the channel."))
//                .doOnNext(flightId -> log.info("Channel flightId: {}", flightId));
//
//        return rSocketService.getTickets(loggedFluxFlightsId);
//    }
}
