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
        HashMap<Device, Integer> map = new HashMap<>();
        for (Device device : devices) {

            //charCounts.add();
            map.put(device, getCharOverlap(query, device.getName()));
            //Log.d("search123", device.getName() + " similar core: " + getCharOverlap(query, device.getName()));
            
        }

        List<Map.Entry<Device, Integer>> list = new LinkedList<Map.Entry<Device, Integer>>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Device, Integer>>() {
            @Override
            public int compare(Map.Entry<Device, Integer> o1, Map.Entry<Device, Integer> o2) {
                return (o2.getValue().compareTo(o1.getValue()));
            }
        });
        ArrayList<Device> searchedDevices = new ArrayList<>();
        for (Map.Entry<Device, Integer> entry : list) {
            if(entry.getValue() != 0) {
                searchedDevices.add(entry.getKey());
            }

            Log.d("search123", entry.getKey().getName() + " " + entry.getValue().toString());
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




//        String[] querySplit = query.split(" ");

//        int count = 0;
//        for (int i = 0; i < querySplit.length; i++) {
//
//            for (int j = 0; j < querySplit[i].length(); j++) {
//
//                if(querySplit[i].charAt(j) == name.charAt(j)) {
//                    count++;
//                }
//
//            }
//
//        }
//
//
//
//        return count;
        
        
    }


}
