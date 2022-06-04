package com.example.j_tech.models;

import java.util.LinkedHashMap;

public class Laptop extends Device {

    public Laptop(String name, String imagePrefix, LinkedHashMap<String, String> specs, float price, String moreInfoLink, Brand brand, int description, int year, int topPickScore) {


        super(name, imagePrefix, specs, price, moreInfoLink, brand, description, year, topPickScore);

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
            return new Laptop(this.name, this.imagePrefix, this.specs, this.price, this.moreInfoLink, this.brand, this.description, this.year, this.topPickScore);
        }

        @Override
        public DeviceBuilder specs(String processor, String size, String ram , String storage) {
            specs.put("Processor", processor);
            specs.put("Screen Size", size);
            specs.put("RAM", ram);
            specs.put("Storage", storage);
            return this;
        }

    }
}