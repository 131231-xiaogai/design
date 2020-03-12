package com.example.newapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.new_utill.SMSTextView;
import com.example.newapplication.new_utill.SharePrefrenceUtil;
import com.example.newapplication.newpage.Notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText e_phonemb, e_bassword;
    TextView register, lonin_help;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e_phonemb = (EditText) findViewById(R.id.e_phonemb);
        e_bassword = (EditText) findViewById(R.id.e_bassword);
        lonin_help = (TextView) findViewById(R.id.lonin_help);
        login = findViewById(R.id.login);
        register = (TextView) findViewById(R.id.register);
        OnClickListener();
    }


    private void OnClickListener() {
        e_phonemb.setOnClickListener(this);
        e_bassword.setOnClickListener(this);
        lonin_help.setOnClickListener(this);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lonin_help:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0596-1234567"));
                startActivity(intent);
                break;
            case R.id.login:
                user_login();
                break;
            case R.id.register:
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                break;
        }
    }

    private void user_login() {
        Map map = new HashMap();
        map.put("phone", e_phonemb.getText().toString());
        map.put("password", e_bassword.getText().toString());
        OkHttp.get(this, Constant.login, map, new OkCallback<Result<String>>() {
            @Override
            public void onResponse(Result<String> response) {
                getUserInfo(e_phonemb.getText().toString());
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getUserInfo(String phone) {
        Map map = new HashMap();
        map.put("mphone", phone);
        OkHttp.get(this, Constant.select_user_by_phone, map, new OkCallback<Result<UsersBean>>() {
            @Override
            public void onResponse(Result<UsersBean> response) {
                SharePrefrenceUtil.saveObject(MainActivity.this, response.getData());
                if (response.getData() != null) {
                    if (SharePrefrenceUtil.getObject(MainActivity.this, UsersBean.class).getRole_id().equals("1")) {
                        Toast.makeText(MainActivity.this, "登 录 成 功 ！", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    }else if (SharePrefrenceUtil.getObject(MainActivity.this, UsersBean.class).getRole_id().equals("2")){
                        Toast.makeText(MainActivity.this, "登 录 成 功 ！", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, ShopkeeperActivity.class));
                    }else {
                        Toast.makeText(MainActivity.this, "登 录 成 功 ！", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, Notice.class));
                    }
                }else {
                    Toast.makeText(MainActivity.this, "登 录 失 败！", Toast.LENGTH_SHORT).show();
                }
                //todo 跳转需要角色判断


            }

            @Override
            public void onFailure(String state, String msg) {

            }
        });
    }

}
