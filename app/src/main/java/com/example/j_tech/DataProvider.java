package com.example.j_tech;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataProvider {
    public static Map<Integer,String> generateDeviceName(){
        Map<Integer,String> name = new LinkedHashMap<Integer,String>();
        name.put(1,"phone1");
        name.put(2,"phone2");
        name.put(3,"phone3");
        name.put(4,"phone4");
        name.put(5,"phone5");
        name.put(6,"phone6");
        name.put(7,"phone7");
        name.put(8,"phone8");
        name.put(9,"phone9");
        name.put(10,"phone10");
        return name;

    }








    // You can get a list of the devices by supplying an instance of the object,
    // Either Phone, Tablet, or Laptop.
    public static ArrayList<Device> getDevices(Device d) {

        if(devices.isEmpty()) {
            generateDevices();
        }

        ArrayList<Device> sortedDevice = new ArrayList<>();

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

    public static void generateDevices() {





        devices.add(new Phone.Builder()
                .name("iPhone 13")
                .useImagePrefix()
                .year(2021)
                .price(730.00f)
                .description(R.string.Lorem_Ipsum)
                .moreInfoLink("https://www.apple.com/nz/iphone-13")
                .brandImageSrc(R.drawable.apple_brand_image)
                .specs("iOS 15", "6.1\"", "128/256/512GB", "Dual 12MP + 12MP")
                .build()
        );
        devices.add(new Phone.Builder()
                .name("Galaxy S20")
                .useImagePrefix()
                .year(2020)
                .price(899.00f)
                .description(R.string.Lorem_Ipsum)
                .moreInfoLink("https://www.samsung.com/nz/smartphones/galaxy-s20/")
                .brandImageSrc(R.drawable.samsung_brand_image)
                .specs("Android 10 - 11", "6.2\"","128GB", "Triple 12/64/12 MP + 10MP")
                .build()
        );
//        devices.add(new Phone.Builder()
//                .name("Xiaomi 11T Pro")
//                .useImagePrefix()
//                .year(2021)
//                .price(467.00f)
//                .description(R.string.Lorem_Ipsum)
//                .moreInfoLink("https://www.mi-store.co.nz/product/MZB09JJEU/11T-Pro-5G-Dual-SIM-Smartphone")
//                .brandImageSrc(R.drawable.xiaomi_brand_image)
//                .specs("Android 11", "6.67\"", "128/256GB", "Triple 108/8/5MP + 16MP")
//                .build()
//        );

        devices.add(new Phone.Builder()
                .name("iPhone 12")
                .useImagePrefix()
                .year(2020)
                .price(500.00f)
                .description(R.string.Lorem_Ipsum)
                .moreInfoLink("https://www.apple.com/nz/iphone-12/")
                .brandImageSrc(R.drawable.apple_brand_image)
                .specs("iOS 14.1 - 15.5", "6.61\"", "64/128/256GB", "Dual 12MP + 12MP")
                .build()
        );



    }


}
