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
import com.example.newapplication.me.Order_fu;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

import java.util.HashMap;
import java.util.Map;

public class Order_fu_Adapter extends BaseRecyclerViewAdapter<OrderBean, RecyclerViewHolder> {
    public Order_fu_Adapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerViewHolder holder, OrderBean data, int position, int viewType) {
        TextView name = holder.getView(R.id.order_fu_goodname);
        name.setText(data.getGood_name());

        ImageView imageView = holder.getView(R.id.order_fu_img);
        Glide.with(mContext).load(data.getGood_img()).into(imageView);

        TextView price = holder.getView(R.id.order_fu_price);
        price.setText(data.getGood_price());

        TextView number = holder.getView(R.id.order_fu_goodNumber);
        number.setText(data.getGood_number());
        TextView total = holder.getView(R.id.order_fu_total);
        total.setText(data.getTotal_price());

        TextView cancle_order =holder.getView(R.id.cancle);

        cancle_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog;
                dialog = new AlertDialog.Builder(mContext);

                dialog.setTitle("您确定取消订单吗？");
                dialog.setCancelable(false);
               dialog.setPositiveButton("确定取消", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       Map map = new HashMap();
                       String order_id=data.getOrder_id();
                       map.put("order_id", order_id);
                       map.put("order_status", "0");
                       Log.d("id",data.getOrder_id());
                       OkHttp.get(mContext, Constant.updateOrder_status, map, new OkCallback<Result<String>>() {
                           @Override
                           public void onResponse(Result<String> response) {
                               Toast.makeText(mContext, "您已取消订单。", Toast.LENGTH_SHORT).show();

                           }



                           @Override
                           public void onFailure(String state, String msg) {
                               Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                           }
                       });

                   }
               });
                dialog.setNegativeButton("我再想想", new DialogInterface.OnClickListener() {
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
        return R.layout.order_itemfu;
    }

    @Override
    protected int getViewType(int position, OrderBean data) {
        return 0;
    }
}
