package com.example.newapplication;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.new_utill.SMSTextView;
import com.example.newapplication.newpage.BaseActivity;
import com.example.newapplication.newpage.Phone_help;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    SMSTextView getcode;
    EditText eh, em, ey;
    TextView bm, by, bz,lonin_help;
    LinearLayout lm, ly;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eh = (EditText) findViewById(R.id.e_phonemb);
        em = (EditText) findViewById(R.id.e_bassword);
        ey = (EditText) findViewById(R.id.e_pass);

        lonin_help =(TextView)findViewById(R.id.lonin_help);

        bm = (TextView) findViewById(R.id.b_m);
        by = (TextView) findViewById(R.id.b_y);
        bz = (TextView) findViewById(R.id.b_z);

        lm = (LinearLayout) findViewById(R.id.lm);
        ly = (LinearLayout) findViewById(R.id.ly);

        login = findViewById(R.id.login);

        getcode = (SMSTextView) findViewById(R.id.getcode);


        bm.setOnClickListener(this);
        by.setOnClickListener(this);
        bz.setOnClickListener(this);
        lonin_help.setOnClickListener(this);
        login.setOnClickListener(this);

        ly.setVisibility(View.GONE);
        getcode.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.lonin_help:
                startActivity(new Intent(MainActivity.this,Phone_help.class));
                break;
            case R.id.login:
                startActivity(new Intent(MainActivity.this,HomeActivity.class));
                break;
            case R.id.b_m:
                ly.setVisibility(View.GONE);
                lm.setVisibility(View.VISIBLE);
                break;
            case R.id.b_y:
                lm.setVisibility(View.GONE);
                ly.setVisibility(View.VISIBLE);
                break;
            case R.id.b_z:
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
            case R.id.getcode:
                //
                Map<String, String> map = new HashMap<>();
                map.put("phone", eh.getText().toString());

                OkHttp.post(this, Constant.getcode, map, new OkCallback() {
                    @Override
                    //返回给用户验证码
                    public void onResponse(Result response) {
                        getcode.start();
                        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        Notification.Builder builder1 = new Notification.Builder(MainActivity.this);
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

                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

                    }
                });


        }


    }
}
