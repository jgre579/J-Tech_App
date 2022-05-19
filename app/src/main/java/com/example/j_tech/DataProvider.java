package com.example.j_tech;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DataProvider {

    private static ArrayList<Device> devices = new ArrayList<Device>();

    public static void generateDevices() {

        devices.add(new Phone("iPhone 13 Max", R.drawable.phone_category));
        devices.add(new Phone("iPhone 12 Mini", R.drawable.phone_category));
        devices.add(new Tablet("iPad Pro", R.drawable.tablet_category));
        devices.add(new Tablet("Galaxy Tab 3", R.drawable.tablet_category));
        devices.add(new Laptop("Macbook pro", R.drawable.laptop_category));
        devices.add(new Laptop("Lenovo Thinkpad", R.drawable.laptop_category));

    }


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


    public static ArrayList<Device> generateTopPicks() {

        Collections.sort(devices, new Comparator<Device>() {
            @Override
            public int compare(Device o1, Device o2) {
                return o1.getTopPickScore() - o2.getTopPickScore();
            }
        });
        
        return devices;

    }
}
