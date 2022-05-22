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

import com.google.android.material.card.MaterialCardView;

import java.io.Serializable;
import java.util.ArrayList;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vh = new MainViewHolder();

        ArrayList<Integer> images = new ArrayList<>();
        topPicks = new ArrayList<>();

        topPicks = DataProvider.generateTopPicks();

        for (Device device : topPicks) {
            // Get first image of every top pick
            String prefix = device.getImagePrefix();
            int id = getImageId(prefix, 1);
            images.add(id);

        }

        View.OnClickListener topPickClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent detailsActivity = new Intent(getBaseContext(), DetailsActivity.class);
                detailsActivity.putExtra("Device",  (Serializable) topPicks.get(imageScroller.getActiveDot()));
                startActivity(detailsActivity);
                Log.d("click", "CLIKED");
            }

        };

       imageScroller = new ImageScroller(images, this, vh.topPicksRV, topPickClickListener);




    }

    public int getImageId(String prefix, int i) {
        return getResources().getIdentifier(prefix + "_" + String.valueOf(i), "drawable", getPackageName());
    }



    public void clickCategory(View view) {

        vh.devicesTextView.setText(vh.getCategoryText(view));



    }



    public void updateTopPicks() {




    }


}