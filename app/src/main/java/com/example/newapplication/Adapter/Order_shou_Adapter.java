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
import com.example.newapplication.entity.ShopBean;
import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order_shou_Adapter extends BaseRecyclerViewAdapter<OrderBean, RecyclerViewHolder> {
    public Order_shou_Adapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerViewHolder holder, OrderBean data, int position, int viewType) {
        TextView name = holder.getView(R.id.order_shou_goodname);
        name.setText(data.getGood_name());

        ImageView imageView = holder.getView(R.id.order_shou_img);
        Glide.with(mContext).load(data.getGood_img()).into(imageView);

        TextView price = holder.getView(R.id.order_shou_price);
        price.setText("￥"+data.getGood_price());

        TextView number = holder.getView(R.id.order_shou_goodNumber);
        number.setText(data.getGood_number());
        TextView total = holder.getView(R.id.order_shou_total);
        total.setText("￥"+data.getTotal_price());

        TextView shou = holder.getView(R.id.shou);

        shou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog;
                dialog = new AlertDialog.Builder(mContext);

                dialog.setTitle("请确定签收订单");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Map map = new HashMap();
                        String order_id = data.getOrder_id();
                        map.put("order_id", order_id);
                        map.put("order_status", "4");
                        Log.d("id", data.getOrder_id());
                        OkHttp.get(mContext, Constant.updateOrder_status, map, new OkCallback<Result<String>>() {
                            @Override
                            public void onResponse(Result<String> response) {
                                Toast.makeText(mContext, "订单已签收。", Toast.LENGTH_SHORT).show();
                                //给商家加钱
                                String a =  data.getTotal_price();
                                String shop_id = data.getShop_id();
                                select_userid_byShopId (a,shop_id);
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

    public void select_userid_byShopId(String a,String shop_id){
        Map map = new HashMap();
        map.put("shop_id",shop_id);
        Log.d("收款的商家编号是：", shop_id);
        OkHttp.get(mContext, Constant.select_user_byShopId, map, new OkCallback<Result<ShopBean>>() {
            @Override
            public void onResponse(Result<ShopBean> response) {
                //给商家加钱
                String user_id = response.getData().getUser_id();

                select_userblance(a,user_id);
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void select_userblance(String a,String user_id) {
        Map map = new HashMap();
        map.put("muser_id",user_id);
        Log.d("收款的用户编号是：", user_id);
        OkHttp.get(mContext, Constant.select_user_by_id, map, new OkCallback<Result<UsersBean>>() {
            @Override
            public void onResponse(Result<UsersBean> response) {
                //给商家加钱
                String user_id = response.getData().getUerid();
                double old_user_blance =Double.valueOf(response.getData().getBalance());
                double new_user_blance = Double.valueOf(a)+old_user_blance;
                add_shopbalance(new_user_blance+"",user_id);
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void add_shopbalance(String new_user_blance,String user_id){
        Map map = new HashMap();
        map.put("user_id", user_id);
        map.put("totalprice", new_user_blance);
        Log.d("收款金额是",new_user_blance);
        OkHttp.get(mContext, Constant.update_shop_balance, map, new OkCallback<Result<String>>() {
            @Override
            public void onResponse(Result<String> response) {
                Log.d("已经向商家付款，商家总金额是",new_user_blance);

            }

            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }



    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.order_itemshou;
    }

    @Override
    protected int getViewType(int position, OrderBean data) {
        return 0;
    }
}
