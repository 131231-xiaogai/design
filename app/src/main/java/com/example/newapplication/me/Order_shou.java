package com.example.newapplication.me;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.R;

public class Order_shou extends AppCompatActivity implements View.OnClickListener {

    ImageButton title_back;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_order_shou);
        title_back=findViewById(R.id.title_back);
        OnClickListener();
    }

    private void OnClickListener() {

        title_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id. title_back:
                Order_shou.this.finish();
                break;
        }

    }

}
