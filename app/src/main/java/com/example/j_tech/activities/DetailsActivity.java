package com.example.j_tech.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.j_tech.models.Device;
import com.example.j_tech.image_scroller.ImageScroller;
import com.example.j_tech.R;

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

        imageScroller = new ImageScroller(device.getImagePrefix(), this, vh.imagesRV);
        setDeviceDetails();
        createSpecsTable();

    }



    private void setDeviceDetails() {
        // Fill out device name, price, specs etc into the proper places.
        vh.actionBar.setTitle(device.getName());
        vh.titleTV.setText(device.getName());
        vh.yearTV.setText(String.valueOf(device.getYear()));
        String price = "$" + String.valueOf(device.getPrice());
        vh.priceTV.setText(price);
        vh.descriptionTV.setText(device.getDescription());
        vh.brandIV.setImageResource(getBrandImageId(device.getBrand()));


    }

    private int getBrandImageId(Device.Brand brand) {
        int id = getResources().getIdentifier(brand.name().toLowerCase() , "drawable", getPackageName());
        return id;
    }

    public void moreInfoClicked(View v) {
        Uri uri = Uri.parse(device.getMoreInfoLink());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        // Use try catch to prevent app from crashing if the intent can't be actioned, e.g the url link is not valid
        try {
            startActivity(intent);
        }
        catch (ActivityNotFoundException e) {
            // Display error message to user
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

    private void createSpecsTable() {

        if(device.getSpecs() != null) {

            for (Map.Entry<String, String> entry : device.getSpecs().entrySet()) {

                TableRow row = new TableRow(this);
                row.setPadding(5, 5, 5, 5);

                TextView key = new TextView(this);
                TextView value = new TextView(this);

                /* Populate the specs table with the keys and values from the
                devices specs hashmap. Each device could have any keys or values
                (phones include the camera key, while laptops don't). By implementing
                the table this way there is no hardcoding of values */

                key.setTextColor(getResources().getColor(R.color.off_black));
                value.setTextColor(getResources().getColor(R.color.off_black));

                key.setText(entry.getKey());
                value.setText(entry.getValue());

                row.addView(key);
                row.addView(value);
                vh.tableLayout.addView(row);

            }
        }
    }
}
