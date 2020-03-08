package com.example.newapplication.inteface;

import com.example.newapplication.viewhandle.RecyclerViewHolder;


/**
 * 条目长按点击事件
 */
public interface OnItemLongClickListener<T> {
    void onItemLongClick(RecyclerViewHolder viewHolder, T data, int position);
}
