package com.example.j_tech;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Phone extends Device{

    public Phone(String name, int imageSrc) {
        super(name, imageSrc);
    }

    public Phone(String name, ArrayList<Integer> imageSrc) {
        super(name, imageSrc);
    }

    public Phone(String name, String imagePrefix, Map<String, String> specs, float price, String moreInfoLink, int brandImageSrc, int description, int year) {


        super(name, imagePrefix, specs, price, moreInfoLink, brandImageSrc, description, year);

    }

    public static class Builder extends DeviceBuilder {

        public Builder() {
            super();
        }

        @Override
        public Phone build() {
            return new Phone(this.name, this.imagePrefix, this.specs, this.price, this.moreInfoLink, this.brandImageSrc, this.description, this.year);
        }

    }

}
