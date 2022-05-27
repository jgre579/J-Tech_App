package com.example.j_tech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    MainViewHolder vh;

    class MainViewHolder {

        TextView devicesTextView;
        EditText searchEditText; // searchTextArea

        RecyclerView topPicksRV;


        public MainViewHolder(){

            devicesTextView = findViewById(R.id.devices_text_view);
            searchEditText = findViewById(R.id.search_text_input);
            searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    boolean handled = false;
                    if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                        devicesTextView.setText(searchEditText.getText());
                        handled = true;
                    }
                    String test =  vh.searchEditText.getText().toString().toLowerCase(Locale.ROOT);
                    Log.v("menuItemTest","Keyboard searchButton onClick");
                    Log.v("menuItemTest",test);
                    return handled;
                }
            });

            topPicksRV = (RecyclerView) findViewById(R.id.top_picks_recycler_view);





        }
        public String getCategoryText(View view) {

            MaterialCardView cardView = (MaterialCardView) view;
            LinearLayout layout = (LinearLayout) cardView.getChildAt(0);
            TextView textView = (TextView) layout.getChildAt(0);

            return (String) textView.getText();

        }

    }

    ImageScroller imageScroller, two;
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
        Log.v("menuItemTest","Main Activity onCreate");

    }
    //@Override
    //public boolean onCreateOptionsMenu(Menu menu){
     //   getMenuInflater().inflate(R.menu.top_app_bar, menu);
        //MenuItem menuItem = menu.findItem(R.id.search);
        //SearchView searchView = (SearchView) menuItem.getActionView();
       // return super.onCreateOptionsMenu(menu);
    //}

    //@Override
   // public boolean onOptionsItemSelected(MenuItem item) {
     //   int id = item.getItemId();
       // if (id == R.id.search){
         //   Intent intent = new Intent(this, MainActivity.class);
           // startActivity(intent);
            //return true;}
       // return super.onOptionsItemSelected(item);
   // }

    public void clickCategory(View v) {
        Intent listActivity = new Intent(getBaseContext(), ListActivity.class);
        MaterialCardView card = (MaterialCardView) v;
        LinearLayout layout = (LinearLayout) card.getChildAt(0);
        TextView textView = (TextView) layout.getChildAt(0);
        listActivity.putExtra("deviceType", textView.getText());
        Log.v("menuItemTest",textView.getText().toString());
        startActivity(listActivity);
    }


    public int getImageId(String prefix, int i) {
        return getResources().getIdentifier(prefix + "_" + String.valueOf(i), "drawable", getPackageName());
    }

    //Feature - Search
    //EditText searchTextArea = findViewById(R.id.search_text_input);

    public void clickSearchButton(MenuItem item) {
        String test =  vh.searchEditText.getText().toString().toLowerCase(Locale.ROOT);
        Log.v("menuItemTest","searchButton onClick");
        Log.v("menuItemTest",test);
        Intent searchActivity = new Intent(getBaseContext(), SearchActivity.class);
        startActivity(searchActivity);

   }

    //public void clickCategory(View view) {

        //vh.devicesTextView.setText(vh.getCategoryText(view));



    //}



    public void updateTopPicks() {
    }


}