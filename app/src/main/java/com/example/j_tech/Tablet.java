package com.example.j_tech;

import java.util.LinkedHashMap;
import java.util.Map;

public class Tablet extends Device{

    public Tablet(String name, String imagePrefix, LinkedHashMap<String, String> specs, float price, String moreInfoLink, String brand, int description, int year, int topPickScore) {


        super(name, imagePrefix, specs, price, moreInfoLink, brand, description, year, topPickScore);

    }
    @Override
    public String getType() {
        return "Tablet";
    }


    public static class Builder extends DeviceBuilder {

        public Builder() {
            super();
        }

        @Override
        public Tablet build() {
            return new Tablet(this.name, this.imagePrefix, this.specs, this.price, this.moreInfoLink, this.brand, this.description, this.year, this.topPickScore);
        }

        @Override
        public DeviceBuilder specs(String os, String display, String pen, String Storage) {
            specs.put("OS", os);
            specs.put("Display", display);
            specs.put("Native Pen", pen);
            specs.put("Battery", Storage);
            return this;
        }

    }

}
