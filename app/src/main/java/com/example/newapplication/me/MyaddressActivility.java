package com.example.newapplication.me;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.R;
import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.newpage.Notice;

import java.util.HashMap;
import java.util.Map;

public class MyaddressActivility extends AppCompatActivity implements View.OnClickListener {
    ImageButton a_title_back,a_notice;
    TextView maddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_address);

        //maddress=findViewById(R.id.address);
        a_title_back=findViewById(R.id.a_title_back);
        a_notice=findViewById(R.id.a_notice);
        //
        OnClickListener();
      //  lodata();
    }
    private void OnClickListener(){
        a_notice.setOnClickListener(this);
        a_title_back.setOnClickListener(this);
    }

//    private void lodata() {
//        Map map = new HashMap() ;
//        //map. put("uerid" , m_userid) ;
//        OkHttp. get(this, Constant.select_user_by_id, map,new OkCallback<Result<UsersBean>>() {
//            @Override
//            public void onResponse(Result<UsersBean> response) {
//                if (response.getData() != null) {
//                    String balance = response.getData().getBalance();
//                    if (balance == null || balance.isEmpty()) {
//                        maddress.setText("无");
//                    } else {
//                        maddress.setText();
//
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(String state, String msg) {
//                Toast.makeText(MyaddressActivility.this, msg, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.a_notice:
                startActivity(new Intent(MyaddressActivility.this, Notice.class));
                break;
            case R.id.a_title_back:
                MyaddressActivility.this.finish();
                break;

        }

    }


}