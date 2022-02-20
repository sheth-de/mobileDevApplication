package com.example.mypracticeapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RviewAdapter extends RecyclerView.Adapter<RviewHolder>{
    private final ArrayList<LinkItem> itemList;
    private ItemClickListener listener;

    //Constructor
    public RviewAdapter(ArrayList<LinkItem> itemList) {
        this.itemList = itemList;
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_link_item, parent, false);
        return new RviewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(RviewHolder holder, int position) {
        LinkItem currentItem = itemList.get(position);
        holder.name.setText(currentItem.getName());
        holder.url.setText(currentItem.getUrl());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

}
