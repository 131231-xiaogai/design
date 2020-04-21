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

public class Order_fa extends AppCompatActivity implements View.OnClickListener {

    ImageButton title_back;

    TextView fa,fu,tui,shou;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_order_fa);
        title_back=findViewById(R.id.title_back);
        fa=findViewById(R.id.ord_fa);
        fu=findViewById(R.id.ord_fu);
        tui=findViewById(R.id.ord_tui);
        shou=findViewById(R.id.ord_shou);
        //
        fa.setBackground(getResources().getDrawable(R.drawable.button_bg3));
        fa.setTextColor(this.getResources().getColor(R.color.white));


        //
        OnClickListener();
    }

    private void OnClickListener() {
        fa.setOnClickListener(this);
        fu.setOnClickListener(this);
        tui.setOnClickListener(this);
        shou.setOnClickListener(this);

    }
    public void finish_reback(View v){
        Order_fa.this.finish();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ord_fa:
                Toast.makeText(Order_fa.this, "您 正 在 当 前 页 。", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ord_fu:
                startActivity(new Intent(Order_fa.this, Order_fu.class));
                break;
            case R.id.ord_tui:
                startActivity(new Intent(Order_fa.this, Order_tui.class));
                break;
            case R.id.ord_shou:
                startActivity(new Intent(Order_fa.this, Order_shou.class));
                break;
        }

    }
}
