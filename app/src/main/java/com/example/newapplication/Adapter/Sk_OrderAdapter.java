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

public class Sk_OrderAdapter extends BaseRecyclerViewAdapter<OrderBean, RecyclerViewHolder> {
    public Sk_OrderAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerViewHolder holder, OrderBean data, int position, int viewType) {
        TextView name = holder.getView(R.id.sk_order_fa_goodname);
        name.setText("#"+data.getGood_name()+"#");

        TextView tiame =holder.getView(R.id.sk_order_fa_goodNumber);
        tiame.setText("订单于："+data.getOrder_rent_finesh_time()+"结束");

        ImageView imageView = holder.getView(R.id.sk_order_fa_img);
        Glide.with(mContext).load(data.getGood_img()).into(imageView);

        TextView price = holder.getView(R.id.sk_order_fa_price);
        price.setText("租金/件：￥"+data.getGood_price()+"/ 押 金："+data.getGoods_yajin()+"/数 量："+data.getGood_number());

        TextView total = holder.getView(R.id.sk_order_fa_total);
        total.setText("订单总金额:￥"+data.getTotal_price());
        TextView sk_fahuo =holder.getView(R.id.sk_fahuo);
        sk_fahuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog;
                dialog = new AlertDialog.Builder(mContext);

                dialog.setTitle("请确认发货");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Map map = new HashMap();
                        String order_id=data.getOrder_id();
                        map.put("order_id", order_id);
                        map.put("order_status", "3");
                        Log.d("id",data.getOrder_id());
                        OkHttp.get(mContext, Constant.updateOrder_status, map, new OkCallback<Result<String>>() {
                            @Override
                            public void onResponse(Result<String> response) {
                                Toast.makeText(mContext, "已发货。", Toast.LENGTH_SHORT).show();

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

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.sk_order_itemfa;
    }//

    @Override
    protected int getViewType(int position, OrderBean data) {
        return 0;
    }
}
