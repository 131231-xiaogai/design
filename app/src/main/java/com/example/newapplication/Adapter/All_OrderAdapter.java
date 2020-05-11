package com.example.newapplication.Adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newapplication.R;
import com.example.newapplication.entity.OrderBean;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

public class All_OrderAdapter extends BaseRecyclerViewAdapter<OrderBean, RecyclerViewHolder> {
    public All_OrderAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerViewHolder holder, OrderBean data, int position, int viewType) {

        TextView name = holder.getView(R.id.order_all_goodname);
        name.setText("#"+data.getGood_name()+"#");

        TextView tiame =holder.getView(R.id.order_all_goodNumber);
        tiame.setText("订单结束时间："+data.getOrder_rent_finesh_time());

        ImageView imageView = holder.getView(R.id.order_all_img);
        Glide.with(mContext).load(data.getGood_img()).into(imageView);

        TextView price = holder.getView(R.id.order_all_price);
        price.setText("租金/件:￥"+data.getGood_price()+"/ 数 量:"+data.getGood_number());

        TextView total = holder.getView(R.id.order_all_total);
        total.setText("租 金:￥"+data.getGoods_yajin()+"/ 总金额：￥"+data.getTotal_price());


    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.all_order_item;
    }

    @Override
    protected int getViewType(int position, OrderBean data) {
        return 0;
    }
}
