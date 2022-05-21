package com.example.j_tech;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ImageScrollerAdapter extends RecyclerView.Adapter<ImageScrollerAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public ViewHolder(View v){

            super(v);
            imageView = (ImageView) v.findViewById(R.id.scroller_image);

        }


    }

    ViewHolder vh;

    private List<Integer> images;


    public ImageScrollerAdapter(List<Integer> images){
        this.images = images;


    }



    public ImageView getImageView(){
        return vh.imageView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View topPickItemView = inflater.inflate(R.layout.image_scroller_item, parent, false);

        vh = new ViewHolder(topPickItemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Integer pick = images.get(position);
        ImageView imageView = holder.imageView;
        imageView.setImageResource(images.get(position));
        Log.d("TAGON", String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }


}
