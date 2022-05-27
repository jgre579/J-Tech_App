package com.example.j_tech;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
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
    DeviceAdaptor deviceAdaptor;


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

    //public boolean onCreateOptionMenu(Menu menu){
      //  getMenuInflater().inflate(R.menu.top_app_bar,menu);
        //MenuItem menuItem =menu.findItem(R.id.search);
        //SearchView searchView = (SearchView) menuItem.getActionView();

        //searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

          //  @Override
           // public boolean onQueryTextSubmit(String query) {
             //   return false;
           // }

            //@Override
            //public boolean onQueryTextChange(String s) {
                //deviceAdaptor.getFilter().filter(s.toString());
                //return false;
            //}
        //});

       // return super.onCreateOptionsMenu(menu);
    //}




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