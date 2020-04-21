package com.example.newapplication.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.R;


public class My_orderBearActivity extends AppCompatActivity implements View.OnClickListener  {

    TextView ord_fu,ord_fa,ord_shou,ord_tui;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_order);
        ord_fu =findViewById(R.id.ord_fu);
        ord_fa=findViewById(R.id.ord_fa);
        ord_shou=findViewById(R.id.ord_shou);
        ord_tui=findViewById(R.id.ord_tui);

        OnClickListener();
    }

    private void OnClickListener() {
        ord_fu .setOnClickListener(this);
        ord_fa.setOnClickListener(this);
        ord_shou.setOnClickListener(this);
        ord_tui.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {


    }
}
