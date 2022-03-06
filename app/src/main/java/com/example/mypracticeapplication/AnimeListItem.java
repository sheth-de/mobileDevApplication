package com.example.mypracticeapplication;

public class AnimeListItem {
    private final String name;
    private final String rating;
    private final String imageUrl;

    public String getAnimeName() {
        return name;
    }

    public String getRating() {
        return rating;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    //Constructor
    public AnimeListItem(String name, String rating, String imageUrl) {
        this.name = name;
        this.rating = rating;
        this.imageUrl = imageUrl;
    }
}
