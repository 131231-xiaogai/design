package com.example.newapplication.Adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newapplication.R;
import com.example.newapplication.entity.OrderBean;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

public class Order_tui_Adapter extends BaseRecyclerViewAdapter<OrderBean, RecyclerViewHolder> {
    public Order_tui_Adapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerViewHolder holder, OrderBean data, int position, int viewType) {
        TextView name = holder.getView(R.id.order_tui_goodname);
        name.setText(data.getGood_name());

        ImageView imageView = holder.getView(R.id.order_tui_img);
        Glide.with(mContext).load(data.getGood_img()).into(imageView);

        TextView price = holder.getView(R.id.order_tui_price);
        price.setText(data.getGood_price());

        TextView number = holder.getView(R.id.order_tui_goodNumber);
        number.setText(data.getGood_number());
        TextView total = holder.getView(R.id.order_tui_total);
        total.setText(data.getTotal_price());



    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.order_itemtui;
    }

    @Override
    protected int getViewType(int position, OrderBean data) {
        return 0;
    }
}
