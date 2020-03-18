package com.example.newapplication.Adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newapplication.R;
import com.example.newapplication.entity.AddressBean;
import com.example.newapplication.entity.GoodBean;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

public class AddressAdapter extends BaseRecyclerViewAdapter<AddressBean, RecyclerViewHolder> {
    public AddressAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerViewHolder holder, AddressBean data, int position, int viewType) {
        TextView ma_name = holder.getView(R.id.ma_name);
        ma_name.setText(data.getContact_name());

        TextView ma_phone = holder.getView(R.id.ma_phone);
        ma_phone.setText(data.getContact_phone());

        TextView ma_adressdagai = holder.getView(R.id.ma_adressdagai);
        ma_adressdagai.setText(data.getAddress_total());

        TextView ma_adressdetial = holder.getView(R.id.ma_adressdetial);
        ma_adressdetial.setText(data.getAddress_detail());
    }
    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.m_address_item;
    }

    @Override
    protected int getViewType(int position, AddressBean data) {
        return 0;
    }

}
