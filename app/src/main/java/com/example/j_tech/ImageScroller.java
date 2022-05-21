package com.example.j_tech;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ImageScroller {

    List<Integer> images;
    int currentTopPickPosition;
    Context context;
    LinearLayout dotsLayout;
    ArrayList<ImageView> dots;
    int activeDot = -1;
    Activity activity;
    ImageScrollerAdapter adapter;
    View.OnClickListener listener;
    public ImageScroller(List<Integer> images,Activity activity, RecyclerView rv, View.OnClickListener listener) {
        buildImageScroller(images, activity, rv, listener);
    }

    public ImageScroller(String imagePrefix,Activity activity, RecyclerView rv, View.OnClickListener listener) {
        buildImageScroller(getImagesFromPrefix(imagePrefix), activity, rv, listener);
    }

    public void buildImageScroller(List<Integer> images,Activity activity, RecyclerView rv, View.OnClickListener listener){
        this.activity = activity;
        this.context = activity.getApplicationContext();
        this.listener = listener;
        this.images = images;
        this.adapter = new ImageScrollerAdapter(this.images);
        rv.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rv.setLayoutManager(layoutManager);
        SnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(rv);

        dotsLayout = activity.findViewById(R.id.image_dots_layout);

        dots = new ArrayList<ImageView>();
        Log.d("device", dots.toString());

        initImageDots();


        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) rv.getLayoutManager();


                currentTopPickPosition = layoutManager.findFirstVisibleItemPosition();
                setImageDot(currentTopPickPosition);
                adapter.getImageView().setOnClickListener(listener);

            }});
    }

    public List<Integer> getImagesFromPrefix(String prefix) {

        List<Integer> images = new ArrayList<>();
        int i = 1;
        while (true) {
            int id = getImageId(prefix, i);
            if(id != 0) {
                images.add(id);
            }
            else {
                break;
            }
            i++;
        }

        return images;



    }

    public int getImageId(String prefix, int i) {
        return context.getResources().getIdentifier(prefix + "_" + String.valueOf(i), "drawable", activity.getPackageName());
    }


    public int getActiveDot(){
        return activeDot;
    }



    public void setImageDot(int position){
        // Ensure that the dots arent being redrawn unnecessarily.
        if(activeDot != position){

            clearImageDots();
            dots.get(position).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.dot_active));
            activeDot = position;

        }

    }

    public void clearImageDots(){
        for (int i = 0; i < images.size(); i++) {
            dots.get(i).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.dot_not_active));
        }
    }

    public void initImageDots() {

        for (int i = 0; i < images.size(); i++) {
            ImageView imageView = new ImageView(context);

            imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.dot_not_active));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.weight = 1.0f;
            dotsLayout.addView(imageView);
            imageView.setLayoutParams(params);
            dots.add(imageView);
        }
        setDotsLayoutMargins();
        setImageDot(0);
    }

    public void setDotsLayoutMargins(){

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.rightMargin = width/3;
        params.leftMargin = width/3;
        dotsLayout.setLayoutParams(params);


    }
}
