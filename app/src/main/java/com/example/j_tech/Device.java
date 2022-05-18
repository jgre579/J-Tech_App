package com.example.j_tech;

import android.graphics.drawable.Drawable;

public class Device {
// topPicks.add(new Device(titles[i],price[i], image[i],year[i],
// performance[i],screen_size[i],storage[i],camera[i],description[i],
// top_picks[i]);
    private String titles;
    private String imageSrc;
    private String name;
    private String price;
    private String image;
    private String year;
    private String performance;
    private String screen_size;
    private String storage;
    private String camera;
    private String description;
    private String top_picks;

    //for detail page
    public Device(String name, String imageSrc, String titles, String price,
                  String image, String year, String performance,
                  String screen_size, String storage, String camera,
                  String description, String top_picks){
        this.imageSrc = imageSrc;
        this.name = name;
        this.titles = titles;
        this.price = price;
        this.image = image;
        this.year = year;
        this.performance = performance;
        this.screen_size = screen_size;
        this.storage = storage;
        this.camera = camera;
        this.description = description;
        this.top_picks = top_picks;
    }

    public String getName(){
        return name;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public String getTitles(){return titles;}

    public String getPrice(){return price;}

    public String getImage(){return image;}

    public String getYear(){return titles;}

    public String getPerformance(){return titles;}

    public String getScreen_size(){return titles;}

    public String getStorage(){return titles;}

    public String getCamera(){return titles;}

    public String getDescription(){return titles;}

    public String getTop_picks(){return titles;}

}
