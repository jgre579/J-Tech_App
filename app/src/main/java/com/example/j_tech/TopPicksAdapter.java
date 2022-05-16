package com.example.j_tech;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TopPicksAdapter extends RecyclerView.Adapter<TopPicksAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public ViewHolder(View v){

            super(v);
            imageView = (ImageView) v.findViewById(R.id.top_pick_image);

        }


    }

    private List<Device> topPicks;

    public TopPicksAdapter(List<Device> topPicks){
        this.topPicks = topPicks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflator = LayoutInflater.from(context);

        View topPickItemView = inflator.inflate(R.layout.top_picks_item, parent, false);

        ViewHolder vh = new ViewHolder(topPickItemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Device pick = topPicks.get(position);
        ImageView imageView = holder.imageView;
        imageView.setImageResource(pick.getImageSrc());
    }

    @Override
    public int getItemCount() {
        return topPicks.size();
    }
}
