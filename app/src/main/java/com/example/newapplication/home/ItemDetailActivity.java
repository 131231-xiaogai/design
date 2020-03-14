package com.example.newapplication.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.HomeActivity;
import com.example.newapplication.R;
import com.example.newapplication.entity.GoodBean;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemDetailActivity extends AppCompatActivity implements View.OnClickListener {
    TextView l_goodid,l_goodname,l_shopname,good_price,good_size,back;
    ImageView l_img;

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

                //goodAdapter.setNewData(response.getData());
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(ItemDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void OnClickListener() {
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                ItemDetailActivity.this.finish();
                break;

        }

    }

}
