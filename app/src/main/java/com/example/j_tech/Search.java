package com.example.j_tech;

import android.os.PatternMatcher;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.Pattern.*;

public class Search {
    private String query;
    private List<Device> devices;
    public Search(String query, List<Device> devices) {

        this.query = query;
        this.devices = devices;


    }

    public ArrayList<Device> searchNames() {
        HashMap<Object, Integer> map = new HashMap<>();

        for (Device device : devices) {
            map.put(device, getCharOverlap(query, device.getName()));
            
        }

        for (Device.Brand brand : Device.Brand.values()) {
            map.put(brand, getCharOverlap(query, brand.name()));
        }

        List<Map.Entry<Object, Integer>> list = new LinkedList<Map.Entry<Object, Integer>>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Object, Integer>>() {
            @Override
            public int compare(Map.Entry<Object, Integer> o1, Map.Entry<Object, Integer> o2) {
                return (o2.getValue().compareTo(o1.getValue()));
            }
        });
        ArrayList<Device> searchedDevices = new ArrayList<>();
        for (Map.Entry<Object, Integer> entry : list) {
            if(entry.getValue() != 0) {

                if(entry.getKey() instanceof Device) {
                    searchedDevices.add((Device) entry.getKey());
                }
                else if (entry.getKey() instanceof Device.Brand) {

                    for (Device device : devices) {

                        if(device.getBrand().equals(entry.getKey())) {
                            searchedDevices.add(device);
                        }
                    }

                }

            }

        }

        return searchedDevices;
    }

    public ArrayList<Device> searchBrands() {

        HashMap<Device.Brand, Integer> map = new HashMap<>();

        for (Device.Brand brand : Device.Brand.values()) {
            map.put(brand, getCharOverlap(query, brand.name()));
        }

        List<Map.Entry<Device.Brand, Integer>> list = new LinkedList<Map.Entry<Device.Brand, Integer>>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Device.Brand, Integer>>() {
            @Override
            public int compare(Map.Entry<Device.Brand, Integer> o1, Map.Entry<Device.Brand, Integer> o2) {
                return (o2.getValue().compareTo(o1.getValue()));
            }
        });
        ArrayList<Device.Brand> searchedBrands = new ArrayList<>();
        ArrayList<Device> searchedDevices = new ArrayList<>();
        for (Map.Entry<Device.Brand, Integer> entry : list) {
            if(entry.getValue() != 0) {
                searchedBrands.add(entry.getKey());
            }
        }

        for (Device.Brand brand : searchedBrands) {

            for (Device device : devices) {

                if(device.getBrand().equals(brand)) {
                    searchedDevices.add(device);
                }
            }
        }

        return searchedDevices;
    }
    
    private int getCharOverlap(String query, String name) {

        String[] querySplit = query.split(" ");

        int count = 0;
        for (int i = 0; i < querySplit.length; i++) {
            Pattern pattern =  Pattern.compile(querySplit[i], Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(name);
            boolean matchFound = matcher.find();;
            if (matchFound) {
                count ++;

            }

        }

        return count;
        
        
    }


}
