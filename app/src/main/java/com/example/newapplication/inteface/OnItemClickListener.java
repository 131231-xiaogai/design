package com.example.newapplication.inteface;


import com.example.newapplication.viewhandle.RecyclerViewHolder;

public interface OnItemClickListener<T> {
    void onItemClick(RecyclerViewHolder viewHolder, T data, int position);
}
