package com.example.newapplication.me;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class Insert_Address extends AppCompatActivity implements View.OnClickListener {

    EditText insert_address_name,insert_address_phone,insert_address1,insert_address2;
    ImageButton insert_address_back,insert_address_save;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_insert_address);
        //绑定控件
        insert_address_name =findViewById(R.id.insert_address_name);
        insert_address_phone =findViewById(R.id.insert_address_phone);
        insert_address1 =findViewById(R.id.insert_address1);
        insert_address2 =findViewById(R.id.insert_address2);
        insert_address_back =findViewById(R.id.insert_address_back);
        insert_address_save =findViewById(R.id.insert_address_save);
        

        //添加监听器
        OnClickListener();
    }

    private void OnClickListener() {
        insert_address_back.setOnClickListener(this);
        insert_address_save.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.insert_address_save:
                save_insert_address_Data();
                break;
            case R.id.insert_address_back:
                Insert_Address.this.finish();
                break;
        }
    }

    private void save_insert_address_Data() {
        String userid = SharePrefrenceUtil.getObject(Insert_Address.this, UsersBean.class).getUerid();
        String creator_phone =SharePrefrenceUtil.getObject(Insert_Address.this, UsersBean.class).getPhone();

        Map map = new HashMap();
        map.put("userid",userid);
        map.put("contact_name",insert_address_name.getText().toString());
        map.put("contact_phone",insert_address_phone.getText().toString());
        map.put("address_total",insert_address1.getText().toString());
        map.put("address_detail",insert_address2.getText().toString());
        map.put("creator_phone",creator_phone);

        OkHttp.get(this, Constant.insert_user_address, map, new OkCallback<Result<String>>() {
            @Override
            public void onResponse(Result<String> response) {
                Toast.makeText(Insert_Address.this, "您的新增地址已保存。", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("data_return","my_addresses");
                setResult(RESULT_OK,intent);
                finish();
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(Insert_Address.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
