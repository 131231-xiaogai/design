package com.example.newapplication.shopkeeper;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.R;
import com.example.newapplication.entity.ShopBean;
import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.new_utill.SharePrefrenceUtil;

import java.util.HashMap;
import java.util.Map;

public class Register_shopActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton save_insert_shop,insert_shop_back;
    EditText insert_shopname,insert_shop_address,insert_shop_phone;
    TextView regist_failure_description;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sk_register_shop);
        save_insert_shop=findViewById(R.id.save_insert_shop);
        insert_shop_back=findViewById(R.id.insert_shop_back);

        insert_shopname =findViewById(R.id.insert_shop_name);
        insert_shop_address =findViewById(R.id.insert_shop_address);
        insert_shop_phone=findViewById(R.id.insert_shop_phone);
        regist_failure_description =findViewById(R.id.regist_failure_description);
        OnClickListener();
    }

    private void OnClickListener() {
        insert_shop_back.setOnClickListener(this);
        save_insert_shop.setOnClickListener(this);
        insert_shopname.setOnClickListener(this);
        insert_shop_address.setOnClickListener(this);
        insert_shop_phone.setOnClickListener(this);
        regist_failure_description.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.insert_shop_back:
                Register_shopActivity.this.finish();
                break;
            case R.id.save_insert_shop:
                check_insertshop_data();
                break;
        }

    }

    private void check_insertshop_data() {
        String user_id = SharePrefrenceUtil.getObject(Register_shopActivity.this, UsersBean.class).getUerid();
        Map map = new HashMap();
        map.put("user_id",user_id);

        OkHttp.get(this, Constant.select_shop_by_userid, map, new OkCallback<Result<ShopBean>>() {
            @Override
            public void onResponse(Result<ShopBean> response) {
                if (response.getData() != null) {
                    Toast.makeText(Register_shopActivity.this, "注 册 失 败", Toast.LENGTH_SHORT).show();
                    regist_failure_description.setText("您已经有一家店铺了呦，一个商家手机号只能注册一个店铺。");
                }else {
                    save_insertshop_data(user_id);
                }
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(Register_shopActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void save_insertshop_data(String user_id) {

        Map map = new HashMap();
        map.put("user_id",user_id);
        map.put("shop_name",insert_shopname.getText().toString());
        map.put("shop_dresss",insert_shop_address.getText().toString());
        map.put("shop_phone",insert_shop_phone.getText().toString());
        Log.d("Register_shopActivity",insert_shopname.getText().toString());

        OkHttp.get(this, Constant.regiest_shop, map, new OkCallback<Result<String>>() {
            @Override
            public void onResponse(Result<String> response) {
                Toast.makeText(Register_shopActivity.this, "注 册 成 功 ！", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(Register_shopActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
