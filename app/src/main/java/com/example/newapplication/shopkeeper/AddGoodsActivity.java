package com.example.newapplication.shopkeeper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.R;
import com.example.newapplication.ShopkeeperActivity;

public class AddGoodsActivity extends AppCompatActivity implements View.OnClickListener {

    TextView k_add;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sk_addgoods);
        k_add=findViewById(R.id.k_add);
        OnClickListener();

    }

    private void OnClickListener() {
        k_add.setOnClickListener( this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id. k_add:
                //startActivity(new Intent(AddGoodsActivity.this, AddGoodsActivity.class));
                break;
        }

    }
}
