package com.example.j_tech;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TopPicks {




    public static List<Device> calculateTopPicks(List<Device> devices) {

        Collections.sort(devices, new Comparator<Device>() {
            @Override
            public int compare(Device o1, Device o2) {
                return o2.getTopPickScore() - o1.getTopPickScore();
            }
        });
        List<Device> topPicks;
        if(devices.size() > 3) {
            topPicks = new ArrayList<Device>(devices.subList(0,3));
        }
        else {
            topPicks = devices;
        }


        return topPicks;

    }






}
