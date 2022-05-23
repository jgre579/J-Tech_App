package com.example.j_tech;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.List;

public class DeviceAdaptor extends ArrayAdapter {


    int mLayoutID;
    List<Device> mDevices;
    Context mContext;
    View.OnClickListener listener;

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


    public DeviceAdaptor(@NonNull Context context, int resource, @NonNull List<Device> objects){
        super(context,resource,objects);
        mLayoutID = resource;
        mContext = context;
        mDevices = objects;

    }
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        View v = convertView;
        ViewHolder holder;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(mLayoutID, null);
            holder = new ViewHolder(v);
            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }

        Device currentDevice = mDevices.get(position);
        holder.deviceNameTextView.setText(currentDevice.getName());
        holder.deviceYearTextView.setText(String.valueOf(currentDevice.getYear()));


        int id = mContext.getResources().getIdentifier(
                currentDevice.getImagePrefix() + "_1","drawable",
                mContext.getPackageName()
        );

        //setting the image
        holder.deviceImageView.setImageResource(id);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentDevice.incrementPickScore();
                Intent detailsActivity = new Intent(mContext, DetailsActivity.class);
                detailsActivity.putExtra("Device", (Serializable) currentDevice);
                mContext.startActivity(detailsActivity);
                Log.d("click", "CLICKED");
            }
        });


        return v;

    }

}
