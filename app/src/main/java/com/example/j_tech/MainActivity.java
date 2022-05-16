package com.example.j_tech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Device> topPicks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView topPicksRV = (RecyclerView) findViewById(R.id.top_picks_recycler_view);

        topPicks = DataProvider.generateTopPicks();

        TopPicksAdapter adapter = new TopPicksAdapter(topPicks);

        topPicksRV.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        topPicksRV.setLayoutManager(layoutManager);

    }
}