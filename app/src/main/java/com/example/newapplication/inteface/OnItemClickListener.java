package com.example.psychologicalcounseling.inteface;

import com.example.psychologicalcounseling.utils.RecyclerViewHolder;

public interface OnItemClickListener<T> {
    void onItemClick(RecyclerViewHolder viewHolder, T data, int position);
}
