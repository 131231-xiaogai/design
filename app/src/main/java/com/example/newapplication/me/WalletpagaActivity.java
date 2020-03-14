package com.example.newapplication.me;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.DateActivity;
import com.example.newapplication.HomeActivity;
import com.example.newapplication.ListActivity;
import com.example.newapplication.MeActivity;
import com.example.newapplication.R;
import com.example.newapplication.ShopcarActivity;
import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.newpage.Notice;

import java.util.HashMap;
import java.util.Map;


public class WalletpagaActivity extends AppCompatActivity implements View.OnClickListener {
        ImageButton P_notice;
        Intent pagename_integer;
        TextView mpageneme,mbalance;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.m_walletpaga);
            mbalance=findViewById(R.id.balance);
            mpageneme=findViewById(R.id.pageneme);
            P_notice=findViewById(R.id.P_notice);
            //
            pagename_integer = getIntent();
            String  data = pagename_integer.getStringExtra("balance");
            mpageneme.setText(data);
            Log.d("WalletpagaActivity",data);
            OnClickListener();
            lodata();
        }

    private void lodata() {
        Map map = new HashMap() ;
        //map. put("uerid" , m_userid) ;
        OkHttp. get(this, Constant.select_user_by_id, map,new OkCallback<Result<UsersBean>>() {
            @Override
            public void onResponse(Result<UsersBean> response) {
                if (response.getData() != null) {
                    String balance = response.getData().getBalance();
                    if (balance == null || balance.isEmpty()) {
                        mbalance.setText("无");
                    } else {
                        mbalance.setText(balance);

                    }
                }
            }

            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(WalletpagaActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

            }



        private void OnClickListener(){
            P_notice.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.P_notice:
                    startActivity(new Intent(WalletpagaActivity.this, Notice.class));
                    break;

            }

        }


    }

