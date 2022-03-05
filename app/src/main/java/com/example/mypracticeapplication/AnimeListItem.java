package com.example.mypracticeapplication;

public class AnimeListItem {
    private final String name;
    private final String rating;

    public String getAnimeName() {
        return name;
    }

    public String getRating() {
        return rating;
    }

    //Constructor
    public AnimeListItem(String name, String rating) {
        this.name = name;
        this.rating = rating;
    }
}
