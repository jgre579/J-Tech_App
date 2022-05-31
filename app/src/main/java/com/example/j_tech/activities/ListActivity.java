package com.example.j_tech.activities;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.j_tech.DataProvider;
import com.example.j_tech.models.Device;
import com.example.j_tech.ListAdapter;
import com.example.j_tech.R;
import com.example.j_tech.utils.Search;

import java.util.List;


public class ListActivity extends AppCompatActivity {


    class ListViewHolder {

        ActionBar actionBar;
        LinearLayout noResultsLayout;

        public ListViewHolder() {
            setSupportActionBar(findViewById(R.id.toolbar));
            actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            noResultsLayout = findViewById(R.id.list_no_results_layout);
        }
    }

    ListViewHolder vh;
    List<Device> deviceList;


    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        vh = new ListViewHolder();
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

    private void searchDevices(String searchQuery) {
        vh.actionBar.setTitle("Results for \"" + searchQuery + "\"");
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

    private void listDevices(String deviceType) {

        vh.noResultsLayout.setVisibility(View.GONE);
        vh.actionBar.setTitle(deviceType);
        if(deviceList != null) {
            deviceList.clear();
        }

        deviceList = DataProvider.getDevices(deviceType);
        setListAdapter(deviceList);


    }

    private void setListAdapter(List<Device> devices) {

        ListAdapter itemsAdapter = new ListAdapter(this, R.layout.list_view_item, devices);
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