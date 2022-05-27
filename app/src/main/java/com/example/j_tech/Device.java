package com.example.j_tech;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Device implements Serializable {

    enum Brand {

        APPLE ,
        GOOGLE,
        XIAOMI,
        SAMSUNG,
        HUAWEI,

    }

    private String name;
    private float price;
    private String moreInfoLink;
    private Brand brand;
    private int description;
    private int year;
    private int topPickScore;
    private String imagePrefix;
    private LinkedHashMap<String, String> specs;

    public Device(String name, String imagePrefix, LinkedHashMap<String, String> specs, float price, String moreInfoLink, Brand brand, int description, int year, int topPickScore) {

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

    public Brand getBrand() {
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

