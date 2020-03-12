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

import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.new_utill.SMSTextView;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener  {

    Button btn_regiest;
    SMSTextView reg_getcode;
    EditText reg_e_ponenumber;
    TextView register_help;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        btn_regiest = (Button) findViewById(R.id.btn_regiesst);//绑定注册页面  注册按钮的id
        reg_getcode =  (SMSTextView)findViewById(R.id.reg_getcode);
        reg_e_ponenumber = (EditText) findViewById(R.id.reg_e_phonemb);
        register_help = findViewById(R.id.register_help);

        onClickListener();
    }

    private void onClickListener() {
        btn_regiest.setOnClickListener(this);//添加监听器
        reg_getcode.setOnClickListener(this);
        reg_e_ponenumber.setOnClickListener(this);
        register_help.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_help:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0596-1234567"));
                startActivity(intent);
                break;
            case R.id.btn_regiesst:

                break;
            case R.id.getcode:
                //
                Map<String, String> map = new HashMap<>();
                map.put("phone", reg_e_ponenumber.getText().toString());
/*
                OkHttp.post(this, Constant.create_code, map, new OkCallback() {
                    @Override
                    //返回给用户验证码
                    public void onResponse(Result response) {
                        reg_getcode.start();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        Notification.Builder builder1 = new Notification.Builder(RegisterActivity.this);
                        builder1.setSmallIcon(R.drawable.ic_launcher_background); //设置图标
                        builder1.setTicker("显示第二个通知");
                        builder1.setContentTitle("通知"); //设置标题
                        builder1.setContentText(response.getData() + ""); //消息内容
                        builder1.setWhen(System.currentTimeMillis()); //发送时间
                        builder1.setDefaults(Notification.DEFAULT_ALL); //设置默认的提示音，振动方式，灯光
                        builder1.setAutoCancel(true);//打开程序后图标消失
                        Notification notification1 = builder1.build();
                        notificationManager.notify(124, notification1); // 通过通知管理器发送通知
                    }
                    @Override
                    public void onFailure(String state, String msg) {
                        Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
*/


        }

    }
}
