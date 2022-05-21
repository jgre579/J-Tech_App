package com.example.j_tech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class DetailsActivity extends AppCompatActivity {

    class DetailsViewHolder{


        ActionBar actionBar;
        RecyclerView imagesRV;

        public DetailsViewHolder() {
            setSupportActionBar(findViewById(R.id.toolbar));
            actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            imagesRV = findViewById(R.id.details_rv);


        }


    }
    DetailsViewHolder vh;
    ImageScroller imageScroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        vh = new DetailsViewHolder();

        Intent intent = getIntent();
        Device device = (Device) intent.getSerializableExtra("Device");
        //ArrayList<Integer> a = new ArrayList<>(Arrays.asList(R.drawable.laptop_category, R.drawable.tablet_category, R.drawable.phone_category));
        vh.actionBar.setTitle(device.getName());
        Toast.makeText(this, device.getImageSrcs().toString(), Toast.LENGTH_SHORT).show();
        imageScroller = new ImageScroller(device.getImageSrcs(), this, vh.imagesRV, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}