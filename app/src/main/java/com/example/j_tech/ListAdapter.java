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

public class ListAdapter extends ArrayAdapter implements Filterable {


    int mLayoutID;
    List<Device> mDevices;
    List<Device> listFull;
    Context mContext;
    View.OnClickListener listener;
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

    public ListAdapter(@NonNull Context context, int resource, @NonNull List<Device> objects){
        super(context,resource,objects);
        mLayoutID = resource;
        mContext = context;
        mDevices = objects;
        lastPosition = -1;
        listFull =  new ArrayList<>(mDevices);

    }
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh;
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(mLayoutID, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }
        else {
            vh = (ViewHolder) convertView.getTag();
        }

        Device currentDevice = mDevices.get(position);
        vh.deviceNameTextView.setText(currentDevice.getName());
        vh.deviceYearTextView.setText(String.valueOf(currentDevice.getYear()));


        int id = mContext.getResources().getIdentifier(
                currentDevice.getImagePrefix() + "_1","drawable",
                mContext.getPackageName()
        );

        //setting the image
        vh.deviceImageView.setImageResource(id);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentDevice.incrementPickScore();
                Intent detailsActivity = new Intent(mContext, DetailsActivity.class);
                detailsActivity.putExtra("Device", (Serializable) currentDevice);
                mContext.startActivity(detailsActivity);
                Log.d("click", "CLICKED");
            }
        });

        if(position > lastPosition) {
            setAnimation(convertView, 700 + position * 50);
            lastPosition = position;

        }
        return convertView;

    }

    private void setAnimation(View v, int duration) {

        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.push_up);
        animation.setDuration(duration);
        v.startAnimation(animation);

    }

    @Override
    public Filter getFilter(){
        return FilterDevice;
    }
    private Filter FilterDevice = new Filter(){
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String searchText = constraint.toString().toLowerCase(Locale.ROOT);
            List<Device> tempList = new ArrayList<>();
            if(searchText.length()==0 || searchText.isEmpty()){
                tempList.addAll(listFull);
            }
            else{
                for(Device device :listFull){
                    if(device.getName().toLowerCase(Locale.ROOT).contains(searchText))
                    {
                        tempList.add(device);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = tempList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mDevices.clear();
            mDevices.addAll((Collection<? extends Device>) results.values);
            notifyDataSetChanged();
        }
    };

}
