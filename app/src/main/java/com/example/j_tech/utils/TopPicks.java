package com.example.j_tech.utils;

import com.example.j_tech.models.Device;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TopPicks {

    /**
     * Helper class to encapsulate all top pick logic into one place.
     * Was not present on the design doc as the placement of this logic was overlooked
     *
     * Take the given list of devices and compare and sort into ascending order of top pick score
     * @param devices   list of devices to be sorted
     * @return          list of sorted devices
     */
    public static List<Device> calculateTopPicks(List<Device> devices) {

        Collections.sort(devices, new Comparator<Device>() {
            @Override
            public int compare(Device o1, Device o2) {
                return o2.getTopPickScore() - o1.getTopPickScore();
            }
        });

        List<Device> topPicks;
        // If the devices list has more than three elements grab the first three, otherwise return the whole list
        if(devices.size() > 3) {
            topPicks = new ArrayList<Device>(devices.subList(0,3));
        }
        else {
            topPicks = devices;
        }
        return topPicks;

    }






}
