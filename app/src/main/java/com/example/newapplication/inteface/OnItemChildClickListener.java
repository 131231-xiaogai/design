package com.example.psychologicalcounseling.inteface;

import android.view.View;

import com.example.psychologicalcounseling.utils.BaseRecyclerViewAdapter;

public interface OnItemChildClickListener {
    void onItemChildClick(BaseRecyclerViewAdapter adapter, View view, int position);
}
