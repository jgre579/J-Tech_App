package com.example.j_tech;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataProvider {
    static String[] titles ={"iPhone 12", "iPhone 12 Pro", "iPhone 12 Pro Max",
            "P4","P5","P6", "P7","P8","P9","P10","L1","L2","L3","L4","L5"
            ,"L6","L7","L8","L9","L10","T1","T2","T3","T4","T5","T6","T7"
            ,"T8","T9","T10"};
    static String[] category = {
            "Phone","Phone","Phone","Phone","Phone","Phone","Phone","Phone",
            "Phone","Phone","Laptop","Laptop","Laptop","Laptop","Laptop","Laptop"
            ,"Laptop","Laptop","Laptop","Laptop","Tablet","Tablet","Tablet",
            "Tablet","Tablet","Tablet","Tablet","Tablet","Tablet","Tablet"
    };
    static String[] price = {"$1999","$1999","$1999","$1999","$1999","$1999","$1999"
            ,"$1999","$1999","$1999","$2999","$2999","$2999","$2999","$2999"
            ,"$2999","$2999","$2999","$2999","$2999","$3999","$3999","$3999"
            ,"$3999","$3999","$3999","$3999","$3999","$3999","$3999"};
    static String[] image = {"url1","url2","url3","url4","url5","url6","url7"
            ,"url8","url9","url10","url11","url12","url13","url14","url15"
            ,"url16","url17","url18","url19","url20","url21","url22","url23"
            ,"url24","url25","url26","url27","url28","url29","url30"};
    static String[] year = {"2022","2021","2020","2019","2018","2017","2016"
            ,"2015","2014","2013","2012","2011","2010","2019","2018","2017"
            ,"2016","2015","2014","2013","2012","2011","2010","2009","2008"
            ,"2007","2006","2005","2004","2003"};
    static String[] performance = {"sim_description 1","sim_description 2",
            "3","4","5","6","7","8","9","10","11","12","13","14","15","16"
            ,"17","18","19","20","21","22","23","24","25","26","27","28"
            ,"29","30"};
    static String[] screen_size ={"1","2",
            "3","4","5","6","7","8","9","10","11","12","13","14","15","16"
            ,"17","18","19","20","21","22","23","24","25","26","27","28"
            ,"29","30"};
    static String[] storage ={"1","2",
            "3","4","5","6","7","8","9","10","11","12","13","14","15","16"
            ,"17","18","19","20","21","22","23","24","25","26","27","28"
            ,"29","30"};
    static String[] camera ={"1","2",
            "3","4","5","6","7","8","9","10","11","12","13","14","15","16"
            ,"17","18","19","20","21","22","23","24","25","26","27","28"
            ,"29","30"};
    static String[] description = {"description 1", "description 2",
            "3","4","5","6","7","8","9","10","11","12","13","14","15","16"
            ,"17","18","19","20","21","22","23","24","25","26","27","28"
            ,"29","30"};
    //user views
    static String[] top_picks = {"1","2",
            "3","4","5","6","7","8","9","10","11","12","13","14","15","16"
            ,"17","18","19","20","21","22","23","24","25","26","27","28"
            ,"29","30"
    };
    //tags
    static String[] key_word = {"1","2",
            "3","4","5","6","7","8","9","10","11","12","13","14","15","16"
            ,"17","18","19","20","21","22","23","24","25","26","27","28"
            ,"29","30"};
    // private static List<Device> devices;
    // private static List<Device> topPicks;


    public static ArrayList<DeviceAttribute> generateDevice() {
        ArrayList<DeviceAttribute> Attribute = new ArrayList<DeviceAttribute>();
        for (int i = 0; i < titles.length; i++) {
            Attribute.add(new DeviceAttribute(titles[i], category[i],price[i], image[i],year[i],
                    performance[i],screen_size[i],storage[i],camera[i],description[i],
                    top_picks[i],key_word[i]));
        }

        return Attribute;
    }

    public static ArrayList<Device> generateTopPicks() {

        ArrayList<Device> topPicks = new ArrayList<Device>();

        for (int i = 0; i < 2; i++) {
            topPicks.add(new Device("iPhone 13 Max", R.drawable.phone_category));
        }

        return topPicks;

    }
}
