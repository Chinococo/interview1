package com.example.interview;

import android.media.Image;

public class Item {
    //Image image;
    String name;
    String adders;

    public Item(String Name, String Adders) {
        name = Name;
        adders = Adders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdders() {
        return adders;
    }

    public void setAdders(String adders) {
        this.adders = adders;
    }
}
