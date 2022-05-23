package com.example.j_tech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class DetailsActivity extends AppCompatActivity {

    class DetailsViewHolder{


        ActionBar actionBar;
        RecyclerView imagesRV;
        TableLayout tableLayout;
        TextView titleTV;
        TextView yearTV;
        TextView priceTV;
        TextView descriptionTV;
        Button moreInfoButton;
        ImageView brandIV;


        public DetailsViewHolder() {
            setSupportActionBar(findViewById(R.id.toolbar));
            actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);

            imagesRV = findViewById(R.id.details_rv);
            tableLayout = findViewById(R.id.details_table);
            titleTV = findViewById(R.id.details_title);
            yearTV = findViewById(R.id.details_year);
            priceTV = findViewById(R.id.details_price);
            descriptionTV = findViewById(R.id.details_description);
            moreInfoButton = findViewById(R.id.details_more_info_button);
            brandIV = findViewById(R.id.details_brand_image);


        }


    }
    DetailsViewHolder vh;
    ImageScroller imageScroller;
    Device device;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        vh = new DetailsViewHolder();

        Intent intent = getIntent();
        device = (Device) intent.getSerializableExtra("Device");
        //ArrayList<Integer> a = new ArrayList<>(Arrays.asList(R.drawable.laptop_category, R.drawable.tablet_category, R.drawable.phone_category));
        vh.actionBar.setTitle(device.getName());

        imageScroller = new ImageScroller(device.getImagePrefix(), this, vh.imagesRV, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        setDeviceDetails();


    }



    public void setDeviceDetails() {
        // Fill out device name, price, specs etc into the proper views.
        vh.titleTV.setText(device.getName());
        vh.yearTV.setText(String.valueOf(device.getYear()));
        vh.priceTV.setText("$" + String.valueOf(device.getPrice()));
        vh.descriptionTV.setText(device.getDescription());
        vh.brandIV.setImageResource(device.getBrandImageSrc());
        createTable();

    }

    public void moreInfoClicked(View v) {


        Uri uri = Uri.parse(device.getMoreInfoLink());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(intent);
        }
        catch (ActivityNotFoundException e) {

            Toast.makeText(this, "Link Not Available", Toast.LENGTH_SHORT).show();

        }


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

    public void createTable() {

        if(device.getSpecs() != null) {

            for (Map.Entry<String, String> entry : device.getSpecs().entrySet()) {

                TableRow row = new TableRow(this);

                TableRow.LayoutParams rowParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);


                //row.setLayoutParams(rowParams);

                TextView key = new TextView(this);
                TextView value = new TextView(this);

                row.setPadding(5, 5, 5, 5);



                key.setTextColor(getResources().getColor(R.color.off_black));
                value.setTextColor(getResources().getColor(R.color.off_black));

                key.setText(entry.getKey());
                value.setText(entry.getValue());
                row.addView(key);
                row.addView(value);
                vh.tableLayout.addView(row);

                Log.d("device", entry.getKey());


            }

        }




    }
}
