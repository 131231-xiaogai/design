package com.example.newapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public  class MeActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton m_btn_home,m_btn_list,m_btn_date,m_btn_shopcar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me);
       m_btn_home = findViewById(R.id.m_bottom_home);
       m_btn_home.setOnClickListener(this);
       m_btn_list = findViewById(R.id.m_button_list);
       m_btn_list.setOnClickListener(this);
       m_btn_date = findViewById(R.id.m_button_date);
       m_btn_date.setOnClickListener(this);
       m_btn_shopcar = findViewById(R.id.m_button_shopcar);
       m_btn_shopcar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
            case R.id.m_bottom_home:
                startActivity(new Intent(MeActivity.this,HomeActivity.class));
                break;
            case R.id.m_button_list:
                startActivity(new Intent(MeActivity.this,ListActivity.class));
                break;
            case R.id.m_button_date:
                startActivity(new Intent(MeActivity.this,DateActivity.class));
                break;
            case R.id.m_button_shopcar:
                startActivity(new Intent(MeActivity.this,ShopcarActivity.class));
                break;

       }

    }
}
