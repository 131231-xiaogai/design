package com.example.newapplication.Adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newapplication.R;
import com.example.newapplication.entity.GoodBean;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

public class GoodAdapter extends BaseRecyclerViewAdapter<GoodBean, RecyclerViewHolder> {
    public GoodAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerViewHolder holder, GoodBean data, int position, int viewType) {
        ImageView imageView=holder.getView(R.id.h_photo_image);
        TextView textView=holder.getView(R.id.h_photo_name);
        textView.setText(data.getGoods_name());
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.home_list;
    }

    @Override
    protected int getViewType(int position, GoodBean data) {
        return 0;
    }
}
