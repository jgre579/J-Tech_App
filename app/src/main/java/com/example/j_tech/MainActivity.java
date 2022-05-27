package com.example.j_tech;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MainViewHolder vh;
    //DeviceAdaptor deviceAdaptor;
    class MainViewHolder {

        TextView devicesTextView;
        EditText searchEditText;
        RecyclerView topPicksRV;
        ActionBar actionBar;





        public MainViewHolder(){



            devicesTextView = findViewById(R.id.devices_text_view);
            topPicksRV = (RecyclerView) findViewById(R.id.top_picks_recycler_view);
            setSupportActionBar(findViewById(R.id.main_toolbar));
            actionBar = getSupportActionBar();




        }
        public String getCategoryText(View view) {

            MaterialCardView cardView = (MaterialCardView) view;
            LinearLayout layout = (LinearLayout) cardView.getChildAt(0);
            TextView textView = (TextView) layout.getChildAt(0);

            return (String) textView.getText();

        }

    }

    ImageScroller imageScroller;
    ArrayList<Device> topPicks;

    public void fillTopPicks() {

        ArrayList<Integer> images = new ArrayList<>();
        ArrayList<String> texts = new ArrayList<>();
        topPicks = new ArrayList<>();
        topPicks = (ArrayList<Device>) TopPicks.calculateTopPicks(DataProvider.getAllDevices());

        for (Device device : topPicks) {
            // Get first image of every top pick
            String prefix = device.getImagePrefix();
            int id = getImageId(prefix, 1);
            images.add(id);
            texts.add(device.getName());

        }
        View.OnClickListener topPickClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Device d = topPicks.get(imageScroller.getActiveDot());
                d.incrementPickScore();
                Intent detailsActivity = new Intent(getBaseContext(), DetailsActivity.class);
                detailsActivity.putExtra("Device",  (Serializable) d );
                startActivity(detailsActivity);
                Log.d("click", "CLICKED");
            }

        };
        imageScroller = new ImageScroller(images, this, vh.topPicksRV, topPickClickListener, texts);

    }

    @Override
    protected void onResume() {
        if(imageScroller != null) {
            imageScroller.clearForUpdate();
        }
        fillTopPicks();
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vh = new MainViewHolder();
        Log.d("search123", "created");

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =(SearchView) findViewById(R.id.search_view) ;
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));



    }
    public void clickCategory(View v) {
        Intent listActivity = new Intent(getBaseContext(), ListActivity.class);
        MaterialCardView card = (MaterialCardView) v;
        LinearLayout layout = (LinearLayout) card.getChildAt(0);
        TextView textView = (TextView) layout.getChildAt(0);
        listActivity.putExtra("deviceType", textView.getText());
        startActivity(listActivity);
    }


    public int getImageId(String prefix, int i) {
        return getResources().getIdentifier(prefix + "_" + String.valueOf(i), "drawable", getPackageName());
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.options_menu, menu);
//
//        // Associate searchable configuration with the SearchView
//        SearchManager searchManager =
//                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView =
//                (SearchView) menu.findItem(R.id.search_menu).getActionView();
//        searchView.setSearchableInfo(
//                searchManager.getSearchableInfo(getComponentName()));
//
//        MenuItem searchItem = menu.findItem(R.id.search_menu);
//        searchItem.setIcon(AppCompatResources.getDrawable(this, R.drawable.search24_white));
//
//
//        return true;
//
//    }



}