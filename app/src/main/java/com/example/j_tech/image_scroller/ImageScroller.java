package com.example.j_tech.image_scroller;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.j_tech.R;

import java.util.ArrayList;
import java.util.List;

public class ImageScroller {
    /**
     * Helper class created when we realised that both the the MainActivity and DetailsActivity both
     * needed very similar image carousels. Instead of implementing everything twice we decided to make
     * a class of image scroller which can be instantiated when another separate image scroller was needed.
     *
     * Was not present on the design doc because we did not know what we needed or how we could implement and image
     * carousel / recycler view until we learned more later on.
     *
     *
     */

    List<Integer> images;
    List<String> texts;
    int currentTopPickPosition;
    Context context;
    LinearLayout dotsLayout;
    ArrayList<ImageView> dots;
    int activeDot = -1;
    Activity activity;
    ImageScrollerAdapter adapter;
    View.OnClickListener listener;
    RecyclerView rv;

    // Constructor used by the top picks panel, as it requires its images to given via Integers (Resource ID's) and
    // associated texts
    public ImageScroller(List<Integer> images,Activity activity, RecyclerView rv, View.OnClickListener listener, List<String> texts) {
        this.context = activity.getApplicationContext();
        this.texts = texts;
        buildImageScroller(images, activity, rv, listener);
    }

    // Constructor used by the details activity image scrollers which give their images via and image prefix and need
    // no onclick listener.
    public ImageScroller(String imagePrefix,Activity activity, RecyclerView rv) {
        this.context = activity.getApplicationContext();
        buildImageScroller(getImagesFromPrefix(imagePrefix), activity, rv, null);
    }

    public void clearForUpdate() {
        // Get ready for the image scroller to be redrawn
        rv.setOnFlingListener(null);
        texts.clear();
        images.clear();
        for (ImageView dot : dots) {

            ((ViewGroup) dot.getParent()).removeView(dot);
        }

    }


    public void buildImageScroller(List<Integer> images,Activity activity, RecyclerView rv, View.OnClickListener listener){
        this.activity = activity;
        this.listener = listener;
        this.images = images;
        this.rv = rv;

        this.adapter = null;
        this.adapter = new ImageScrollerAdapter(this.images);

        adapter.setText(texts);
        rv.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rv.setLayoutManager(layoutManager);

        // Add snap helper so images snap into place when scrolled.
        SnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(rv);

        dotsLayout = activity.findViewById(R.id.image_dots_layout);
        dots = new ArrayList<ImageView>();

        initImageDots();

        addScrollListener();

    }

    private void addScrollListener() {

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) rv.getLayoutManager();

                currentTopPickPosition = layoutManager.findFirstVisibleItemPosition();
                // Change the image dot to reflect the new position
                setImageDot(currentTopPickPosition);
                LinearLayout layout = (LinearLayout) layoutManager.findViewByPosition(currentTopPickPosition);
                ImageView view = (ImageView) layout.getChildAt(0);
                // Set the on click listener of the new view.
                view.setOnClickListener(listener);

            }});


    }


    private List<Integer> getImagesFromPrefix(String prefix) {
        // Use an image prefix to generate a list of Resource ID's following the device images naming convention
        // Image prefixes should all be lowercase and contain no spaces.
        // e.g iphone13_1 for the first image of iPhone 13, the image prefix would be iphone13
        // e.g pocof4gt_3 for the third image of Poco F4 GT

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
        return context.getResources().getIdentifier(prefix + "_" + String.valueOf(i), "drawable", context.getPackageName());
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

        // Use the current screen width to set the dots margin to ensure, its resize responsive,
        // margins are used to that the dots arent evenly spread across the screen which look terrible.
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.rightMargin = width/3;
        params.leftMargin = width/3;
        dotsLayout.setLayoutParams(params);


    }

}
