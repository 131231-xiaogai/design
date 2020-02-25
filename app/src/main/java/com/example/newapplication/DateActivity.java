package com.example.newapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DateActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton d_btn_home,d_btn_list,d_btn_shopcar,d_btn_me;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date);
        d_btn_home = findViewById(R.id.d_bottom_home);
        d_btn_home.setOnClickListener(this);
        d_btn_list = findViewById(R.id.d_button_list);
        d_btn_list.setOnClickListener(this);
        d_btn_me = findViewById(R.id.d_button_me);
        d_btn_me.setOnClickListener(this);
        d_btn_shopcar = findViewById(R.id.d_button_shopcar);
        d_btn_shopcar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.d_bottom_home:
                startActivity(new Intent(DateActivity.this,HomeActivity.class));
                break;
            case R.id.d_button_list:
                startActivity(new Intent(DateActivity.this,ListActivity.class));
                break;
            case R.id.d_button_me:
                startActivity(new Intent(DateActivity.this,MeActivity.class));
                break;
            case R.id.d_button_shopcar:
                startActivity(new Intent(DateActivity.this,ShopcarActivity.class));
                break;

        }

    }
}
