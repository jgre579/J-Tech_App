package com.example.j_tech;

import java.util.ArrayList;

public class Phone extends Device{

    public Phone(String name, int imageSrc) {
        super(name, imageSrc);
    }

    public Phone(String name, ArrayList<Integer> imageSrc) {
        super(name, imageSrc);
    }

}
