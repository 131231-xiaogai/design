package com.example.newapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.Administrators.Adm_dj_gk;
import com.example.newapplication.Administrators.Adm_dj_sj;
import com.example.newapplication.Administrators.Adm_qxdj_guke;
import com.example.newapplication.Administrators.Adm_qxdj_sj;
import com.example.newapplication.Administrators.AllShopkeeperActivity;
import com.example.newapplication.Administrators.AllUserActivity;
import com.example.newapplication.Administrators.Deleted_ShopkeeperActivity;

import java.util.HashMap;
import java.util.Map;

public class AdministratorsActivity extends AppCompatActivity implements View.OnClickListener {

    private  TextView Ad_user,adm_allUsser,adm_allShopkeeper,adm_deletedUser,adm_deletedShopkeeper;
    private TextView adm_dj_gk,adm_dj_sj,adm_qxdj_guke,adm_qxdj_sj;
    private String p_pagename,deleted_number;
    private Intent intent;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administrators);
        Ad_user=findViewById(R.id.Ad_user);
        adm_allUsser=findViewById(R.id.adm_allUsser);
        adm_allShopkeeper=findViewById(R.id.adm_allShopkeeper);
        adm_deletedUser=findViewById(R.id.adm_deletedUser);
        adm_deletedShopkeeper=findViewById(R.id.adm_deletedShopkeeper);
        //
        adm_dj_gk=findViewById(R.id.adm_dj_gk);
        adm_dj_sj=findViewById(R.id.adm_dj_sj);
        adm_qxdj_guke=findViewById(R.id.adm_qxdj_guke);
        adm_qxdj_sj=findViewById(R.id.adm_qxdj_sj);

        //
        OnClickListener();

    }

    private void OnClickListener() {
        Ad_user.setOnClickListener(this);
        adm_allUsser.setOnClickListener(this);
        adm_allShopkeeper.setOnClickListener(this);
        adm_deletedUser.setOnClickListener(this);
        adm_deletedShopkeeper.setOnClickListener(this);
        adm_dj_gk.setOnClickListener(this);
        adm_dj_sj.setOnClickListener(this);
        adm_qxdj_guke.setOnClickListener(this);
        adm_qxdj_sj.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.adm_allUsser:
                p_pagename="查 看 顾 客";
                deleted_number="0";
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
            case R.id.adm_deletedShopkeeper:
                p_pagename="注 销 商 家";
                //deleted_number="1";
                intent = new Intent(AdministratorsActivity.this, Deleted_ShopkeeperActivity.class);
                intent.putExtra("p_pagename", p_pagename);

                startActivity(intent);
                break;
            case R.id.adm_dj_gk:
                p_pagename ="冻结顾客";
                intent = new Intent(AdministratorsActivity.this, Adm_dj_gk.class);
                intent.putExtra("p_pagename", p_pagename);

                startActivity(intent);
                break;
            case R.id.adm_dj_sj:
                p_pagename ="冻结商家";
                intent = new Intent(AdministratorsActivity.this, Adm_dj_sj.class);
                intent.putExtra("p_pagename", p_pagename);
                startActivity(intent);
                break;
            case R.id. adm_qxdj_guke:
                p_pagename ="取消冻结顾客";
                intent = new Intent(AdministratorsActivity.this, Adm_qxdj_guke.class);
                intent.putExtra("p_pagename", p_pagename);

                startActivity(intent);
                break;
            case R.id. adm_qxdj_sj:
                p_pagename ="取消冻结商家";
                intent = new Intent(AdministratorsActivity.this, Adm_qxdj_sj.class);
                intent.putExtra("p_pagename", p_pagename);
                startActivity(intent);
                break;
        }

    }


}
