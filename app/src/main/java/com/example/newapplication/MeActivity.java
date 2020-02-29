package com.example.newapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.newpage.Notice;

public  class MeActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton btn_list, btn_date, btn_shop, btn_home;
    ImageView btn_notice;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me);
        btn_list = (ImageButton) findViewById(R.id.b_list);
        btn_list.setOnClickListener(this);//添加监听器
        btn_date = (ImageButton) findViewById(R.id.b_date);
        btn_date.setOnClickListener(this);
        btn_shop = (ImageButton) findViewById(R.id.b_shopcar);
        btn_shop.setOnClickListener(this);
        btn_home = (ImageButton) findViewById(R.id.b_home);
        btn_home.setOnClickListener(this);


        btn_notice = findViewById(R.id.btn_notice);
        btn_notice.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
      switch (v.getId()){
            case R.id.b_home:
                startActivity(new Intent(MeActivity.this,HomeActivity.class));
                break;
            case R.id.b_list:
                startActivity(new Intent(MeActivity.this,ListActivity.class));
                break;
            case R.id.b_date:
                startActivity(new Intent(MeActivity.this,DateActivity.class));
                break;
            case R.id.b_shopcar:
                startActivity(new Intent(MeActivity.this,ShopcarActivity.class));
                break;
          case R.id.btn_notice:
              startActivity(new Intent(MeActivity.this, Notice.class));
              break;

       }

    }
}
