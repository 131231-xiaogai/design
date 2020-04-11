package com.example.newapplication.Adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newapplication.R;
import com.example.newapplication.entity.GoodBean;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

public class Shopkeeper_goodAdapter extends BaseRecyclerViewAdapter<GoodBean, RecyclerViewHolder> {
    public Shopkeeper_goodAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerViewHolder holder, GoodBean data, int position, int viewType) {


        ImageView imageView = holder.getView(R.id.skd_good_image);
        // imageView.setIm(data.getGood_img());
        Glide.with(mContext).load(data.getGood_img()).into(imageView);

        TextView textView = holder.getView(R.id.kd_goodname);
        textView.setText(data.getGoods_name());

        TextView price = holder.getView(R.id.kd_goood_price);
        price.setText(data.getGoods_price());

        TextView goodid = holder.getView(R.id.skd_goods_id);
        goodid.setText(data.getGoods_id());

        TextView kd_goood_yajin =holder.getView(R.id.kd_goood_yajin);
        kd_goood_yajin.setText(data.getGoods_yajin());

        TextView kd_goood_type =holder.getView(R.id.kd_goood_type);
        kd_goood_type.setText(data.getGoods_yajin());

        TextView kd_good_number =holder.getView(R.id.kd_good_number);
        kd_good_number.setText(data.getGoods_number());


    }


    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.sk_deletedgood_item;
    }

    @Override
    protected int getViewType(int position, GoodBean data) {
        return 0;
    }
}
