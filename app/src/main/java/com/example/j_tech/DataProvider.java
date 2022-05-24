package com.example.j_tech;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class DataProvider {
    public static Map<Integer,String> generateDeviceName(){
        Map<Integer,String> name = new LinkedHashMap<>();
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




    private static ArrayList<Device> devices = new ArrayList<>();




    // You can get a list of the devices by supplying an instance of the object,
    // Either Phone, Tablet, or Laptop.
    public static ArrayList<Device> getDevices(String deviceType) {

        if(devices.isEmpty()) {
            devices.clear();
            Log.d("len", String.valueOf(devices.size()));
            generateDevices();
        }


        ArrayList<Device> sortedDevice = new ArrayList<>();

        for (Device device : devices) {
            if(device.getType().equals(deviceType.substring(0, deviceType.length() - 1)) ) {

               sortedDevice.add(device);

            }
        }
        Log.d("len", String.valueOf(sortedDevice.size()));

        return sortedDevice;

    }

    public static ArrayList<Device> getAllDevices() {

        if(devices.isEmpty()) {
            generateDevices();
        }
        return devices;

    }


    public static Device getDevice(String name) {

        for (Device device : devices) {
            if(device.getName().equals(name)) {

                return device;

            }
        }

        return null;

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
        devices.add(new Phone.Builder()
                .name("Xiaomi 11T Pro")
                .useImagePrefix()
                .year(2021)
                .price(467.00f)
                .description(R.string.Lorem_Ipsum)
                .moreInfoLink("https://www.mi-store.co.nz/product/MZB09JJEU/11T-Pro-5G-Dual-SIM-Smartphone")
                .brandImageSrc(R.drawable.xiaomi_brand_image)
                .specs("Android 11", "6.67\"", "128/256GB", "Triple 108/8/5MP + 16MP")
                .build()
        );

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

        devices.add(new Phone.Builder()
                .name("Galaxy A13")
                .useImagePrefix()
                .year(2020)
                .price(171.00f)
                .description(R.string.Lorem_Ipsum)
                .moreInfoLink("https://www.samsung.com/nz/smartphones/galaxy-a/galaxy-a13-light-blue-128gb-sm-a135flbhxnz/")
                .brandImageSrc(R.drawable.samsung_brand_image)
                .specs("Android 12", "6.6\"", "32/64/128GB", "Quad 50/5/2/2MP + 8MP")
                .build()
        );
        devices.add(new Phone.Builder()
                .name("Poco F4 GT")
                .useImagePrefix()
                .year(2022)
                .price(666.00f)
                .description(R.string.Lorem_Ipsum)
                .moreInfoLink("https://www.po.co/global/product/poco-f4-gt/")
                .brandImageSrc(R.drawable.xiaomi_brand_image)
                .specs("Android 12, MIUI 13", "6.67\"", "128/256GB", "Triple 64/8/2MP + 8MP")
                .build()
        );

        devices.add(new Phone.Builder()
                .name("Pixel 6")
                .useImagePrefix()
                .year(2021)
                .price(557.99f)
                .description(R.string.Lorem_Ipsum)
                .moreInfoLink("https://store.google.com/gb/product/pixel_6?hl=en-GB")
                .brandImageSrc(R.drawable.google_brand_image)
                .specs("Android 12", "6.4\"", "128/256GB", "Dual 50/12MP + 8MP")
                .build()
        );

        devices.add(new Phone.Builder()
                .name("Pixel 4 XL")
                .useImagePrefix()
                .year(2019)
                .price(749.99f)
                .description(R.string.Lorem_Ipsum)
                .moreInfoLink("https://themarket.com/nz/p/google-pixel-4-xl-6.3-16-mp-sd-855/5285-GGLPX4XLCFG?skuid=10146735&utm_source=google&utm_medium=cpc&gclid=CjwKCAjw4ayUBhA4EiwATWyBrgfOTtzlqz7dke-PmL7FEpNsO__kPqbPGWeTPqWub9DtMymZ5ZEb5RoCbd4QAvD_BwE")
                .brandImageSrc(R.drawable.google_brand_image)
                .specs("Android 12 - 10", "6.3\"", "64/128GB", "Dual 12.2/16MP + 8MP")
                .build()
        );

        devices.add(new Phone.Builder()
                .name("iPhone 11 Pro")
                .useImagePrefix()
                .year(2019)
                .price(899.00f)
                .description(R.string.Lorem_Ipsum)
                .moreInfoLink("https://www.apple.com/nz/iphone-11/")
                .brandImageSrc(R.drawable.apple_brand_image)
                .specs("iOS 13 - 15.5", "5.8\"", "64/256/512GB", "Triple 12MP + Dual 12MP")
                .build()
        );

        devices.add(new Phone.Builder()
                .name("iPhone 12 Mini")
                .useImagePrefix()
                .year(2020)
                .price(668.91f)
                .description(R.string.Lorem_Ipsum)
                .moreInfoLink("https://www.apple.com/nz/shop/buy-iphone/iphone-12?afid=p238%7CsrQ9awRxm-dc_mtid_18707vxu38484_pcrid_545743162980_pgrid_133998605464_pntwk_g_pchan__pexid__&cid=aos-nz-kwgo-iphone--slid---product-")
                .brandImageSrc(R.drawable.apple_brand_image)
                .specs("iOS 14.1 - 15.5", "5.4\"", "64/256/512GB", "Dual 12MP + Dual 12MP")
                .build()
        );






        devices.add(new Tablet.Builder()
                .name("Galaxy Tab S8")
                .useImagePrefix()
                .year(2020)
                .price(500.00f)
                .description(R.string.Lorem_Ipsum)
                .moreInfoLink("https://www.apple.com/nz/iphone-12/")
                .brandImageSrc(R.drawable.samsung_brand_image)
                .specs("Android 12", "11.0\"", "S Pen", "128 GB")
                .build()
        );



    }


}
