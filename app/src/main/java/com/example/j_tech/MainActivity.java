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
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MainViewHolder vh;

    class MainViewHolder {

        TextView devicesTextView;
        EditText searchEditText;
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



    //public void clickCategory(View view) {

        //vh.devicesTextView.setText(vh.getCategoryText(view));



    //}



    public void updateTopPicks() {




    }


}