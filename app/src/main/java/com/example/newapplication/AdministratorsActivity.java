package com.example.newapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.newapplication.Administrators.Adm_dj_gk;
import com.example.newapplication.Administrators.Adm_dj_sj;
import com.example.newapplication.Administrators.Adm_qxdj_guke;
import com.example.newapplication.Administrators.Adm_qxdj_sj;
import com.example.newapplication.Administrators.AllShopkeeperActivity;
import com.example.newapplication.Administrators.AllUserActivity;
import com.example.newapplication.Administrators.DeleteUserActivity;
import com.example.newapplication.Administrators.Deleted_ShopkeeperActivity;
import com.example.newapplication.entity.OrderBean;
import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.me.MySetUpActivility;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.new_utill.SharePrefrenceUtil;
import com.example.newapplication.newpage.Notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdministratorsActivity extends AppCompatActivity implements View.OnClickListener {

    private  TextView Ad_user,adm_allUsser,adm_allShopkeeper,adm_deletedUser,adm_deletedShopkeeper,ad_user_setup;
    private TextView adm_dj_gk,adm_dj_sj,adm_qxdj_guke,adm_qxdj_sj,m_username,m_phone;
    private String p_pagename,deleted_number;
    private Intent intent;
    private ImageButton btn_notice;
    private SwipeRefreshLayout swipeRefreshLayout;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administrators);
        Ad_user=findViewById(R.id.Ad_user);
        adm_allUsser=findViewById(R.id.adm_allUsser);
        adm_allShopkeeper=findViewById(R.id.adm_allShopkeeper);
        adm_deletedUser=findViewById(R.id.adm_deletedUser);
        adm_deletedShopkeeper=findViewById(R.id.adm_deletedShopkeeper);
        ad_user_setup=findViewById(R.id.ad_user_setup);
        m_username=findViewById(R.id.Ad_user);
        m_phone=findViewById(R.id.Ad_phone);
        btn_notice=findViewById(R.id.btn_notice);
        //
        adm_dj_gk=findViewById(R.id.adm_dj_gk);
        adm_dj_sj=findViewById(R.id.adm_dj_sj);
        adm_qxdj_guke=findViewById(R.id.adm_qxdj_guke);
        adm_qxdj_sj=findViewById(R.id.adm_qxdj_sj);
        //****----//
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout_ad);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData_name();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        //---****-//
        OnClickListener();
        loadData();
    }

    private void loadData_name() {
        String user_id  = SharePrefrenceUtil.getObject(AdministratorsActivity.this, UsersBean.class).getUerid();
        Map map = new HashMap();
        map.put("muser_id",user_id);

        OkHttp.get(this, Constant.select_user_by_id, map, new OkCallback<Result<UsersBean>>() {
            @Override
            public void onResponse(Result<UsersBean> response) {
                m_username.setText("用户： "+response.getData().getNickname());
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(AdministratorsActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadData() {
        String nickname = SharePrefrenceUtil.getObject(AdministratorsActivity.this, UsersBean.class).getNickname();
        String phone = SharePrefrenceUtil.getObject(AdministratorsActivity.this, UsersBean.class).getPhone();
        if (nickname == null || nickname.isEmpty()){
            m_username.setText("暂未设置昵称");
        }else{
            m_username.setText("用户： "+nickname);
        }
        m_phone.setText("手机号： "+phone);
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
        ad_user_setup.setOnClickListener(this);
        btn_notice.setOnClickListener(this);
    }

    public void finish_reback(View v){
        AdministratorsActivity.this.finish();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_notice:
                startActivity(new Intent(AdministratorsActivity.this, Notice.class));
                break;
            case R.id.adm_allUsser:
                p_pagename="查 看 顾 客";
                deleted_number="0";
                intent = new Intent(AdministratorsActivity.this, AllUserActivity.class);
                intent.putExtra("p_pagename", p_pagename);
                intent.putExtra("p_deleted_number", deleted_number);
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
            case R.id.ad_user_setup:
                startActivity(new Intent(AdministratorsActivity.this, MySetUpActivility.class));
                break;
        }
    }
}
