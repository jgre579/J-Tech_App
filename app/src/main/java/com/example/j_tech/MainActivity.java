package com.example.j_tech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MainViewHolder vh;

    class MainViewHolder {

        TextView devicesTextView;
        EditText searchEditText;
        RecyclerView topPicksRV;
        LinearLayout dotsLayout;
        ArrayList<ImageView> dots;

        public MainViewHolder(){

            dotsLayout = findViewById(R.id.image_dots_layout);
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
            dots = new ArrayList<ImageView>();


        }
        public String getCategoryText(View view) {

            MaterialCardView cardView = (MaterialCardView) view;
            LinearLayout layout = (LinearLayout) cardView.getChildAt(0);
            TextView textView = (TextView) layout.getChildAt(0);

            return (String) textView.getText();

        }
    }


    ArrayList<Device> topPicks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vh = new MainViewHolder();

        initTopPicks();
        initImageDots();
    }

    public void initImageDots() {
        int imageNum = 3;
        for (int i = 0; i < imageNum; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.dot_not_active));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.weight = 1.0f;
            vh.dotsLayout.addView(imageView);
            imageView.setLayoutParams(params);
            vh.dots.add(imageView);
        }
        setDotsLayoutMargins();
        vh.dots.get(0).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.dot_active));
    }

    public void setDotsLayoutMargins(){

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        Log.d("TAG", String.valueOf(width));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.rightMargin = width/3;
        params.leftMargin = width/3;
        vh.dotsLayout.setLayoutParams(params);


    }

    public void initTopPicks() {

        topPicks = DataProvider.generateTopPicks();
        TopPicksAdapter adapter = new TopPicksAdapter(topPicks);
        vh.topPicksRV.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        vh.topPicksRV.setLayoutManager(layoutManager);

    }

    public void clickCategory(View view) {

        vh.devicesTextView.setText(vh.getCategoryText(view));


    }

    public void updateTopPicks() {




    }


}