package com.example.j_tech.utils;

import com.example.j_tech.models.Device;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search {
    /**
     * Helper class to encapsulate all searching logic into one place.
     * Was not present on the design doc as the placement of this logic was overlooked
     */
    private String query;
    private List<Device> devices;

    /**
     * @param query     string that was searched for
     * @param devices   list of devices that are to be searched
     */
    public Search(String query, List<Device> devices) {

        this.query = query;
        this.devices = devices;


    }

    /**
     * Search through each devices name and brand for matches of the specific search query.
     * @return  an ordered ArrayList of devices from best matched to the search to least
     *          (excluding devices with no matches)
     */
    public ArrayList<Device> searchNamesAndBrands() {
        HashMap<Object, Integer> map = new HashMap<>();

        for (Device device : devices) {
            map.put(device, getOverlap(query, device.getName()));
            
        }

        for (Device.Brand brand : Device.Brand.values()) {
            map.put(brand, getOverlap(query, brand.name()));
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
                    if(!searchedDevices.contains(entry.getKey())) {
                        searchedDevices.add((Device) entry.getKey());
                    }
                }
                else if (entry.getKey() instanceof Device.Brand) {

                    for (Device device : devices) {

                        if(device.getBrand().equals(entry.getKey()) && !searchedDevices.contains(device)) {
                            searchedDevices.add(device);
                        }
                    }

                }

            }

        }

        return searchedDevices;
    }

    /**
     * Calculate how much of the query matches the name string by splitting the
     * query into words and using java regex to find matches
     *
     * @param query     query that is being searched for
     * @param name      string that the query is being compared to
     * @return          the search score of the current device, zero for no matches, 1 for one word matching, etc
     */
    private int getOverlap(String query, String name) {

        String[] querySplit = query.split(" ");

        int count = 0;
        for (String s : querySplit) {
            Pattern pattern = Pattern.compile(s, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(name);
            if (matcher.find()) {
                count++;

            }

        }
        return count;
    }

}
