package dev.salex.resourceserver.controller.rest;

import dev.salex.resourceserver.dto.model.Joke;
import dev.salex.resourceserver.service.JokeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Api(tags = {"Шутка"}, authorizations = { @Authorization(value="apiKey") })
@RestController
@RequestMapping("joke")
public class JokeController {
    @Autowired
    private JokeService jokeService;

    @ApiOperation(value = "${JokeController.getJoke}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Error: Unauthorized")})
    @GetMapping(value = "/random")
    public Mono<Joke> getJoke(){
        return jokeService.getRandomJoke();
    }
}
