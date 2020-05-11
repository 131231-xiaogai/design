package com.example.newapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.me.MyBodySizeActivility;
import com.example.newapplication.me.MyEditionActivility;
import com.example.newapplication.me.MySetUpActivility;
import com.example.newapplication.me.MyaddressActivility;
import com.example.newapplication.me.WalletpagaActivity;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.new_utill.SharePrefrenceUtil;
import com.example.newapplication.newpage.All_orderActivity;
import com.example.newapplication.newpage.Notice;
import com.example.newapplication.me.Order_fa;
import com.example.newapplication.me.Order_fu;
import com.example.newapplication.me.Order_shou;
import com.example.newapplication.me.Order_tui;

import java.util.HashMap;
import java.util.Map;

public  class MeActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton btn_list, btn_date, btn_shop, btn_home;
    ImageView btn_notice;
    TextView fa,fu,tui,shou,m_username,m_wallet,m_adress,m_setup,m_bodysize,evaluate,all_order;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me);
        fa=findViewById(R.id.fa);
        fu=findViewById(R.id.fu);
        tui=findViewById(R.id.tui);
        shou=findViewById(R.id.shou);
        m_username= findViewById(R.id.m_username);
        btn_list =  findViewById(R.id.b_list);
        btn_date =  findViewById(R.id.b_date);
        btn_shop =  findViewById(R.id.b_shopcar);
        btn_home =  findViewById(R.id.b_home);
        btn_notice = findViewById(R.id.btn_notice);
        m_wallet = findViewById(R.id.m_wallet);
        m_adress = findViewById(R.id.m_adress);
        m_setup =findViewById(R.id.m_setup);
        m_bodysize =findViewById(R.id.m_bodysize);
        evaluate =findViewById(R.id.evaluate);
        all_order=findViewById(R.id.all_order);
        OnClickListener();
        loadData();
    }
    private void loadData() {
        String nickname = SharePrefrenceUtil.getObject(MeActivity.this, UsersBean.class).getNickname();
            if (nickname == null || nickname.isEmpty()){
                m_username.setText("暂未设置昵称");
            }else{
                m_username.setText(nickname);
            }
    }

    private void OnClickListener() {
        btn_list.setOnClickListener(this);//添加监听器
        btn_date.setOnClickListener(this);
        btn_shop.setOnClickListener(this);
        btn_home.setOnClickListener(this);
        btn_notice.setOnClickListener(this);
        all_order.setOnClickListener(this);
        fa.setOnClickListener(this);
        fu.setOnClickListener(this);
        tui.setOnClickListener(this);
        shou.setOnClickListener(this);
        m_username.setOnClickListener(this);
        m_wallet.setOnClickListener(this);
        m_adress.setOnClickListener(this);
        m_setup.setOnClickListener(this);
        m_bodysize.setOnClickListener(this);
        evaluate.setOnClickListener(this);
    }

    public void finish_reback(View v){
        MeActivity.this.finish();
    }
    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.b_home:
              startActivity(new Intent(MeActivity.this, HomeActivity.class));
              MeActivity.this.finish();
              break;
          case R.id.b_list:
              startActivity(new Intent(MeActivity.this, ListActivity.class));
              MeActivity.this.finish();
              break;
          case R.id.b_date:
              startActivity(new Intent(MeActivity.this, DateActivity.class));
              MeActivity.this.finish();
              break;
          case R.id.b_shopcar:
              startActivity(new Intent(MeActivity.this, ShopcarActivity.class));
              MeActivity.this.finish();
              break;
          case R.id.btn_notice:
              startActivity(new Intent(MeActivity.this, Notice.class));
              //MeActivity.this.finish();
              break;
          case R.id.fa:
              startActivity(new Intent(MeActivity.this, Order_fa.class));
              //MeActivity.this.finish();
              break;
          case R.id.fu:
              startActivity(new Intent(MeActivity.this, Order_fu.class));
              //MeActivity.this.finish();
              break;
          case R.id.tui:
              startActivity(new Intent(MeActivity.this, Order_tui.class));
              //MeActivity.this.finish();
              break;
          case R.id.shou:
              startActivity(new Intent(MeActivity.this, Order_shou.class));
              //MeActivity.this.finish();
              break;
          case R.id.m_wallet:
              String data = "我 的 钱 包";
              Intent intent = new Intent(MeActivity.this, WalletpagaActivity.class);
              intent.putExtra("balance", data);
              startActivity(intent);
              break;
          case R.id.m_adress:
              startActivity(new Intent(MeActivity.this, MyaddressActivility.class));
              break;
          case R.id.m_setup:
              startActivity(new Intent(MeActivity.this, MySetUpActivility.class));
              break;
          case R.id.m_bodysize:
              startActivity(new Intent(MeActivity.this, MyBodySizeActivility.class));

              break;
          case R.id.evaluate:
              startActivity(new Intent(MeActivity.this, MyEditionActivility.class));
              break;
          case R.id.all_order:
              startActivity(new Intent(MeActivity.this, All_orderActivity.class));
              break;
       }

    }
}
