package com.example.j_tech;

import android.content.Intent;
import android.view.View;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Phone extends Device{

    public Phone(String name, String imagePrefix, LinkedHashMap<String, String> specs, float price, String moreInfoLink, int brandImageSrc, int description, int year) {


        super(name, imagePrefix, specs, price, moreInfoLink, brandImageSrc, description, year);

    }
    public Phone() {
        super();

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

            return new Phone(this.name, this.imagePrefix, this.specs, this.price, this.moreInfoLink, this.brandImageSrc, this.description, this.year);
        }

    }

}
