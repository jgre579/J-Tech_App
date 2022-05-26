package com.example.j_tech;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class Device implements Serializable {

    private String name;
    private float price;
    private String moreInfoLink;
    private String brand;
    private int description;
    private int year;
    private int topPickScore;
    private String imagePrefix;
    private LinkedHashMap<String, String> specs;

    public Device(String name, String imagePrefix, LinkedHashMap<String, String> specs, float price, String moreInfoLink, String brand, int description, int year, int topPickScore) {

        this.name = name;
        this.imagePrefix = imagePrefix;
        this.price = price;
        this.moreInfoLink = moreInfoLink;
        this.brand = brand;
        this.description = description;
        this.year = year;
        this.specs = specs;
        this.topPickScore = topPickScore;

    }



    public void incrementPickScore() {
        this.topPickScore++;
    }

    public int getTopPickScore() {
        return topPickScore;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getSpecs() {
        return specs;
    }

    public float getPrice() {
        return price;
    }

    public String getMoreInfoLink() {
        return moreInfoLink;
    }

    public String getBrand() {
        return brand;
    }

    public int getDescription() {
        return description;
    }

    public int getYear() {
        return year;
    }

    public String getImagePrefix() {return imagePrefix;}

    public abstract String getType();




}

