package com.example.j_tech;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class ListActivity extends AppCompatActivity {


    class ViewHolder {


        ActionBar actionBar;
        LinearLayout noResultsLayout;

        public ViewHolder() {
            setSupportActionBar(findViewById(R.id.toolbar));
            actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            noResultsLayout = findViewById(R.id.list_no_results_layout);
        }

    }

    ViewHolder vh;
    List<Device> deviceList;


    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        vh = new ViewHolder();
        Intent intent = getIntent();

        if(Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String searchQuery = intent.getStringExtra(SearchManager.QUERY);
            searchDevices(searchQuery);
        }
        else {
            String deviceType = intent.getStringExtra("deviceType");
            listDevices(deviceType);
        }

    }

    public void searchDevices(String searchQuery) {
        vh.actionBar.setTitle("Results for"+ " "+ '"' +searchQuery +'"');
        Search search = new Search(searchQuery, DataProvider.getAllDevices());
        List<Device> searchedDevices = search.searchNames();

        if(searchedDevices.isEmpty()) {
            // Display No Results
            vh.noResultsLayout.setVisibility(View.VISIBLE);
        }
        else {
            // Display found results
            vh.noResultsLayout.setVisibility(View.GONE);
            setListAdapter(searchedDevices);
        }



    }

    public void listDevices(String deviceType) {
        vh.noResultsLayout.setVisibility(View.GONE);
        vh.actionBar.setTitle(deviceType);
        if(deviceList != null) {
            deviceList.clear();
        }

        deviceList = DataProvider.getDevices(deviceType);
        setListAdapter(deviceList);


    }

    public void setListAdapter(List<Device> devices) {

        DeviceAdaptor itemsAdapter = new DeviceAdaptor(this, R.layout.list_view, devices);
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