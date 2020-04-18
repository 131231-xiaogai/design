package com.example.newapplication.Administrators;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.R;
import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.newapplication.new_utill.Constant.deleted_user_byId;
import static com.example.newapplication.new_utill.Constant.select_all_user;

public class Adm_ItemDetailActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton adm_a_back,adm_d_deleted;
    TextView muserId,mname,mphone,mIdnumber,msex,mblance;
    String  userId;
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.adm_itemdetail);

        adm_a_back=findViewById(R.id.adm_d_back);
        adm_d_deleted=findViewById(R.id.adm_d_deleted);
        muserId=findViewById(R.id.adm_d_user_id);
        mname=findViewById(R.id.adm_d_name);
        mphone=findViewById(R.id.adm_d_phone);
        mIdnumber=findViewById(R.id.adm_d_IDnumber);
        msex=findViewById(R.id.adm_d_sex);
        mblance=findViewById(R.id.adm_d_balance);
        //
        //接收上一个页面的数据
        Intent integer = getIntent();
        userId = integer.getStringExtra("userId");
        String  name = integer.getStringExtra("name");
        String  phone = integer.getStringExtra("phone");
        String  Idnumber = integer.getStringExtra("Idnumber");
        String  sex = integer.getStringExtra("sex");
        String  blance = integer.getStringExtra("blance");

        muserId.setText(userId);
        mname.setText(name);
        mphone.setText(phone);
        mIdnumber.setText(Idnumber);
        msex.setText(sex);
        mblance.setText(blance);
        //
        OnClickListener();

    }

    private void OnClickListener() {
        adm_a_back.setOnClickListener(this);
        adm_d_deleted.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.adm_d_back:
                Adm_ItemDetailActivity.this.finish();
                break;
            case R.id.adm_d_deleted:
                adm_deleted();
                break;
        }

    }


    private void adm_deleted() {
        Map map = new HashMap();
        map.put("userId",userId);
        Log.d("删 除 的 顾 客 编 号 为",userId);

        OkHttp.get(this, deleted_user_byId, map, new OkCallback<Result<String>>() {
            @Override
            public void onResponse(Result<String> response) {
                Toast.makeText(Adm_ItemDetailActivity.this, "注 销 成 功 。", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("data_return","删 除 顾 客 用 户");
                setResult(RESULT_OK,intent);
                Adm_ItemDetailActivity.this.finish();
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(Adm_ItemDetailActivity.this, msg, Toast.LENGTH_SHORT).show();

            }
        });


    }
}
