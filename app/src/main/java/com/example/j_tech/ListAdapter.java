package com.example.j_tech;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.j_tech.R;
import com.example.j_tech.activities.DetailsActivity;
import com.example.j_tech.models.Device;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class ListAdapter extends ArrayAdapter {
    /**
     * Adapter class used by the list view in ListActivity
     *
     * Was not in design doc as we did not know how to implement a list view until we learned more
     * later on.
     */

    int mLayoutID;
    List<Device> mDevices;
    Context mContext;
    int lastPosition;

    class ViewHolder {

        TextView deviceNameTextView;
        TextView deviceYearTextView;
        ImageView deviceImageView;

        public ViewHolder(View v) {

            deviceNameTextView = (TextView) v.findViewById(R.id.list_view_device_name);
            deviceYearTextView = (TextView) v.findViewById(R.id.list_view_device_year);
            deviceImageView = (ImageView) v.findViewById(R.id.list_view_image);

        }

    }

    private static final int MAX_ANIMATION_DURATION = 1200;


    public ListAdapter(@NonNull Context context, int resource, @NonNull List<Device> objects){
        super(context,resource,objects);
        mLayoutID = resource;
        mContext = context;
        mDevices = objects;
        lastPosition = -1;

    }
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh;
        // If the view in the list does not exist create it and a a view holder for it.
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(mLayoutID, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }
        else {
            vh = (ViewHolder) convertView.getTag();
        }

        Device currentDevice = mDevices.get(position);
        // Set view to the right texts for the current device.
        vh.deviceNameTextView.setText(currentDevice.getName());
        vh.deviceYearTextView.setText(String.valueOf(currentDevice.getYear()));

        // Get the idea of the current devices first image, to show in the list view item.
        int id = mContext.getResources().getIdentifier(
                currentDevice.getImagePrefix() + "_1","drawable",
                mContext.getPackageName()
        );

        //setting the image
        vh.deviceImageView.setImageResource(id);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Increment the pick score so we know which devices are the most popular
                currentDevice.incrementPickScore();

                // Start the details activity and pass in a Device object so the DataProvider
                // does not need to be queried again.
                Intent detailsActivity = new Intent(mContext, DetailsActivity.class);
                detailsActivity.putExtra("Device", (Serializable) currentDevice);
                mContext.startActivity(detailsActivity);

            }
        });

        // Run the animations, staggered based on the current views position.
        // Check against a max duration so view off the screen don't take too long to animate in
        // when the screen is scrolled.

        if(position > lastPosition) {
            int duration = 700 + position * 50;
            if(duration > MAX_ANIMATION_DURATION) {
                duration = MAX_ANIMATION_DURATION;
            }
            setAnimation(convertView, duration );
            lastPosition = position;

        }
        return convertView;
    }

    private void setAnimation(View v, int duration) {

        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.push_up);
        animation.setDuration(duration);
        v.startAnimation(animation);

    }
}
