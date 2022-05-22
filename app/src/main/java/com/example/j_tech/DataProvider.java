package com.example.j_tech;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

public class DataProvider {

    private static ArrayList<Device> devices = new ArrayList<Device>();

    public static void generateDevices() {
//        ArrayList<Integer> images = new ArrayList<>(Arrays.asList(
//                R.drawable.laptop_category, R.drawable.tablet_category, R.drawable.tablet_category));
//
//        LinkedHashMap<String, String> map = new LinkedHashMap<>();
//        map.put("Performance", "Octa core");
//        map.put("Display", "5.8\" ");
//        map.put("Storage", "128 GB");
//        map.put("Camera", "12 MP + 20 MP");
//
//
//        devices.add(new Phone("iPhone Test 12", images, map, 1200.00f, "link", R.drawable.tablet_category,
// "decription", 2012));
//
//
//        devices.add(new Phone("iPhone 13 Max", R.drawable.phone_category));
//        devices.add(new Phone("iPhone 12 Mini", R.drawable.phone_category));
//        devices.add(new Tablet("iPad Pro", R.drawable.tablet_category));
//        devices.add(new Tablet("Galaxy Tab 3", R.drawable.tablet_category));
//        devices.add(new Laptop("Macbook pro", R.drawable.laptop_category));
//        devices.add(new Laptop("Lenovo Thinkpad", R.drawable.laptop_category));

        devices.add(new Phone.Builder()
                .name("iPhone 13")
                .useImagePrefix()
                .year(2021)
                .price(730.00f)
                .description(R.string.Lorem_Ipsum)
                .moreInfoLink("Link")
                .brandImageSrc(R.drawable.tablet_category)
                .build()
        );



    }

    // You can get a list of the devices by supplying an instance of the object,
    // Either Phone, Tablet, or Laptop.
    public static ArrayList<Device> getDevices(Device d) {

        if(devices.isEmpty()) {
            generateDevices();
        }

        ArrayList<Device> sortedDevice = new ArrayList<Device>();

        for (Device device : devices) {
            if(device.getClass().isInstance(d)) {

                sortedDevice.add(device);

            }
        }

        return sortedDevice;

    }

    public static Device getDevice(String name) {

        for (Device device : devices) {
            if(device.getName().equals(name)) {

                return device;

            }
        }

        return null;

    }


    public static ArrayList<Device> generateTopPicks() {

        if(devices.isEmpty()) {
            generateDevices();
        }

        Collections.sort(devices, new Comparator<Device>() {
            @Override
            public int compare(Device o1, Device o2) {
                return o1.getTopPickScore() - o2.getTopPickScore();
            }
        });
        ArrayList<Device> topPicks;
        if(devices.size() > 3) {
            topPicks = new ArrayList<Device>(devices.subList(0,3));
        }
        else {
            topPicks = devices;
        }

        
        return topPicks;

    }


}
