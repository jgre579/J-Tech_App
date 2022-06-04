package com.example.j_tech.models;

import java.util.LinkedHashMap;

public abstract class DeviceBuilder {
    /**
     * Helper class used to simplify the creation of device objects in the DataProvider, by using
     * the buidler pattern we did not need to use a 8-9 parameter constructor when making devices
     * this makes our DataProvider much more maintainable and readable.
     */
    protected String name;
    protected LinkedHashMap<String, String> specs;
    protected float price;
    protected String moreInfoLink;
    protected int brandImageSrc;
    protected int description;
    protected int year;
    protected Device.Brand brand;
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

    public DeviceBuilder brand(Device.Brand brand) {
        this.brand = brand;
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

    // Each device child returns an object of itself when its overridden build method is called.
    public abstract Device build();

    // Each device child has its own spec parameters which are unique to it.
    public abstract DeviceBuilder specs(String s, String s1, String s2, String s3);
}

