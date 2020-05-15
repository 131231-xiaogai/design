package com.example.newapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.newapplication.entity.EvaluateBean;
import com.example.newapplication.entity.OrderBean;
import com.example.newapplication.entity.ShopBean;
import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.me.MySetUpActivility;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.new_utill.SharePrefrenceUtil;
import com.example.newapplication.newpage.All_orderActivity;
import com.example.newapplication.newpage.Sk_Notice;
import com.example.newapplication.shopkeeper.AddGoodsActivity;
import com.example.newapplication.shopkeeper.DeletedGoodsActivity;
import com.example.newapplication.shopkeeper.My_OrderActivity;
import com.example.newapplication.shopkeeper.My_Order_fuActivity;
import com.example.newapplication.shopkeeper.My_Order_huanActivity;
import com.example.newapplication.shopkeeper.My_Order_shouActivity;
import com.example.newapplication.shopkeeper.Register_shopActivity;
import com.example.newapplication.shopkeeper.Sk_All_orderActivity;
import com.example.newapplication.shopkeeper.Sk_more_maney;
import com.example.newapplication.shopkeeper.Sk_select_goods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopkeeperActivity extends AppCompatActivity implements View.OnClickListener {
    private String myshop_id,pagename,myshop_name;
    private String pagenumber,isshop;
    private TextView my_shop_name,my_shop_address,me_shop_register_time,me_shop_phone,my_shop_sorc,sk_huan;
    private TextView k_add,k_delete,k_change,me_shop_name,me_shop_blance,s_totalprice,sk_all_order,sk_user_setup,k_select;
    private TextView more_maney;
    private ImageButton btn_notice,title_back;
    private TextView fu,fa,shou;
    private SwipeRefreshLayout swipeRefreshLayout;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopkeeper);
        k_add=findViewById(R.id.k_add);
        btn_notice=findViewById(R.id.btn_notice);
        my_shop_name=findViewById(R.id.my_shop_name);
        my_shop_address=findViewById(R.id.my_shop_address);
        me_shop_register_time=findViewById(R.id.me_shop_register_time);
        me_shop_phone=findViewById(R.id.me_shop_phone);
        title_back=findViewById(R.id.title_back);
        my_shop_sorc=findViewById(R.id.my_shop_sorc);// 评分
        k_delete=findViewById(R.id.k_delete);
        k_change=findViewById(R.id.k_change);
        fu=findViewById(R.id.sk_fu);
        fa=findViewById(R.id.sk_fa);
        shou=findViewById(R.id.sk_shou);
        me_shop_name=findViewById(R.id.me_shop_username);
        me_shop_blance=findViewById(R.id.me_shop_blance);
        sk_huan=findViewById(R.id.sk_huan);
        s_totalprice=findViewById(R.id.s_totalprice);
        sk_all_order=findViewById(R.id.sk_all_order);
        sk_user_setup=findViewById(R.id.sk_user_setup);
        more_maney=findViewById(R.id.more_maney);
        k_select=findViewById(R.id.k_select);
        //****----//
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout_sk);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        //---****-//
        OnClickListener();
        loadData();
    }

    private void loadData() {
        String user_id = SharePrefrenceUtil.getObject(ShopkeeperActivity.this, UsersBean.class).getUerid();
        String user_name = SharePrefrenceUtil.getObject(ShopkeeperActivity.this, UsersBean.class).getNickname();
        String user_blance = SharePrefrenceUtil.getObject(ShopkeeperActivity.this, UsersBean.class).getBalance();
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
                    me_shop_name.setText(user_name);
                    me_shop_blance.setText("￥"+user_blance);

                    isshop="1";
                }else {
                    Toast.makeText(ShopkeeperActivity.this, "您还没有注册商铺。", Toast.LENGTH_SHORT).show();
                    my_shop_name.setText("注册我的店铺");
                    isshop="0";
                }
                shop_totoprice();
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(ShopkeeperActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void shop_evaluate() {
        Map map = new HashMap();
        map.put("shop_id",myshop_id);
        OkHttp.get(this, Constant.selece_shop_evaluate, map, new OkCallback<Result<List<EvaluateBean>>>() {
            @Override
            public void onResponse(Result<List<EvaluateBean>> response) {

                  double evaluate = 0;
                  for (int i = 0; i < response.getData().size(); i++) {
                      evaluate = evaluate + Double.valueOf(response.getData().get(i).getP_content());
                  }
                  evaluate = evaluate / response.getData().size();
                  my_shop_sorc.setText(evaluate + "分");

            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(ShopkeeperActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void shop_totoprice() {
        Map map = new HashMap();
        map.put("shop_id",myshop_id);

        OkHttp.get(this, Constant.selece_order_totalprice, map, new OkCallback<Result<List<OrderBean>>>() {
            @Override
            public void onResponse(Result<List<OrderBean>> response) {
                double totalPrice=0;
                for (int i = 0; i < response.getData().size(); i++) {
                    totalPrice= totalPrice+Double.valueOf(response.getData().get(i).getTotal_price());
                }
                s_totalprice.setText("￥"+totalPrice);
                shop_evaluate();
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
        fu.setOnClickListener(this);
        fa.setOnClickListener(this);
        shou.setOnClickListener(this);
        sk_huan.setOnClickListener(this);
        btn_notice.setOnClickListener(this);
        title_back.setOnClickListener(this);
        sk_all_order.setOnClickListener(this);
        sk_user_setup.setOnClickListener(this);
        more_maney.setOnClickListener(this);
        k_select.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.k_select:
                Intent intent8 = new Intent(ShopkeeperActivity.this, Sk_select_goods.class);
                intent8.putExtra("my_shop_id",myshop_id);
                pagenumber="1";
                intent8.putExtra("my_pagenumber", pagenumber);
                startActivity(intent8);
                break;
            case R.id.more_maney:
                Intent intent7 = new Intent(ShopkeeperActivity.this, Sk_more_maney.class);
                intent7.putExtra("my_shop_id",myshop_id);
                startActivity(intent7);
                break;
            case R.id. sk_fa:
                Intent intent2 = new Intent(ShopkeeperActivity.this, My_OrderActivity.class);
                intent2.putExtra("my_shop_id",myshop_id);
                intent2.putExtra("page_name","待 发 货 页 面");
                intent2.putExtra("order_status","2");
                startActivity(intent2);

                break;
            case R.id. sk_fu:
                Intent intent1 = new Intent(ShopkeeperActivity.this, My_Order_fuActivity.class);
                intent1.putExtra("my_shop_id",myshop_id);
                intent1.putExtra("order_status","1");
                intent1.putExtra("page_name","待 顾 客 付 款 订 单");
                startActivity(intent1);

                break;
            case R.id. sk_huan:
                Intent intent3 = new Intent(ShopkeeperActivity.this, My_Order_shouActivity.class);
                intent3.putExtra("my_shop_id",myshop_id);
                intent3.putExtra("order_status","4");
                intent3.putExtra("page_name","待 顾 客 还 货 订 单");
                startActivity(intent3);

               break;
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
            case R.id.sk_shou:
                Intent intent4 = new Intent(ShopkeeperActivity.this, My_Order_huanActivity.class);
                intent4.putExtra("my_shop_id",myshop_id);
                intent4.putExtra("order_status","5");
                intent4.putExtra("page_name","确 认 顾 客 还 货 ");
                startActivity(intent4);

                break;
            case R.id.btn_notice:
                Intent intent5 = new Intent(ShopkeeperActivity.this, Sk_Notice.class);
                intent5.putExtra("my_shop_id",myshop_id);
                startActivity(intent5);

                break;
            case R.id.title_back:
                ShopkeeperActivity.this.finish();
                break;
            case R.id.sk_all_order:
                Intent intent6 = new Intent(ShopkeeperActivity.this, Sk_All_orderActivity.class);
                intent6.putExtra("my_shop_id",myshop_id);
                startActivity(intent6);
                break;
            case R.id.sk_user_setup:
                startActivity(new Intent(ShopkeeperActivity.this, MySetUpActivility.class));
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
