package com.example.newapplication.Adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newapplication.R;
import com.example.newapplication.entity.GoodBean;
import com.example.newapplication.entity.Shooping_carBean;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

public class ShopcarAdapter extends BaseRecyclerViewAdapter<Shooping_carBean, RecyclerViewHolder> {
    public ShopcarAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerViewHolder holder, Shooping_carBean data, int position, int viewType) {
        ImageView imageView = holder.getView(R.id.sc_good_image);
        // imageView.setIm(data.getGood_img());
       // Glide.with(mContext).load(data.getGood_img()).into(imageView);

       // TextView textView = holder.getView(R.id.sc_shopname);
       // textView.setText(data.getGoods_name());

       // TextView price = holder.getView(R.id.sc_goood_price);
        //price.setText(data.getGoods_price());

        TextView good_number = holder.getView(R.id.sc_good_number);
        good_number.setText(data.getGoods_id());
    }


    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.shop_list;
    }

    @Override
    protected int getViewType(int position, Shooping_carBean data) {
        return 0;
    }


}
