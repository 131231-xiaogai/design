package com.example.newapplication.newpage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.newapplication.R;
import com.example.newapplication.entity.AddressBean;
import com.example.newapplication.entity.EvaluateBean;
import com.example.newapplication.entity.OrderBean;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.shopkeeper.Sk_All_orderActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton title_back,notic;
    private String  da,address,deliver;
    private TextView  uname, phone, de_address,gsize,gprice,gtotal,order_code,startTime,gfinishTime,order_de_shopname;
    private TextView  gname;
    private ImageView gimg;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_datail);
        title_back=findViewById(R.id.order_de_back);
        notic=findViewById(R.id.order_de_notice);
        uname=findViewById(R.id.addoder_de_name);
        phone=findViewById(R.id.addoder_de_phone);
        de_address=findViewById(R.id.addoder_de_adresstatal);
        gimg=findViewById(R.id.order_de_img);
        gname=findViewById(R.id.order_de_goodname);
        gsize=findViewById(R.id.order_de_size);
        gprice=findViewById(R.id.order_de_price);
        gtotal=findViewById(R.id.order_de_total);
        order_de_shopname=findViewById(R.id.order_de_shopname);
        order_code=findViewById(R.id.order_de_code);
        startTime=findViewById(R.id.order_de_sartTime);
        gfinishTime=findViewById(R.id.order_de_finish_Time);

        //*****************//

        Intent goodid_integer = getIntent();
        da = goodid_integer.getStringExtra("hgoodid");
        String img=goodid_integer.getStringExtra("img");
        Glide.with(OrderDetailActivity.this).load(img).into(gimg);
        String name =goodid_integer.getStringExtra("name");
        gname.setText(name);
        String yajin =goodid_integer.getStringExtra("yajin");
        gsize.setText("押金： ￥"+yajin);
        String price =goodid_integer.getStringExtra("price");
        String number =goodid_integer.getStringExtra("number");
        gprice.setText("租金/件：￥"+price+"数量: "+number);
        String totalprice =goodid_integer.getStringExtra("totalprice");
        String deliver_number =goodid_integer.getStringExtra("deliver");// 1	店家配送 2	到店自取
        if (deliver_number.equals("1")){ deliver="店家配送";
        }else if(deliver_number.equals("2")){ deliver="到店自取";}
        gtotal.setText("总价： ￥"+totalprice+"/ 取货方式："+deliver);
        address =goodid_integer.getStringExtra("address");
        String shop_name =goodid_integer.getStringExtra("shop_name");
        order_de_shopname.setText(shop_name);
        String crateTime =goodid_integer.getStringExtra("crateTime");
        startTime.setText("订单创建时间："+crateTime);
        String startTime =goodid_integer.getStringExtra("startTime");
        String finishTime =goodid_integer.getStringExtra("finishTime");
        gfinishTime.setText("租期结束时间："+finishTime);
        //ordercode
        String ordercode =goodid_integer.getStringExtra("order_code");

        Log.d("订单详情页的订单编号",da);
        //**********************//
        find_address();
        OnClickListener();
    }

    private void find_address() {
        Map map = new HashMap();
        map.put("address_id",da);
        OkHttp.get(this, Constant.select_address_by_addressId, map, new OkCallback<Result<AddressBean>>() {
            @Override
            public void onResponse(Result<AddressBean> response) {
                de_address.setText("收货地址："+response.getData().getAddress_total()+response.getData().getAddress_detail());
                uname.setText(response.getData().getContact_name());
                phone.setText(response.getData().getContact_phone());
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(OrderDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
        find_pingfen();
    }

    private void find_pingfen() {
        Map map = new HashMap();
        map.put("order_id",address);
        OkHttp.get(this, Constant.select_evaluate_by_orderid, map, new OkCallback<Result<EvaluateBean>>() {
            @Override
            public void onResponse(Result<EvaluateBean> response) {
                if (response.getData().getP_content()==null){
                    order_code.setText("订单评分： 该订单暂无评分");
                }else {
                    order_code.setText("订单评分："+response.getData().getP_content()+"分");
                }

            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(OrderDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void OnClickListener() {
        notic.setOnClickListener(this);
        title_back.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.order_de_back:
                OrderDetailActivity.this.finish();
                break;
            case R.id.order_de_notice:
                startActivity(new Intent(OrderDetailActivity.this, Notice.class));
                break;
        }
    }
}
