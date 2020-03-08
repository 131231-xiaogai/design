package com.example.psychologicalcounseling.inteface;

import com.example.psychologicalcounseling.utils.RecyclerViewHolder;

/**
 * 条目长按点击事件
 */
public interface OnItemLongClickListener<T> {
    void onItemLongClick(RecyclerViewHolder viewHolder, T data, int position);
}
