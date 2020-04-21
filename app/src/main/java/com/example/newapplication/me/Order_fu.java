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

public class Order_fu extends AppCompatActivity implements View.OnClickListener {
    ImageButton title_back;
    TextView ord_fu;
    TextView fa,tui,shou;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_order_fu);
        ord_fu=findViewById(R.id.ord_fu);
        fa=findViewById(R.id.ord_fa);

        tui=findViewById(R.id.ord_tui);
        shou=findViewById(R.id.ord_shou);
        //-------------------------------/
        ord_fu.setBackground(getResources().getDrawable(R.drawable.button_bg3));
        ord_fu.setTextColor(this.getResources().getColor(R.color.white));
        //------------------------------------/

        OnClickListener();
    }
    public void finish_reback(View v){
        Order_fu.this.finish();
    }

    private void OnClickListener() {
        fa.setOnClickListener(this);
        ord_fu.setOnClickListener(this);
        tui.setOnClickListener(this);
        shou.setOnClickListener(this);


        //title_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ord_fa:
                startActivity(new Intent(Order_fu.this, Order_fa.class));
                break;
            case R.id.ord_fu:
                Toast.makeText(Order_fu.this, "您 正 在 当 前 页 。", Toast.LENGTH_SHORT).show();

                break;
            case R.id.ord_tui:
                startActivity(new Intent(Order_fu.this, Order_tui.class));
                break;
            case R.id.ord_shou:
                startActivity(new Intent(Order_fu.this, Order_shou.class));
                break;
        }


    }
}
