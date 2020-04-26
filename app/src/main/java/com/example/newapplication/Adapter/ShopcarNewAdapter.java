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

public class ShopcarNewAdapter extends BaseRecyclerViewAdapter<Shooping_carBean, RecyclerViewHolder> {

    public ShopcarNewAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerViewHolder holder, Shooping_carBean data, int position, int viewType) {

        ImageView ivIsSelect = (ImageView) holder.getView(R.id.iv_is_select);
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
        tvPrice.setText("￥"+data.getGood_price());
        tvCount.setText(data.getGood_number());

        holder.addOnClickListener(R.id.iv_is_select);
//        ivAdd.setEnabled(false);
        ivMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.valueOf(tvCount.getText().toString()) <= 1) {
                    Toast.makeText(mContext, "不能再减少了", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Map map = new HashMap();
                    map.put("id", data.getId());
                    map.put("count", Integer.valueOf(tvCount.getText().toString()) - 1 + "");
                    OkHttp.get(mContext, Constant.updateShopCount, map, new OkCallback<Result<String>>() {
                        @Override
                        public void onResponse(Result<String> response) {
                            tvCount.setText(Integer.valueOf(tvCount.getText().toString()) - 1 + "");
                            data.setGood_number(Integer.valueOf(tvCount.getText().toString()) - 1 + "");

                        }

                        @Override
                        public void onFailure(String state, String msg) {
                            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m_id =data.getId();
                Map map = new HashMap();
                map.put("id", m_id);
                map.put("count", Integer.valueOf(tvCount.getText().toString()) + 1 + "");
                Log.d("id",m_id);

                OkHttp.get(mContext, Constant.updateShopCount, map, new OkCallback<Result<String>>() {
                    @Override
                    public void onResponse(Result<String> response) {
                        tvCount.setText(Integer.valueOf(tvCount.getText().toString()) + 1 + "");
                        data.setGood_number(Integer.valueOf(tvCount.getText().toString()) + 1 + "");
                    }

                    @Override
                    public void onFailure(String state, String msg) {
                        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


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
                totalPrice = totalPrice + Double.valueOf(allDatum.getGood_number()) * Double.valueOf(allDatum.getGood_price());
            }
        }
        return totalPrice;
    }
}
