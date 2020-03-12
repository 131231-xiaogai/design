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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.new_utill.SMSTextView;
import com.example.newapplication.new_utill.SharePrefrenceUtil;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener  {

    Button btn_register;
    EditText reg_phonemb,reg_bassword,reg_roleid;
    TextView register_help;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        btn_register = (Button) findViewById(R.id.btn_register);//绑定注册页面  注册按钮的id
        reg_phonemb = (EditText) findViewById(R.id.reg_phonemb);
        reg_bassword= (EditText) findViewById(R.id.reg_bassword);
        register_help = findViewById(R.id.register_help);
        reg_roleid = findViewById(R.id.reg_roleid);
        onClickListener();
    }

    private void onClickListener() {
        btn_register.setOnClickListener(this);//添加监听器
        reg_phonemb.setOnClickListener(this);
        reg_bassword.setOnClickListener(this);
        register_help.setOnClickListener(this);
        reg_roleid.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_help:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0596-1234567"));
                startActivity(intent);
                break;
            case R.id.btn_register:
             register();
                break;
        }
    }

    private void register() {
        Map map = new HashMap();
        map.put("reg_phonemb", reg_phonemb.getText().toString());
        map.put("reg_bassword", reg_bassword.getText().toString());
        map.put("reg_roleid", reg_roleid.getText().toString());
        OkHttp.get(this, Constant.register, map, new OkCallback<Result<String>>() {
            @Override
            public void onResponse(Result<String> response) {
                Toast.makeText(RegisterActivity.this, "注 册 成 功 ！", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
