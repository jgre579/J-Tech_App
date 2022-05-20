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

public class ImageScroller {

    ArrayList<Integer> images;
    int currentTopPickPosition;
    Context context;
    LinearLayout dotsLayout;
    ArrayList<ImageView> dots;
    int imageNum = 3;
    int activeDot = -1;
    Activity activity;
    ImageScrollerAdapter adapter;
    View.OnClickListener listener;
    public ImageScroller(ArrayList<Integer> images, Activity activity, RecyclerView rv, View.OnClickListener listener) {
        this.images = images;
        this.activity = activity;
        this.context = activity.getApplicationContext();
        this.listener = listener;
        this.adapter = new ImageScrollerAdapter(images);
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
        for (int i = 0; i < imageNum; i++) {
            dots.get(i).setImageDrawable(ContextCompat.getDrawable(context, R.drawable.dot_not_active));
        }
    }

    public void initImageDots() {

        for (int i = 0; i < imageNum; i++) {
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
