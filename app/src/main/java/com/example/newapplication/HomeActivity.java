package com.example.newapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener  {
    ImageButton h_btn_list,h_btn_date,h_btn_shop,h_btn_me;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        h_btn_list = (ImageButton) findViewById(R.id.h_button_list);
        h_btn_list.setOnClickListener(this);//添加监听器
        h_btn_date = (ImageButton)findViewById(R.id.h_button_date);
        h_btn_date.setOnClickListener(this);
        h_btn_shop = (ImageButton)findViewById(R.id.h_button_shopcar);
        h_btn_shop.setOnClickListener(this);
        h_btn_me = (ImageButton)findViewById(R.id.h_button_me);
        h_btn_me.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.h_button_list:
                startActivity(new Intent(HomeActivity.this,ListActivity.class));
                break;
            case  R.id.h_button_me:
                startActivity(new Intent(HomeActivity.this,MeActivity.class));
                break;
            case R.id.h_button_shopcar:
                startActivity(new Intent(HomeActivity.this,ShopcarActivity.class));
                break;
            case R.id.h_button_date:
                startActivity(new Intent(HomeActivity.this,DateActivity.class));

        }

    }
}

