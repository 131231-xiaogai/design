package com.example.newapplication.inteface;

import android.view.View;

import com.example.newapplication.Adapter.BaseRecyclerViewAdapter;


public interface OnItemChildClickListener {
    void onItemChildClick(BaseRecyclerViewAdapter adapter, View view, int position);
}
