package com.example.j_tech;

import android.graphics.drawable.Drawable;

public class DeviceAttribute {
    private String titles;
    private String category;
    private String price;
    private String image;
    private String year;
    private String performance;
    private String screen_size;
    private String storage;
    private String camera;
    private String description;
    private String top_picks;
    private String key_word;

    //for detail page
    public DeviceAttribute(String titles, String category,String price,
                           String image, String year, String performance,
                           String screen_size, String storage, String camera,
                           String description, String top_picks, String key_word){
        this.titles = titles;
        this.category = category;
        this.price = price;
        this.image = image;
        this.year = year;
        this.performance = performance;
        this.screen_size = screen_size;
        this.storage = storage;
        this.camera = camera;
        this.description = description;
        this.top_picks = top_picks;
        this.key_word = key_word;

    }

    public String getTitles(){return titles;}

    public String getCategory(){return category;}

    public String getPrice(){return price;}

    public String getImage(){return image;}

    public String getYear(){return year;}

    public String getPerformance(){return performance;}

    public String getScreen_size(){return screen_size;}

    public String getStorage(){return storage;}

    public String getCamera(){return camera;}

    public String getDescription(){return description;}

    public String getTop_picks(){return top_picks;}

    public String getKey_word(){return key_word;}

}
