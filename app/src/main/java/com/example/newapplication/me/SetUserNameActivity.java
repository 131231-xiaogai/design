package com.example.newapplication.me;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.R;
import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.new_utill.SharePrefrenceUtil;

import java.util.HashMap;
import java.util.Map;

public class SetUserNameActivity extends AppCompatActivity implements View.OnClickListener{

    TextView setup_back;
    EditText setup_nickname;
    Button save_nick;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_setup_username);
        setup_back =findViewById(R.id.setup_back);
        setup_nickname =findViewById(R.id.setup_nickname);
        save_nick =findViewById(R.id.save_nick);
        OnClickListener();
    }

    private void OnClickListener() {
        setup_back.setOnClickListener(this);
        setup_nickname.setOnClickListener(this);
        save_nick.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.save_nick:
                insert();
                break;
            case R.id.setup_back:
                SetUserNameActivity.this.finish();
                break;

        }

    }



    private void insert() {
        String id = SharePrefrenceUtil.getObject(SetUserNameActivity.this, UsersBean.class).getUerid();
        Toast.makeText(SetUserNameActivity.this, setup_nickname.getText().toString(), Toast.LENGTH_SHORT).show();
        Log.d("SetUserNameActivity",setup_nickname.getText().toString());
        Map map = new HashMap();
        map.put("muser_id",id);
        map.put("mnickname",setup_nickname.getText().toString());
        OkHttp.get(this, Constant.insert_user_nickname, map, new OkCallback<Result<UsersBean>>() {
            @Override
            public void onResponse(Result<UsersBean> response) {
                //SharePrefrenceUtil.saveObject(SetUserNameActivity.this, response.getData());
                if (response.getData() != null) {
                    Toast.makeText(SetUserNameActivity.this, "保存成功！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.putExtra("data_return","my_mame");
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(SetUserNameActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

        });


    }
}
