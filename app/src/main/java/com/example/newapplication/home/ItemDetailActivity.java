package com.example.newapplication.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.HomeActivity;
import com.example.newapplication.R;
import com.example.newapplication.entity.GoodBean;
import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.new_utill.SharePrefrenceUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemDetailActivity extends AppCompatActivity implements View.OnClickListener {
    TextView l_goodid,l_goodname,l_shopname,good_price,good_size,back;
    ImageView l_img;
    Button add_to_shopcar,to_pay;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemdetail);
        l_goodid=findViewById(R.id.l_goodid);
        l_goodname=findViewById(R.id.l_goodname);
        l_img=findViewById(R.id.l_img);
        l_shopname=findViewById(R.id.l_shopname);
        good_price=findViewById(R.id.l_goodprice);
        good_size=findViewById(R.id.good_size);
        back=findViewById(R.id.back);
        add_to_shopcar =findViewById(R.id.add_to_shopcar);
        to_pay = findViewById(R.id.to_pay);
        //
        Intent goodid_integer = getIntent();
        String  da = goodid_integer.getStringExtra("hgoodid");
        l_goodid.setText(da);
        Log.d("ItemDetailActivity",da);
        lodata();
        OnClickListener();
    }

    private void lodata() {
        Map map = new HashMap();
        map.put("goods_id",l_goodid.getText().toString());
        OkHttp.get(this, Constant.select_good_by_id, map, new OkCallback<Result<GoodBean>>() {
            @Override
            public void onResponse(Result<GoodBean> response) {
                if (response.getData() != null) {
                    String gname = response.getData().getGoods_name();
                    String pice = response.getData().getGoods_price();
                    String size = response.getData().getSize();
                    if (gname == null || gname.isEmpty()) {
                        l_goodname.setText("无");
                    } else {
                        l_goodname.setText(gname);
                    }
                    if (pice == null || pice.isEmpty()) {
                        good_price.setText("无");
                    } else {
                        good_price.setText(pice);
                    }
                    if (size == null || size.isEmpty()) {
                        good_size.setText("无");
                    } else {
                        good_size.setText(size);
                    }
                }
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(ItemDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void OnClickListener() {
        back.setOnClickListener(this);
        add_to_shopcar.setOnClickListener(this);
        to_pay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                ItemDetailActivity.this.finish();
                break;
            case R.id.add_to_shopcar:
                addto_shopcar();
                break;
            case R.id.to_pay:
                break;
        }
    }

    private void addto_shopcar() {
        String userid=SharePrefrenceUtil.getObject(ItemDetailActivity.this, UsersBean.class).getUerid();
        String goodid=l_goodid.getText().toString();
        String good_number ="1";

        Map map = new HashMap();
        map.put("goods_id",goodid);
        map.put("userid",userid);
        map.put("good_number",good_number);

        OkHttp.get(this, Constant.add_to_shopcar, map, new OkCallback<Result<String>>() {
            @Override
            public void onResponse(Result<String> response) {
                Toast.makeText(ItemDetailActivity.this, "已收藏", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(ItemDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

}
