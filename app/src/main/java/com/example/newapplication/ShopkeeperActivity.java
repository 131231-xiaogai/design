package com.example.newapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.newpage.Notice;
import com.example.newapplication.shopkeeper.AddGoodsActivity;

public class ShopkeeperActivity extends AppCompatActivity implements View.OnClickListener {

    TextView k_add;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout. shopkeeper);
        k_add=findViewById(R.id.k_add);
        OnClickListener();

    }

    private void OnClickListener() {
        k_add.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id. k_add:
                startActivity(new Intent(ShopkeeperActivity.this, AddGoodsActivity.class));
                break;
        }

    }
}
