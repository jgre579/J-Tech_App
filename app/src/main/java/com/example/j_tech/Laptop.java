package com.example.j_tech;

import java.util.LinkedHashMap;
import java.util.Map;

public class Laptop extends Device{

    public Laptop(String name, String imagePrefix, LinkedHashMap<String, String> specs, float price, String moreInfoLink, int brandImageSrc, int description, int year) {


        super(name, imagePrefix, specs, price, moreInfoLink, brandImageSrc, description, year, 0);

    }
    @Override
    public String getType() {
        return "Laptop";
    }

    public static class Builder extends DeviceBuilder {

        public Builder() {
            super();
        }



        @Override
        public Laptop build() {
            return new Laptop(this.name, this.imagePrefix, this.specs, this.price, this.moreInfoLink, this.brandImageSrc, this.description, this.year);
        }

        @Override
        public DeviceBuilder specs(String s, String s1, String s2, String s3) {
            return null;
        }

    }
}