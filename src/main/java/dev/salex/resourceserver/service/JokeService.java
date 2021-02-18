package dev.salex.resourceserver.service;

import dev.salex.resourceserver.dto.response.JokeResponse;
import dev.salex.resourceserver.dto.model.Joke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class JokeService {

    @Autowired
    private WebClient webClient;

    public Mono<Joke> getRandomJoke(){
        Mono<Joke> employeeMono = webClient.get()
                .uri("/jokes/random").accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(JokeResponse.class)
                .map(JokeResponse::getValue);
        return employeeMono;
    }
}
