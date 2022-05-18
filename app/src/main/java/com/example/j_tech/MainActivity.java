package com.example.j_tech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MainViewHolder vh;

    class MainViewHolder {

        TextView devicesTextView;

        public MainViewHolder(){

            devicesTextView = findViewById(R.id.devices_text_view);

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

        RecyclerView topPicksRV = (RecyclerView) findViewById(R.id.top_picks_recycler_view);

        topPicks = DataProvider.generateTopPicks();

        TopPicksAdapter adapter = new TopPicksAdapter(topPicks);

        topPicksRV.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        topPicksRV.setLayoutManager(layoutManager);

    }

    public void clickCategory(View view) {

        vh.devicesTextView.setText(vh.getCategoryText(view));


    }


}