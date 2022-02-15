package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class LinkItem {
    private final String name;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    private final String url;

    //Constructor
    public LinkItem(String name, String url) {
        this.name = name;
        this.url = url;
    }


}
