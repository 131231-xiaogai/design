package com.example.newapplication.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.newapplication.R;
import com.example.newapplication.entity.OrderBean;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

import java.util.HashMap;
import java.util.Map;

public class Order_evaluate_Adapter extends BaseRecyclerViewAdapter<OrderBean, RecyclerViewHolder> {
    public Order_evaluate_Adapter(Context context) {
        super(context);
    }
    private String order_id,good_id,user_id,shop_id;

    @Override
    protected void convert(RecyclerViewHolder holder, OrderBean data, int position, int viewType) {
        TextView name = holder.getView(R.id.order_tui_goodname);
        name.setText(data.getGood_name());
        ImageView imageView = holder.getView(R.id.order_tui_img);
        Glide.with(mContext).load(data.getGood_img()).into(imageView);
        TextView price = holder.getView(R.id.order_tui_price);
        price.setText("单价：￥"+data.getGood_price()+"  "+"押金是"+data.getGoods_yajin());
        TextView number = holder.getView(R.id.order_tui_goodNumber);
        number.setText("数量："+data.getGood_number());
        TextView total = holder.getView(R.id.order_tui_total);
        total.setText("总金额：￥"+data.getTotal_price());

        order_id=data.getOrder_id();
        good_id=data.getGoods_id();
        user_id=data.getUser_id();
        shop_id=data.getShop_id();

        TextView evaluate1=holder.getView(R.id.evaluate1);
        TextView evaluate2=holder.getView(R.id.evaluate2);
        TextView evaluate3=holder.getView(R.id.evaluate3);
        TextView evaluate4=holder.getView(R.id.evaluate4);
        TextView evaluate5=holder.getView(R.id.evaluate5);

        evaluate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p_content ="2";
                add_evaluate(p_content);
            }
        });
        evaluate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p_content ="4";
                add_evaluate(p_content);
            }
        });
        evaluate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p_content ="6";
                add_evaluate(p_content);
            }
        });
        evaluate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p_content ="8";
                add_evaluate(p_content);
            }
        });
        evaluate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p_content ="10";
                add_evaluate(p_content);
            }
        });
    }

    private void add_evaluate(String p_content) {
        Map map = new HashMap();
        map.put("good_id", good_id);
        map.put("shop_id", shop_id);
        map.put("user_id", user_id);
        map.put("order_id", order_id);
        map.put("p_content", p_content);

        OkHttp.get(mContext, Constant.add_order_p_content, map, new OkCallback<Result<String>>() {
            @Override
            public void onResponse(Result<String> response) {
                Log.d("顾客对该订单的评分",p_content);
                updata_order_evaluateStatus();
            }

            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updata_order_evaluateStatus() {
        Map map = new HashMap();
        map.put("order_id", order_id);
        map.put("evaluate_status", "1");//订单评价状态：1.已评价，0.未评价

        OkHttp.get(mContext, Constant.updata_Order_evaluateStatus, map, new OkCallback<Result<String>>() {
            @Override
            public void onResponse(Result<String> response) {
                Toast.makeText(mContext, "您的评价已提交。", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }



    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.order_item_evaluate;    }

    @Override
    protected int getViewType(int position, OrderBean data) {
        return 0;
    }
}
