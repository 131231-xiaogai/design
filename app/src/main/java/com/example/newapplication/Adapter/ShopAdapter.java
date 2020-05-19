package com.example.newapplication.Adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.newapplication.R;
import com.example.newapplication.entity.AddressBean;
import com.example.newapplication.entity.ShopBean;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

public class ShopAdapter extends BaseRecyclerViewAdapter<ShopBean, RecyclerViewHolder> {
    public ShopAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerViewHolder holder, ShopBean data, int position, int viewType) {
        TextView ma_name = holder.getView(R.id.ho_find_shopname);
        ma_name.setText(data.getShop_name());

        TextView shop_id = holder.getView(R.id.ho_find_shop_id);
        shop_id.setText(data.getShop_id());

        TextView ma_phone = holder.getView(R.id.ho_find_shop_phone);
        ma_phone.setText(data.getShop_phone());

        TextView ma_adressdetial = holder.getView(R.id.ho_find_shop_address);
        ma_adressdetial.setText(data.getShop_dresss());

        TextView ho_find_shop_register_time = holder.getView(R.id.ho_find_shop_register_time);
        ho_find_shop_register_time.setText(data.getShop_regist_time());
    }
    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.shop_item;
    }

    @Override
    protected int getViewType(int position, ShopBean data) {
        return 0;
    }

}
