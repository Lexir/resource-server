package dev.salex.resourceserver.dto.response;

import dev.salex.resourceserver.dto.model.Joke;

public class JokeResponse {
    private String type;
    private Joke value;

    public JokeResponse(String type, Joke value) {
        this.type = type;
        this.value = value;
    }

    public JokeResponse() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Joke getValue() {
        return value;
    }

    public void setValue(Joke value) {
        this.value = value;
    }
}
