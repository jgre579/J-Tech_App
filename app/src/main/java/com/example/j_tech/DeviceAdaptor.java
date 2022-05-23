package com.example.j_tech;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class DeviceAdaptor extends ArrayAdapter {


    int mLayoutID;
    List<Device> mDevices;
    Context mContext;

    public DeviceAdaptor(@NonNull Context context, int resource, @NonNull List<Device> objects){
        super(context,resource,objects);
        mLayoutID = resource;
        mContext = context;
        mDevices = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        //reference to the current ListView item
        View currentListViewItem = convertView;

        //if no current ListView, inflate the view
        if (currentListViewItem == null) {
            currentListViewItem = LayoutInflater.from(getContext()).inflate(mLayoutID, parent,false);

        }
        // get the Device for the current position
        Device currentDevice = mDevices.get(position);

        //Set the Attributed of list_view
        TextView deviceNameTextView = (TextView) currentListViewItem.findViewById(R.id.list_view_device_name);
        deviceNameTextView.setText(currentDevice.getName());

        TextView deviceYearTextView = (TextView) currentListViewItem.findViewById(R.id.list_view_device_year);
        deviceYearTextView.setText(String.valueOf(currentDevice.getYear()));

        ImageView deviceImageView = (ImageView) currentListViewItem.findViewById(R.id.list_view_image);
        int id = mContext.getResources().getIdentifier(
                currentDevice.getImagePrefix() + "_1","drawable",
                mContext.getPackageName()
        );

        //setting the image
        deviceImageView.setImageResource(id);





        return currentListViewItem;
    }
}
