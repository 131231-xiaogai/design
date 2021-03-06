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
import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.new_utill.SharePrefrenceUtil;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

import java.util.HashMap;
import java.util.Map;

public class Sk_Order_huanAdapter extends BaseRecyclerViewAdapter<OrderBean, RecyclerViewHolder> {
    public Sk_Order_huanAdapter(Context context) {
        super(context);
    }
    String user_id,goods_yajin;
    @Override
    protected void convert(RecyclerViewHolder holder, OrderBean data, int position, int viewType) {
        TextView name = holder.getView(R.id.sk_order_huan_goodname);
        name.setText("#"+data.getGood_name()+"#");

        TextView tiame =holder.getView(R.id.sk_order_huan_goodNumber);
        tiame.setText("订单于："+data.getOrder_rent_finesh_time()+"结束");
        ImageView imageView = holder.getView(R.id.sk_order_huan_img);
        Glide.with(mContext).load(data.getGood_img()).into(imageView);
        TextView price = holder.getView(R.id.sk_order_huan_price);
        price.setText("租金/件：￥"+data.getGood_price()+"/ 押 金："+data.getGoods_yajin()+"/ 数 量 ："+data.getGood_number());

        TextView total = holder.getView(R.id.sk_order_huan_total);
        total.setText("订单总金额:￥"+data.getTotal_price());
        TextView sk_fahuo =holder.getView(R.id.huan);
        user_id=data.getUser_id();
        goods_yajin=data.getGoods_yajin();//顾客id
        sk_fahuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog;
                dialog = new AlertDialog.Builder(mContext);
                dialog.setTitle("请确认是否收到顾客还货");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Map map = new HashMap();
                        String order_id=data.getOrder_id();
                        map.put("order_id", order_id);
                        map.put("order_status", "6");
                        Log.d("id",data.getOrder_id());
                        OkHttp.get(mContext, Constant.updateOrder_status, map, new OkCallback<Result<String>>() {
                            @Override
                            public void onResponse(Result<String> response) {
                                Toast.makeText(mContext, "订单已完成。", Toast.LENGTH_SHORT).show();
                                select_userblance();
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
    private void select_userblance() {
        Map map = new HashMap();
        map.put("muser_id",user_id);//顾客id
        Log.d("收款的用户编号是：", user_id);
        OkHttp.get(mContext, Constant.select_user_by_id, map, new OkCallback<Result<UsersBean>>() {
            @Override
            public void onResponse(Result<UsersBean> response) {
                //给商家加钱
                double old_user_blance =Double.valueOf(response.getData().getBalance());
                double new_user_blance = Double.valueOf(goods_yajin)+old_user_blance;
                add_shopbalance(new_user_blance+"");
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void add_shopbalance(String new_user_blance){
        Map map = new HashMap();
        map.put("user_id", user_id);
        map.put("totalprice", new_user_blance);
        OkHttp.get(mContext, Constant.update_shop_balance, map, new OkCallback<Result<String>>() {
            @Override
            public void onResponse(Result<String> response) {
                Log.d("已经向顾客退还押金",goods_yajin);
                add_message();

            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void add_message() {

        String user = SharePrefrenceUtil.getObject(mContext, UsersBean.class).getNickname();//顾客id
        String title ="退还押金成功提醒";
        Map map = new HashMap();

        map.put("shop_id", "0");//商家id
        map.put("message_title", title);
        map.put("message_context","退还押金￥"+goods_yajin+"成功。");
        map.put("message_status", "1");//1给用户看；2给商家看
        map.put("user_id", user_id);
        map.put("message_type","1");//消息类型：1系统消息；2用户消息
        Log.d("id",title);
        OkHttp.get(mContext, Constant.insert_message, map, new OkCallback<Result<String>>() {
            @Override
            public void onResponse(Result<String> response) {
                // Toast.makeText(SettlementActivity.this, "付款成功提醒。", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.sk_order_itemhuan;
    }//

    @Override
    protected int getViewType(int position, OrderBean data) {
        return 0;
    }
}
