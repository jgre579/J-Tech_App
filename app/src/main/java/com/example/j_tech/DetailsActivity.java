package com.example.j_tech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

public class DetailsActivity extends AppCompatActivity {

    class DetailsViewHolder{


        ActionBar actionBar;

        public DetailsViewHolder() {
            setSupportActionBar(findViewById(R.id.toolbar));
            actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);


        }


    }
    DetailsViewHolder vh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        vh = new DetailsViewHolder();

        Intent intent = getIntent();
        Device device = (Device) intent.getSerializableExtra("Device");

        vh.actionBar.setTitle(device.getName());
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