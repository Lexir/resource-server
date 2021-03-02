package dev.salex.resourceserver.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

public class Joke {
    @JsonIgnore
    private long id;
    @NotEmpty(message = "joke may not be empty")
    private String jokeText;
    private List<String> categories = new ArrayList<>();

    public Joke(long id, String joke) {
        this.id = id;
        this.jokeText = joke;
    }

    public Joke(String joke) {
        this.jokeText = joke;
    }

    public Joke() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJokeText() {
        return jokeText;
    }

    public void setJokeText(String jokeText) {
        this.jokeText = jokeText;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}