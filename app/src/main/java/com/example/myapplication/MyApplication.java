package com.example.myapplication;

import android.app.Application;

import java.util.ArrayList;

public class MyApplication extends Application {
    private static ArrayList<LinkItem> itemList = new ArrayList<>();

    public static ArrayList<LinkItem> getItemList() {
        return itemList;
    }

    public static void setItemList(ArrayList<LinkItem> itemList) {
        MyApplication.itemList = itemList;
    }

}
