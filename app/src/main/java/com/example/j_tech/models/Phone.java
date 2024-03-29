package com.example.j_tech.models;

import java.util.LinkedHashMap;

public class Phone extends Device {

    public Phone(String name, String imagePrefix, LinkedHashMap<String, String> specs, float price, String moreInfoLink, Brand brand, int description, int year, int topPickScore) {


        super(name, imagePrefix, specs, price, moreInfoLink, brand, description, year, topPickScore);

    }
    @Override
    public String getType() {
        return "Phone";
    }

    public static class Builder extends DeviceBuilder {
        public Builder() {

            super();
        }

        @Override
        public DeviceBuilder specs(String os, String display, String camera, String storage) {
            specs.put("OS", os);
            specs.put("Display", display);
            specs.put("Camera", camera);
            specs.put("Storage", storage);
            return this;

        }


        @Override
        public Phone build() {

            return new Phone(this.name, this.imagePrefix, this.specs, this.price, this.moreInfoLink, this.brand, this.description, this.year, this.topPickScore);
        }

    }

}
