package com.example.newapplication.Adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.newapplication.R;
import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

public class Select_userAdapter extends BaseRecyclerViewAdapter<UsersBean, RecyclerViewHolder> {
    public Select_userAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerViewHolder holder, UsersBean data, int position, int viewType) {

        TextView adm_name = holder.getView(R.id.adm_name);
        adm_name.setText(data.getNickname());

        TextView adm_userid = holder.getView(R.id.adm_user_id);
        adm_userid.setText(data.getUerid());

        TextView adm_phone = holder.getView(R.id.adm_phone);
        adm_phone.setText(data.getPhone());

        TextView adm_IDnumber = holder.getView(R.id.adm_IDnumber);
       // adm_IDnumber.setText(data.getId_number());

        TextView adm_sex = holder.getView(R.id.adm_sex);
        adm_sex.setText(data.getSex());

        TextView adm_balance = holder.getView(R.id.adm_balance);
        adm_balance.setText(data.getBalance());

    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.adm_uer_item;
    }

    @Override
    protected int getViewType(int position, UsersBean data) {
        return 0;
    }



}
