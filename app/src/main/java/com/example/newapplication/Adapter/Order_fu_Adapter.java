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
import com.example.newapplication.MainActivity;
import com.example.newapplication.R;
import com.example.newapplication.entity.OrderBean;
import com.example.newapplication.entity.Shooping_carBean;
import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.me.Order_fu;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.new_utill.SharePrefrenceUtil;
import com.example.newapplication.shopcar.SettlementActivity;
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
        name.setText("#"+data.getGood_name()+"#");

        TextView tiame =holder.getView(R.id.order_fu_goodNumber);
        tiame.setText("订单于："+data.getOrder_rent_finesh_time()+"结束");

        ImageView imageView = holder.getView(R.id.order_fu_img);
        Glide.with(mContext).load(data.getGood_img()).into(imageView);

        TextView price = holder.getView(R.id.order_fu_price);
        price.setText("租金/件: ￥"+data.getGood_price()+"/租 金：￥"+data.getGoods_yajin()+"/数 量："+data.getGood_number());


        TextView total = holder.getView(R.id.order_fu_total);
        total.setText("总 金 额 ：￥"+data.getTotal_price());

        TextView cancle_order =holder.getView(R.id.cancle);
        TextView to_pay=holder.getView(R.id.pay);

        //对未付款订单进行取消订单操作
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
                       String ruturn_goodNumber =data.getGood_number();
                       String good_id = data.getGoods_id();
                       map.put("order_id", order_id);
                       map.put("order_status", "0");
                       Log.d("id",data.getOrder_id());
                       OkHttp.get(mContext, Constant.updateOrder_status, map, new OkCallback<Result<String>>() {
                           @Override
                           public void onResponse(Result<String> response) {
                               Toast.makeText(mContext, "您已取消订单。", Toast.LENGTH_SHORT).show();
                               change_goodNumber(ruturn_goodNumber,good_id);
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
        //对未付款订单进行付款
        to_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog;
                dialog = new AlertDialog.Builder(mContext);
                dialog.setTitle("提示");
                dialog.setMessage(String.format("是否支付以下金额：￥%s",data.getTotal_price()));
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String userid=SharePrefrenceUtil.getObject(mContext, UsersBean.class).getUerid();
                        Map map = new HashMap();
                        map.put("uerid", userid);
                        map.put("price", data.getTotal_price());
                        OkHttp.get(mContext, Constant.update_user_balance, map, new OkCallback<Result<String>>() {
                            @Override
                            public void onResponse(Result<String> response) {
                                Toast.makeText(mContext, "付款成功。", Toast.LENGTH_SHORT).show();
                                String order_id =data.getOrder_id();
                                change_order(order_id);
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
                Log.d("取消订单顾客，归还衣服的数量",ruturn_goodNumber);
            }

            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void change_order(String order_id) {
        Map map = new HashMap();
        map.put("order_id", order_id);
        map.put("order_status", "2");
        //Log.d("id",order_id);
        OkHttp.get(mContext, Constant.updateOrder_status, map, new OkCallback<Result<String>>() {
            @Override
            public void onResponse(Result<String> response) {
                Toast.makeText(mContext, "请耐心等待发货呦", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
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
