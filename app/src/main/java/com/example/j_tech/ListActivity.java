package com.example.j_tech;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class ListActivity extends AppCompatActivity {
    List<Device> deviceList;
    DeviceAdaptor deviceAdaptor;
    ActionBar actionBar;

    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        setSupportActionBar(findViewById(R.id.toolbar));
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Log.d("search123", "intent loaded");
        if(Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String searchQuery = intent.getStringExtra(SearchManager.QUERY);
            Log.d("search123", "search if entered");
            searchDevices(searchQuery);
        }
        else {
            String deviceType = intent.getStringExtra("deviceType");
            listDevices(deviceType);
        }




    }

    public void searchDevices(String searchQuery) {

        Log.d("search123", "List search");

    }

    public void listDevices(String deviceType) {
        actionBar.setTitle(deviceType);
        if(deviceList != null) {
            deviceList.clear();
        }

        deviceList = DataProvider.getDevices(deviceType);

        DeviceAdaptor itemsAdapter = new DeviceAdaptor(this, R.layout.list_view, deviceList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(itemsAdapter);

    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//        getMenuInflater().inflate(R.menu.options_menu,menu);
//        MenuItem menuItem =menu.findItem(R.id.sear);
//        SearchView searchView = (SearchView) menuItem.getActionView();
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                deviceAdaptor.getFilter().filter(s.toString());
//                return false;
//            }
//        });
//
//        return super.onCreateOptionsMenu(menu);
//    }




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