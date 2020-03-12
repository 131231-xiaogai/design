package com.example.newapplication.shopkeeper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.newapplication.R;

public class AddGoodsActivity extends AppCompatActivity implements View.OnClickListener {


    ImageView add_img;
    EditText add_name,add_price,add_yajin,add_saize,add_type;
    Button btn_add;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sk_addgoods);
        add_img =findViewById(R.id.add_img);
        add_name = findViewById(R.id.add_name);
        add_price = findViewById(R.id.add_price);
        add_saize = findViewById(R.id.add_saize);
        add_type =findViewById(R.id.add_type);
        add_yajin =findViewById(R.id.add_yajin);
        btn_add =findViewById(R.id.btn_add);
        OnClickListener();
    }

    private void OnClickListener() {
      add_yajin.setOnClickListener(this);
      add_type.setOnClickListener(this);
      add_saize.setOnClickListener(this);
      add_price.setOnClickListener(this);
      add_name.setOnClickListener(this);
      add_img.setOnClickListener(this);
      btn_add.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_img:
                break;
            case R.id.add_name:
                break;
            case R.id.add_price:
                break;
            case R.id.add_type:
                break;
            case R.id.add_yajin:
                break;
            case R.id.btn_add:
                break;
            case R.id.add_saize:
                break;
        }

    }
}
