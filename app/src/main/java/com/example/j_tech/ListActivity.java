package com.example.j_tech;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ListActivity extends AppCompatActivity {

    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        List<Device> deviceList = DataProvider.getDevices();
        DeviceAdaptor itemsAdapter = new DeviceAdaptor(this, R.layout.list_view, deviceList);

        ListView listView = (ListView) findViewById(androidx.appcompat.R.id.list_item);
        listView.setAdapter(itemsAdapter);
    }
}