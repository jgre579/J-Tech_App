package com.example.j_tech.image_scroller;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.j_tech.R;

import java.util.List;

public class ImageScrollerAdapter extends RecyclerView.Adapter<ImageScrollerAdapter.ViewHolder> {
    /**
     * Adapter class used by the recycle views in ImageScroller
     *
     * Was not in design doc as we did not know how to implement a recycle view until we learned more
     * later on.
     */

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textView;

        public ViewHolder(View v){

            super(v);
            imageView = (ImageView) v.findViewById(R.id.scroller_image);
            textView = (TextView) v.findViewById(R.id.scroller_text);

        }


    }

    ViewHolder vh;

    private List<Integer> images;
    private List<String> texts;


    public ImageScrollerAdapter(List<Integer> images){
        this.images = images;

    }

    public void setText(List<String> texts) {
        this.texts = texts;
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
        ImageView imageView = holder.imageView;
        imageView.setImageResource(images.get(position));

        // If texts are used set the text other wise make the textView take up no space.
        if (texts != null) {
            holder.textView.setText(texts.get(position));
        }
        else {
            holder.textView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return images.size();
    }


}
