package com.example.newapplication.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.newapplication.R;
import com.example.newapplication.entity.Shooping_carBean;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

import java.util.HashMap;
import java.util.Map;

public class Submit_OrderAdapter extends BaseRecyclerViewAdapter<Shooping_carBean, RecyclerViewHolder> {

    public Submit_OrderAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerViewHolder holder, Shooping_carBean data, int position, int viewType) {

        ImageView ivIsSelect = (ImageView) holder.getView(R.id.iv_is_select);
        ivIsSelect.setVisibility(View.GONE);
        ImageView ivShop = (ImageView) holder.getView(R.id.iv_shop);
        TextView tvShopName = (TextView) holder.getView(R.id.tv_shop_name);
        TextView tvPrice = (TextView) holder.getView(R.id.tv_price);
        ImageView ivMinus = (ImageView) holder.getView(R.id.iv_minus);
        TextView tvCount = (TextView) holder.getView(R.id.tv_count);
        ImageView ivAdd = (ImageView) holder.getView(R.id.iv_add);


        if (data.isSelect()) {
            ivIsSelect.setImageResource(R.mipmap.select);
        } else {
            ivIsSelect.setImageResource(R.mipmap.unselect);
        }
        Glide.with(mContext).load(data.getGood_img()).into(ivShop);
        tvShopName.setText(data.getGood_name());
        tvPrice.setText("单价：￥"+data.getGood_price()+"  "+"押 金："+data.getGoods_yajin());
        tvCount.setText(data.getGood_number());
        holder.addOnClickListener(R.id.iv_is_select);
        ivAdd.setEnabled(false);
        ivMinus.setEnabled(false);

    }


    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_shop_card;
    }

    @Override
    protected int getViewType(int position, Shooping_carBean data) {
        return 0;
    }

    /**
     * 计算合计金额
     *
     * @return
     */
    public double totalPrice() {
        double totalPrice = 0;
        for (Shooping_carBean allDatum : getAllData()) {
            if (allDatum.isSelect()) {
                totalPrice = totalPrice + Double.valueOf(allDatum.getGood_number()) * Double.valueOf(allDatum.getGood_price())+Double.valueOf(allDatum.getGoods_yajin());
            }
        }
        return totalPrice;
    }
}
