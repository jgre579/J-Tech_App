package com.example.j_tech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.jar.Attributes;

public class SearchActivity extends AppCompatActivity {
    private ListView listView;
    List<Device> deviceList;

    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

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
    private void initSearchWidgets(){
        SearchView searchView = (SearchView) findViewById(R.id.deviceListSearchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String d) {
                ArrayList<Device> filteredDevice = new ArrayList<Device>();
                for (Device device: deviceList)
                {
                    if(device.getName().toLowerCase().contains(d.toLowerCase()))
                    {
                        filteredDevice.add(device);
                    }
                }
                DeviceAdaptor adaptor = new DeviceAdaptor(getApplicationContext(),0, filteredDevice);
                listView.setAdapter(adaptor);

                return false;
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