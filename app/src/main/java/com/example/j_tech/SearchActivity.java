package com.example.j_tech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class SearchActivity extends AppCompatActivity {
    List<Device> deviceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setSupportActionBar(findViewById(R.id.toolbar));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        //String deviceType = intent.getStringExtra("deviceType");
        //actionBar.setTitle(deviceType);
        actionBar.setTitle("Phones");
        if(deviceList != null) {
            deviceList.clear();
            Toast.makeText(this, String.valueOf(deviceList.size()), Toast.LENGTH_SHORT).show();
        }

        //deviceList = DataProvider.getDevices(deviceType);
        deviceList = DataProvider.getDevices("Phones");
        Toast.makeText(this, String.valueOf(deviceList.size()), Toast.LENGTH_SHORT).show();
        DeviceAdaptor itemsAdapter = new DeviceAdaptor(this, R.layout.list_view, deviceList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(itemsAdapter);


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