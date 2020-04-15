package com.example.newapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.entity.ShopBean;
import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.new_utill.SharePrefrenceUtil;
import com.example.newapplication.newpage.Notice;
import com.example.newapplication.shopkeeper.AddGoodsActivity;
import com.example.newapplication.shopkeeper.DeletedGoodsActivity;
import com.example.newapplication.shopkeeper.Register_shopActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopkeeperActivity extends AppCompatActivity implements View.OnClickListener {
    String myshop_id,pagename,myshop_name;
    String pagenumber,isshop;
    TextView my_shop_name,my_shop_address,me_shop_register_time,me_shop_phone,my_shop_sorc;
    TextView k_add,k_delete,k_change;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopkeeper);
        k_add=findViewById(R.id.k_add);
        my_shop_name=findViewById(R.id.my_shop_name);
        my_shop_address=findViewById(R.id.my_shop_address);
        me_shop_register_time=findViewById(R.id.me_shop_register_time);
        me_shop_phone=findViewById(R.id.me_shop_phone);
        my_shop_sorc=findViewById(R.id.my_shop_sorc);
        k_delete=findViewById(R.id.k_delete);
        k_change=findViewById(R.id.k_change);
        //
//        从店铺注册页面返回的数据
//        Intent goodid_integer = getIntent();
//        String  shop_name = goodid_integer.getStringExtra("shop_name");
//        my_shop_name.setText(shop_name);
//        String  shop_dresss = goodid_integer.getStringExtra("shop_dresss");
//        my_shop_address.setText(shop_dresss);
//        String  shop_phone = goodid_integer.getStringExtra("shop_phone");
//        me_shop_phone.setText(shop_phone);


        OnClickListener();
        loadData();

    }

    private void loadData() {
        String user_id = SharePrefrenceUtil.getObject(ShopkeeperActivity.this, UsersBean.class).getUerid();
        Map map = new HashMap();
        map.put("user_id",user_id);

        OkHttp.get(this, Constant.select_shop_by_userid, map, new OkCallback<Result<ShopBean>>() {
            @Override
            public void onResponse(Result<ShopBean> response) {
                if (response.getData() != null) {
                    myshop_name =response.getData().getShop_name();
                    String myshop_dress =response.getData().getShop_dresss();
                    String myshop_phone =response.getData().getShop_phone();
                    String myshop_sorc =response.getData().getShop_score();
                    String myshop_register_time =response.getData().getShop_regist_time();
                    myshop_id =response.getData().getShop_id();
                    if (myshop_name == null || myshop_name.isEmpty()) {
                        my_shop_name.setText("空的店铺名");
                    } else {
                        my_shop_name.setText(myshop_name);
                    }
                    my_shop_address.setText(myshop_dress);
                    me_shop_register_time.setText(myshop_register_time);
                    my_shop_sorc.setText(myshop_sorc);
                    me_shop_phone.setText(myshop_phone);
                    isshop="1";
                }else {
                    Toast.makeText(ShopkeeperActivity.this, "您还没有注册商铺。", Toast.LENGTH_SHORT).show();
                    my_shop_name.setText("注册我的店铺");
                    isshop="0";
                }
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(ShopkeeperActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void OnClickListener() {
        k_add.setOnClickListener(this);
        my_shop_name.setOnClickListener(this);
        my_shop_address.setOnClickListener(this);
        me_shop_register_time.setOnClickListener(this);
        me_shop_phone.setOnClickListener(this);
        my_shop_sorc.setOnClickListener(this);
        k_delete.setOnClickListener(this);
        k_change.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id. k_add:
                add_goods();
                break;
            case R.id.my_shop_name:
                register_myshop();
                break;
            case R.id.k_delete:
                delete_goods();
                break;
            case R.id.k_change:
                change_goods();
                break;
        }

    }

    private void add_goods() {
        Intent addgood_intent = new Intent(ShopkeeperActivity.this, AddGoodsActivity.class);
        addgood_intent.putExtra("my_shop_id",myshop_id);
        addgood_intent.putExtra("myshop_name",myshop_name);
        startActivityForResult(addgood_intent,1);
    }

    private void change_goods() {
        pagename="修 改 商 品";
        pagenumber= "11";
        Intent change_intent = new Intent(ShopkeeperActivity.this, DeletedGoodsActivity.class);
        change_intent.putExtra("myshop_id", myshop_id);
        change_intent.putExtra("my_pagename", pagename);
        change_intent.putExtra("my_pagenumber", pagenumber);
        startActivity(change_intent);
    }

    private void delete_goods() {
        pagename="商 品 下 架";
        pagenumber="1";
        Intent intent = new Intent(ShopkeeperActivity.this, DeletedGoodsActivity.class);
        intent.putExtra("myshop_id", myshop_id);
        intent.putExtra("my_pagename", pagename);
        intent.putExtra("my_pagenumber", pagenumber);
        startActivity(intent);
    }

    private void register_myshop() {
        Intent myshop_intent = new Intent(ShopkeeperActivity.this, Register_shopActivity.class);
        if(isshop.equals("1")){
            myshop_intent.putExtra("my_shop_name",my_shop_name.getText().toString());
            myshop_intent.putExtra("my_shop_address",my_shop_address.getText().toString());
            myshop_intent.putExtra("me_shop_phone",me_shop_phone.getText().toString());
        }else {
            myshop_intent.putExtra("my_shop_name","");
            myshop_intent.putExtra("my_shop_address","");
            myshop_intent.putExtra("me_shop_phone","");
        }
        startActivityForResult(myshop_intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String data_return = data.getStringExtra("data_return");
                    Log.d("updata_goods",data_return);
                    loadData();
                }
                break;
            default:
        }
    }
}
