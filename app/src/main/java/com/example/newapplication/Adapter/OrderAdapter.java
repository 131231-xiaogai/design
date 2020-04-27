package com.example.newapplication.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.newapplication.R;
import com.example.newapplication.entity.OrderBean;
import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.me.Order_fu;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.new_utill.SharePrefrenceUtil;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderAdapter extends BaseRecyclerViewAdapter<OrderBean, RecyclerViewHolder> {
    public OrderAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerViewHolder holder, OrderBean data, int position, int viewType) {
        TextView name = holder.getView(R.id.order_fa_goodname);
        name.setText(data.getGood_name());

        ImageView imageView = holder.getView(R.id.order_fa_img);
        Glide.with(mContext).load(data.getGood_img()).into(imageView);

        TextView price = holder.getView(R.id.order_fa_price);
        price.setText(data.getGood_price());

        TextView number = holder.getView(R.id.order_fa_goodNumber);
        number.setText(data.getGood_number());
        TextView total = holder.getView(R.id.order_fa_total);
        total.setText(data.getTotal_price());

        TextView fa =holder.getView(R.id.remark_fa);
        //顾客向商家提醒发货
        fa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = SharePrefrenceUtil.getObject(mContext, UsersBean.class).getUerid();//顾客id
                String title ="提醒发货";
                Map map = new HashMap();
                String order_id=data.getOrder_id();
                map.put("shop_id", data.getShop_id());//商家id
                map.put("message_title", title);
                map.put("message_context", "订单"+order_id+"请您及时发货呦~");
                map.put("message_status", "2");
                map.put("user_id", userID);
                map.put("message_type","2");//消息类型：1系统消息；2用户消息
                Log.d("id",title);
                OkHttp.get(mContext, Constant.insert_message, map, new OkCallback<Result<String>>() {
                    @Override
                    public void onResponse(Result<String> response) {
                        Toast.makeText(mContext, "已经通知商家。", Toast.LENGTH_SHORT).show();
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
        return R.layout.order_itemfa;
    }

    @Override
    protected int getViewType(int position, OrderBean data) {
        return 0;
    }
}
