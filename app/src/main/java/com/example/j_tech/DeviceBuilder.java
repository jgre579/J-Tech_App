package com.example.j_tech;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class DeviceBuilder {
    protected String name;
    protected List<Integer> imageSrcs;
    protected LinkedHashMap<String, String> specs;
    protected float price;
    protected String moreInfoLink;
    protected int brandImageSrc;
    protected int description;
    protected int year;
    protected int imageSrc;
    protected String imagePrefix;
    protected int topPickScore;

    protected DeviceBuilder() {
        this.specs = new LinkedHashMap<>();
    }



    public DeviceBuilder name(String name) {
        this.name = name;
        return this;
    }


    public DeviceBuilder useImagePrefix() {
        this.imagePrefix = this.name.toLowerCase().replaceAll("\\s","");
        return this;
    }

    public DeviceBuilder price(float price) {
        this.price = price;
        return this;
    }

    public DeviceBuilder moreInfoLink(String moreInfoLink) {
        this.moreInfoLink = moreInfoLink;
        return this;
    }

    public DeviceBuilder brandImageSrc(int brandImageSrc) {
        this.brandImageSrc = brandImageSrc;
        return this;
    }

    public DeviceBuilder description(int description) {
        this.description = description;
        return this;
    }

    public DeviceBuilder year(int year) {
        this.year = year;
        return this;
    }

    public DeviceBuilder topPickScore(int score) {
        this.topPickScore = score;
        return this;
    }




    public abstract Device build();

    public abstract DeviceBuilder specs(String s, String s1, String s2, String s3);
}

