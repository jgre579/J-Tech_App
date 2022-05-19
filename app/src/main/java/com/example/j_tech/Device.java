package com.example.j_tech;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Map;

public class Device {



    private ArrayList<Integer> imageSrcs;
    private String name;
    private Map<String, String> specs;
    private float price;
    private String moreInfoLink;
    private int brandImageSrc;
    private String description;
    private int year;
    private int topPickScore;

    public Device(String name, ArrayList<Integer> imageSrcs, Map<String, String> specs, float price, String moreInfoLink, int brandImageSrc, String description, int year) {

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
    public Device(String name, int imageSrc){
        this.name = name;
        this.imageSrcs = new ArrayList<Integer>();
        this.imageSrcs.add(imageSrc);


    }

    public void incrementPickScore(){
        topPickScore+=1;
    }

    public int getTopPickScore(){
        return topPickScore;
    }

    public ArrayList<Integer> getImageSrcs() {
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

    public String getDescription() {
        return description;
    }

    public int getYear() {
        return year;
    }
}
