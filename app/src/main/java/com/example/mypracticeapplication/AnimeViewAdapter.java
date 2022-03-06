package com.example.mypracticeapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AnimeViewAdapter extends RecyclerView.Adapter<AnimeViewAdapter.ViewHolder> {
    ArrayList rating, name, images;
    Context context;
    public AnimeViewAdapter(Context context, ArrayList rating, ArrayList name, ArrayList images) {
        this.context = context;
        this.rating  = rating;
        this.name = name;
        this.images = images;
    }


    @NonNull
    @Override
    public AnimeViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.anime_single_item, parent, false);
        AnimeViewAdapter.ViewHolder viewHolder = new AnimeViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    // Binding data to the into specified position
    @Override
    public void onBindViewHolder(@NonNull AnimeViewAdapter.ViewHolder holder, int position) {
        // TypeCast Object to int type
//        int res = (int) courseImg.get(position);
//        holder.images.setImageResource(res);
          holder.anime_name.setText((String) name.get(position));
          String set_rating = (String)rating.get(position) + "/10";
          holder.rating.setText(set_rating);
          Glide.with(this.context).load(images.get(position)).into(holder.anime_image);
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    // Initializing the Views
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView anime_name;
        TextView rating;
        ImageView anime_image;
        public ViewHolder(View view) {
            super(view);
            anime_name = (TextView) view.findViewById(R.id.anime_name);
            rating = (TextView) view.findViewById(R.id.anime_rating);
            anime_image = view.findViewById(R.id.anime_image);
        }
    }
}

