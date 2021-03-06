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
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.new_utill.SharePrefrenceUtil;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

import java.util.HashMap;
import java.util.Map;

public class Sk_Order_fuAdapter extends BaseRecyclerViewAdapter<OrderBean, RecyclerViewHolder> {
    public Sk_Order_fuAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerViewHolder holder, OrderBean data, int position, int viewType) {
        TextView name = holder.getView(R.id.sk_order_fu_goodname);
        name.setText("#"+data.getGood_name()+"#");

        TextView tiame =holder.getView(R.id.sk_order_fu_goodNumber);
        tiame.setText("订单于："+data.getOrder_rent_finesh_time()+"结束");

        ImageView imageView = holder.getView(R.id.sk_order_fu_img);
        Glide.with(mContext).load(data.getGood_img()).into(imageView);

        TextView price = holder.getView(R.id.sk_order_fu_price);
        price.setText("租金/件：￥"+data.getGood_price()+"/ 押 金："+data.getGoods_yajin()+"/ 数 量："+data.getGood_number());

        TextView total = holder.getView(R.id.sk_order_fu_total);
        total.setText("订单总金额:￥"+data.getTotal_price());
        TextView tixing = holder.getView(R.id.tixing_fukuan);

        tixing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = SharePrefrenceUtil.getObject(mContext, UsersBean.class).getUerid();//商家id
                String title ="提醒用户及时付款";
                Map map = new HashMap();
                String order_id=data.getOrder_id();
                map.put("shop_id", data.getShop_id());//商家id
                map.put("message_title", title);
                map.put("message_context", "订单"+order_id+"请您及时付款呦~");
                map.put("message_status", "1");//消息状态：1顾客要读的信息；2.商家要读的信息
                map.put("user_id",data.getUser_id());
                map.put("message_type","2");//消息类型：1系统消息；2用户消息
                Log.d("id",title);
                OkHttp.get(mContext, Constant.insert_message, map, new OkCallback<Result<String>>() {
                    @Override
                    public void onResponse(Result<String> response) {
                        Toast.makeText(mContext, "已经通知顾客。", Toast.LENGTH_SHORT).show();
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
        return R.layout.sk_order_itemfu;
    }//

    @Override
    protected int getViewType(int position, OrderBean data) {
        return 0;
    }
}
