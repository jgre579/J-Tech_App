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
    int imageNum = 3;
    int activeDot = -1;
    class MainViewHolder {

        TextView devicesTextView;
        EditText searchEditText;
        RecyclerView topPicksRV;
        LinearLayout dotsLayout;
        ArrayList<ImageView> dots;
        ImageView currentTopPickView;
        int currentTopPickPosition;

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


            topPicksRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    LinearLayoutManager layoutManager = (LinearLayoutManager) topPicksRV.getLayoutManager();
                    currentTopPickPosition = layoutManager.findFirstVisibleItemPosition();
                    //LinearLayout layout = (LinearLayout) layoutManager.findViewByPosition(currentTopPickPosition);
                    //currentTopPickView = (ImageView) layout.getChildAt(0);

                    setImageDot(currentTopPickPosition);
                    currentTopPickView = findViewById(R.id.top_pick_image);
                    initCurrentImageView();
                    Log.d("click", currentTopPickView.toString());



                }
            });









        }
        public String getCategoryText(View view) {

            MaterialCardView cardView = (MaterialCardView) view;
            LinearLayout layout = (LinearLayout) cardView.getChildAt(0);
            TextView textView = (TextView) layout.getChildAt(0);

            return (String) textView.getText();

        }
        public void initCurrentImageView() {
            currentTopPickView.setClickable(true);
            currentTopPickView.setFocusable(true);
            Log.d("click", "Listner run");
            Log.d("click", currentTopPickView.getDrawable().toString());
            currentTopPickView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent detailsActivity = new Intent(getBaseContext(), DetailsActivity.class);
                    detailsActivity.putExtra("Device",  (Serializable) getTopPickByPosition(currentTopPickPosition));
                    startActivity(detailsActivity);
                    Log.d("click", "CLIKED");
                }
            });
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

    public Device getTopPickByPosition(int position){

        return topPicks.get(position);

    }

    public void setImageDot(int position){
        // Ensure that the dots arent being redrawn unnecessarily.
        if(activeDot != position){

            clearImageDots();
            vh.dots.get(position).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.dot_active));
            activeDot = position;

        }

    }

    public void clearImageDots(){
        for (int i = 0; i < imageNum; i++) {
            vh.dots.get(i).setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.dot_not_active));
        }
    }

    public void initImageDots() {

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
        setImageDot(0);
    }

    public void setDotsLayoutMargins(){

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
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
        SnapHelper helper = new LinearSnapHelper();
        helper.attachToRecyclerView(vh.topPicksRV);

    }

    public void clickCategory(View view) {

        vh.devicesTextView.setText(vh.getCategoryText(view));



    }



    public void updateTopPicks() {




    }


}