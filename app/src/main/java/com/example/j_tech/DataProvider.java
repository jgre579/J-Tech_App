package com.example.j_tech;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataProvider {
    static String[] titles ={"iPhone 12", "iPhone 12 Pro", "iPhone 12 Pro Max"};
    static String[] category = {
            "Phone","Laptop","Tablet"
    };
    static String[] price = {};
    static String[] image = {"url1","url2"};
    static String[] year = {"2021","2022"};
    static String[] performance = {"sim_description 1","sim_description 2"};
    static String[] screen_size ={};
    static String[] storage ={};
    static String[] camera ={};
    static String[] description = {"description 1", "description 2"};
    static ArrayList<Device> topPicks1 = generateTopPicks();

    //user views
    static String[] top_picks = {
            "100","30"
    };
    //tags
    static String[] key_word = {};
   //private static List<Device> devices;
    // private static List<Device> topPicks;


    public static ArrayList<Device> generateTopPicks() {

        ArrayList<Device> topPicks = new ArrayList<Device>();
        for (int i = 0; i < titles.length; i++) {
            topPicks.add(new Device(titles[i],category[i],price[i], image[i],year[i],performance[i],screen_size[i],storage[i],camera[i],description[i],top_picks[i]));
            //topPicks.add(new Device("iPhone 13 Max", R.drawable.phone_category));
        }

        return topPicks;
    }
    //put into list activity
    //cate list function
    void test(String cate ){
        for(int i = 0; i < topPicks1.lendth; i++){
            if(topPicks1[i].category == cate){
                Device();
            }
        }
    }

    // search function
    void search(String tag){
        for(int i = 0; i < topPicks1.lendth; i++){
            if(topPicks1[i].category == cate){
                Device();
            }
        }
    }
}
