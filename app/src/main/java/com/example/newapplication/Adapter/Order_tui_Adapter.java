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

public class Order_tui_Adapter extends BaseRecyclerViewAdapter<OrderBean, RecyclerViewHolder> {
    public Order_tui_Adapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerViewHolder holder, OrderBean data, int position, int viewType) {
        TextView name = holder.getView(R.id.order_tui_goodname);
        name.setText("#"+data.getGood_name()+"#");

        TextView tiame =holder.getView(R.id.order_tui_goodNumber);
        tiame.setText("订单于："+data.getOrder_rent_finesh_time()+"结束");
        ImageView imageView = holder.getView(R.id.order_tui_img);
        Glide.with(mContext).load(data.getGood_img()).into(imageView);

        TextView price = holder.getView(R.id.order_tui_price);
        price.setText("租金/件: ￥"+data.getGood_price()+"/租 金：￥"+data.getGoods_yajin()+"/数 量："+data.getGood_number());

        TextView total = holder.getView(R.id.order_tui_total);
        total.setText("总 金 额 ：￥"+data.getTotal_price());

        TextView m_return=holder.getView(R.id.m_return);

        m_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog;
                dialog = new AlertDialog.Builder(mContext);
                dialog.setTitle("请确定归还");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Map map = new HashMap();
                        String order_id=data.getOrder_id();
                        String ruturn_goodNumber =data.getGood_number();
                        String good_id = data.getGoods_id();
                        map.put("order_id", order_id);
                        map.put("order_status", "5");
                        Log.d("id",data.getOrder_id());
                        OkHttp.get(mContext, Constant.updateOrder_status, map, new OkCallback<Result<String>>() {
                            @Override
                            public void onResponse(Result<String> response) {
                                Toast.makeText(mContext, "衣物已归还，感谢您的光临。", Toast.LENGTH_SHORT).show();

                                change_goodNumber(ruturn_goodNumber,good_id);
                            }
                            @Override
                            public void onFailure(String state, String msg) {
                                Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });
    }

    private void change_goodNumber(String ruturn_goodNumber,String good_id) {
        Map map = new HashMap();
        map.put("good_id", good_id);
        map.put("ruturn_goodNumber", ruturn_goodNumber);
        OkHttp.get(mContext, Constant.update_goodNumber_add, map, new OkCallback<Result<String>>() {
            @Override
            public void onResponse(Result<String> response) {
                Log.d("顾客归还的数量",ruturn_goodNumber);
            }

            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.order_itemtui;
    }

    @Override
    protected int getViewType(int position, OrderBean data) {
        return 0;
    }
}
