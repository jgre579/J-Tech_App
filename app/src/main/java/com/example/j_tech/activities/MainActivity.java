package com.example.j_tech.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.res.Configuration;
import android.transition.Fade;
import android.transition.Slide;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.j_tech.DataProvider;
import com.example.j_tech.models.Device;
import com.example.j_tech.image_scroller.ImageScroller;
import com.example.j_tech.R;
import com.example.j_tech.utils.TopPicks;
import com.google.android.material.card.MaterialCardView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    class MainViewHolder {

        TextView devicesTextView;
        EditText searchEditText;
        RecyclerView topPicksRV;
        ActionBar actionBar;
        SearchManager searchManager;
        SearchView searchView;
        ImageView searchButtonImage;
        View search_plate;

        public MainViewHolder(){

            devicesTextView = findViewById(R.id.devices_text_view);
            topPicksRV = (RecyclerView) findViewById(R.id.top_picks_recycler_view);
            setSupportActionBar(findViewById(R.id.main_toolbar));
            actionBar = getSupportActionBar();
            searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            searchView = (SearchView) findViewById(R.id.search_view) ;
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

            int searchTextId = getResources().getIdentifier("android:id/search_src_text", null, null);
            int searchButtonId = getResources().getIdentifier("android:id/search_button", null, null);
            int search_plateId = getResources().getIdentifier("android:id/search_plate", null, null);

            searchEditText = ((EditText) searchView.findViewById(searchTextId));
            searchButtonImage = (ImageView) searchView.findViewById(searchButtonId);
            search_plate = ((View) searchView.findViewById(search_plateId));

        }
        public String getCategoryText(View view) {

            MaterialCardView cardView = (MaterialCardView) view;
            LinearLayout layout = (LinearLayout) cardView.getChildAt(0);
            TextView textView = (TextView) layout.getChildAt(0);

            return (String) textView.getText();

        }

    }
    MainViewHolder vh;
    ImageScroller imageScroller;
    ArrayList<Device> topPicks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vh = new MainViewHolder();
        customizeSearchBar();
    }

    @Override
    protected void onResume() {
        if(imageScroller != null) {
            imageScroller.clearForUpdate();
        }

        fillTopPicks();
        vh.searchEditText.clearFocus();
        super.onResume();
    }

    private void fillTopPicks() {

        ArrayList<Integer> images = new ArrayList<>();
        ArrayList<String> texts = new ArrayList<>();
        topPicks = new ArrayList<>();
        topPicks = (ArrayList<Device>) TopPicks.calculateTopPicks(DataProvider.getAllDevices());

        for (Device device : topPicks) {
            // Get first image of every top picked device
            String prefix = device.getImagePrefix();
            int id = getImageId(prefix, 1);
            images.add(id);
            texts.add(device.getName());
        }

        // Add on click listener to start the details activity
        imageScroller = new ImageScroller(images, this, vh.topPicksRV, getTopPickClickListener(), texts);

    }

    private View.OnClickListener getTopPickClickListener() {

        View.OnClickListener topPickClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Device d = topPicks.get(imageScroller.getActiveDot());
                d.incrementPickScore();
                Intent detailsActivity = new Intent(getBaseContext(), DetailsActivity.class);
                detailsActivity.putExtra("Device",  (Serializable) d );
                startActivity(detailsActivity);
            }
        };

        return topPickClickListener;

    }



    private int getImageId(String prefix, int i) {
        return getResources().getIdentifier(prefix + "_" + String.valueOf(i), "drawable", getPackageName());
    }


    // On click listener for the category cards
    public void clickCategory(View v) {
        Intent listActivity = new Intent(getBaseContext(), ListActivity.class);
        listActivity.putExtra("deviceType", vh.getCategoryText(v));
        startActivity(listActivity);

    }


    public void customizeSearchBar() {
        // Custom search view components programmatically as its components can't be customised via XML
        vh.searchEditText.setBackgroundResource(R.drawable.search_bar);
        vh.searchEditText.setTextColor(getResources().getColor(R.color.white));
        vh.searchEditText.setHintTextColor(getResources().getColor(R.color.white_50a));
        vh.searchEditText.setPadding(40,5,5,5);
        vh.searchButtonImage.setImageResource(R.drawable.search24_white);
        vh.search_plate.setBackgroundResource(R.color.blue_900);


    }




}