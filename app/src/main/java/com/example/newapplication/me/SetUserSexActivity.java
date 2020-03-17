package com.example.newapplication.me;

import android.os.Bundle;
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

public class SetUserSexActivity extends AppCompatActivity implements View.OnClickListener {
    TextView setupsex_back;
    EditText s_e_sex;
    Button save_sex;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_setup_sex);
        setupsex_back=findViewById(R.id.setupsex_back);
        save_sex=findViewById(R.id.save_sex);
        s_e_sex=findViewById(R.id.s_e_sex);
        OnClickListener();
    }

    private void OnClickListener() {
        setupsex_back.setOnClickListener(this);
        s_e_sex.setOnClickListener(this);
        save_sex.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save_sex:
                insert();
                break;
            case R.id.setupsex_back:
                SetUserSexActivity.this.finish();
                break;
        }

    }

    private void insert() {
        String id = SharePrefrenceUtil.getObject(SetUserSexActivity.this, UsersBean.class).getUerid();
        Toast.makeText(SetUserSexActivity.this, s_e_sex.getText(), Toast.LENGTH_SHORT).show();
        Map map = new HashMap();
        map.put("muser_id",id);
        map.put("msex",s_e_sex.getText().toString());
        OkHttp.get(this, Constant.insert_user_sex, map, new OkCallback<Result<UsersBean>>() {
            @Override
            public void onResponse(Result<UsersBean> response) {
                //SharePrefrenceUtil.saveObject(SetUserNameActivity.this, response.getData());
                if (response.getData() != null) {
                    Toast.makeText(SetUserSexActivity.this, "保存成功！", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(SetUserSexActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

        });
    }
}
