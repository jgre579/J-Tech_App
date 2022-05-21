package com.example.j_tech;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Device implements Serializable {


    private List<Integer> imageSrcs;
    private String name;
    private Map<String, String> specs;
    private float price;
    private String moreInfoLink;
    private int brandImageSrc;
    private int description;
    private int year;
    private int topPickScore;

    public Device(String name, List<Integer> imageSrcs, Map<String, String> specs, float price, String moreInfoLink, int brandImageSrc, int description, int year) {

        this.name = name;
        this.imageSrcs = imageSrcs;
        this.specs = specs;
        this.price = price;
        this.moreInfoLink = moreInfoLink;
        this.brandImageSrc = brandImageSrc;
        this.description = description;
        this.year = year;
        topPickScore = 0;

    }

    //Temporary Constructor for Toppicks testing
    public Device(String name, int imageSrc) {
        this.name = name;
        this.imageSrcs = new ArrayList<Integer>();
        this.imageSrcs.add(imageSrc);

    }

    public Device(String name, ArrayList<Integer> imageSrcs) {
        this.name = name;
        this.imageSrcs = imageSrcs;
    }

    public void incrementPickScore() {
        topPickScore += 1;
    }

    public int getTopPickScore() {
        return topPickScore;
    }

    public List<Integer> getImageSrcs() {
        return imageSrcs;
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

    public int getBrandImageSrc() {
        return brandImageSrc;
    }

    public int getDescription() {
        return description;
    }

    public int getYear() {
        return year;
    }


}

