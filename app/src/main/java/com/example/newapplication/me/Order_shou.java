package com.example.newapplication.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.R;

public class Order_shou extends AppCompatActivity implements View.OnClickListener {

    ImageButton title_back;
    TextView fa,fu,tui,shou;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_order_shou);
        title_back=findViewById(R.id.title_back);
        fa=findViewById(R.id.ord_fa);
        fu=findViewById(R.id.ord_fu);
        tui=findViewById(R.id.ord_tui);
        shou=findViewById(R.id.ord_shou);
        //-------------------------------/
        shou.setBackground(getResources().getDrawable(R.drawable.button_bg3));
        shou.setTextColor(this.getResources().getColor(R.color.white));
        //------------------------------------/
        OnClickListener();
    }
    public void finish_reback(View v){
        Order_shou.this.finish();
    }

    private void OnClickListener() {
        fa.setOnClickListener(this);
        fu.setOnClickListener(this);
        tui.setOnClickListener(this);
        shou.setOnClickListener(this);

        //title_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ord_fa:
                startActivity(new Intent(Order_shou.this, Order_fa.class));
                break;
            case R.id.ord_fu:
                startActivity(new Intent(Order_shou.this, Order_fu.class));
                break;
            case R.id.ord_tui:
                startActivity(new Intent(Order_shou.this, Order_tui.class));
                break;
            case R.id.ord_shou:
                Toast.makeText(Order_shou.this, "您 正 在 当 前 页 。", Toast.LENGTH_SHORT).show();
                break;
        }



    }

}
