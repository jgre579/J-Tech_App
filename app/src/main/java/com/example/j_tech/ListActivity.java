package com.example.j_tech;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ListActivity extends AppCompatActivity {
    List<Device> deviceList;


    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        setSupportActionBar(findViewById(R.id.toolbar));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String deviceType = intent.getStringExtra("deviceType");
        actionBar.setTitle(deviceType);
        if(deviceList != null) {
            deviceList.clear();
            Toast.makeText(this, String.valueOf(deviceList.size()), Toast.LENGTH_SHORT).show();
        }

        deviceList = DataProvider.getDevices(deviceType);
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