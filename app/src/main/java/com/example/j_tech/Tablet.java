package com.example.j_tech;

import java.util.LinkedHashMap;
import java.util.Map;

public class Tablet extends Device{

    public Tablet(String name, String imagePrefix, LinkedHashMap<String, String> specs, float price, String moreInfoLink, int brandImageSrc, int description, int year) {


        super(name, imagePrefix, specs, price, moreInfoLink, brandImageSrc, description, year);

    }

    public static class Builder extends DeviceBuilder {

        public Builder() {
            super();
        }

        @Override
        public Tablet build() {
            return new Tablet(this.name, this.imagePrefix, this.specs, this.price, this.moreInfoLink, this.brandImageSrc, this.description, this.year);
        }

        @Override
        public DeviceBuilder specs(String s, String s1, String s2, String s3) {
            return null;
        }

    }

}
