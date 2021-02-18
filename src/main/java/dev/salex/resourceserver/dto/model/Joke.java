package dev.salex.resourceserver.dto.model;

import java.util.ArrayList;
import java.util.List;

public class Joke {
    private long id;
    private String joke;
    private List<String> categories=new ArrayList<>();

    public Joke(long id, String joke) {
        this.id = id;
        this.joke = joke;
    }

    public Joke() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}