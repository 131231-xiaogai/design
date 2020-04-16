package com.example.newapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.Administrators.AllShopkeeperActivity;
import com.example.newapplication.Administrators.AllUserActivity;

import java.util.HashMap;
import java.util.Map;

public class AdministratorsActivity extends AppCompatActivity implements View.OnClickListener {

    TextView Ad_user,adm_allUsser,adm_allShopkeeper,adm_deletedUser;
    String p_pagename,deleted_number;
    Intent intent;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administrators);
        Ad_user=findViewById(R.id.Ad_user);
        adm_allUsser=findViewById(R.id.adm_allUsser);
        adm_allShopkeeper=findViewById(R.id.adm_allShopkeeper);
        adm_deletedUser=findViewById(R.id.adm_deletedUser);

        //
        OnClickListener();

    }

    private void OnClickListener() {
        Ad_user.setOnClickListener(this);
        adm_allUsser.setOnClickListener(this);
        adm_allShopkeeper.setOnClickListener(this);
        adm_deletedUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.adm_allUsser:
                p_pagename="查 看 顾 客";
                intent = new Intent(AdministratorsActivity.this, AllUserActivity.class);
                intent.putExtra("p_pagename", p_pagename);
                startActivity(intent);
                break;
            case R.id.adm_allShopkeeper:
                p_pagename="查 看 商 家";
                intent = new Intent(AdministratorsActivity.this, AllShopkeeperActivity.class);
                intent.putExtra("p_pagename", p_pagename);
                startActivity(intent);
                break;
            case R.id.adm_deletedUser:
                p_pagename="注 销 顾 客";
                deleted_number="1";
                intent = new Intent(AdministratorsActivity.this, AllUserActivity.class);
                intent.putExtra("p_pagename", p_pagename);
                intent.putExtra("p_deleted_number", deleted_number);
                startActivity(intent);
                break;
        }

    }


}
