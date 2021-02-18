package dev.salex.resourceserver.controller.rest;

import dev.salex.resourceserver.dto.model.Joke;
import dev.salex.resourceserver.service.JokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("joke")
public class JokeController {
    @Autowired
    private JokeService jokeService;

    @GetMapping(value = "/random")
    public Mono<Joke> getJoke(){
        return jokeService.getRandomJoke();
    }
}
