package com.example.j_tech;

import android.graphics.drawable.Drawable;

public class Device {

    private int imageSrc;
    private String name;

    public Device(String name, int imageSrc){
        this.imageSrc = imageSrc;
        this.name = name;

    }

    public String getName(){
        return name;
    }

    public int getImageSrc() {
        return imageSrc;
    }
}
