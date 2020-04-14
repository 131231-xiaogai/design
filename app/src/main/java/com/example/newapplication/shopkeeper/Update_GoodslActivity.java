package com.example.newapplication.shopkeeper;

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

import com.bumptech.glide.Glide;
import com.example.newapplication.R;
import com.example.newapplication.entity.GoodBean;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;

import java.util.HashMap;
import java.util.Map;

public class Update_GoodslActivity extends AppCompatActivity implements View.OnClickListener {
    TextView l_goodid,l_goodname,gdd_yajin,good_price,good_size,back,gdd_goood_type,gdd_good_number;
    ImageView l_img;
    Button to_save_update,to_deleted;
    private GoodBean data;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_update);
        l_goodid=findViewById(R.id.gup_goodid);
        l_goodname=findViewById(R.id.gup_goodname);
        l_img=findViewById(R.id.gup_img);
        gdd_yajin=findViewById(R.id.gup_goood_yajin);
        good_price=findViewById(R.id.gup_goood_price);
        gdd_goood_type=findViewById(R.id.gup_goood_type);
        back=findViewById(R.id.gup_back);
        to_save_update =findViewById(R.id.to_save_update);
        gdd_good_number=findViewById(R.id.gup_good_number);
        //to_deleted = findViewById(R.id.to_deleted);
        //
        Intent goodid_integer = getIntent();
        String  da = goodid_integer.getStringExtra("hgoodid");
        l_goodid.setText(da);
        Log.d("Goods_DetailActivity",da);
        //
        OnClickListener();
        loadData();
    }

   private void loadData() {
        Map map = new HashMap();
        map.put("goods_id",l_goodid.getText().toString());

       OkHttp.get(this, Constant.select_good_by_id, map, new OkCallback<Result<GoodBean>>() {
            @Override
            public void onResponse(Result<GoodBean> response) {
                data = response.getData();
                if (data != null) {
                    String gname = data.getGoods_name();
                    String pice = data.getGoods_price();
                    String goood_type = data.getType_id();
                    String yajin = data.getGoods_yajin();
                    String good_number = data.getGoods_number();

                    if (good_number == null || good_number.isEmpty()) {
                        gdd_good_number.setText("无");
                    } else {
                        gdd_good_number.setText(good_number);
                    }

                    if (yajin == null || yajin.isEmpty()) {
                        gdd_yajin.setText("无");
                    } else {
                        gdd_yajin.setText(yajin);
                    }
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
                    if (goood_type == null || goood_type.isEmpty()) {
                        gdd_goood_type.setText("无");
                    } else {
                        gdd_goood_type.setText(goood_type);
                    }
                    Glide.with(Update_GoodslActivity.this).load(data.getGood_img()).into(l_img);
                }
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(Update_GoodslActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
       });
    }

    private void OnClickListener() {
        back.setOnClickListener(this);
        to_save_update.setOnClickListener(this);
        to_deleted.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gdd_back:
                Update_GoodslActivity.this.finish();
                break;
//            case R.id.to_deleted:
//                deleted_to_goods();
//                break;
            case R.id.to_save_update:
//                Intent intent = new Intent(Goods_DetailActivity.this, DeletedGoodsActivity.class);
//                //intent.putExtra("myshop_id", myshop_id);
//                startActivity(intent);
                break;
        }
    }

    private void deleted_to_goods() {
        //String userid=SharePrefrenceUtil.getObject(Goods_DetailActivity.this, UsersBean.class).getUerid();
        String goodid=l_goodid.getText().toString();
        Map map = new HashMap();
        map.put("goods_id",goodid);

        OkHttp.get(this, Constant.deleted_goods_by_goodsid, map, new OkCallback<Result<String>>() {
            @Override
            public void onResponse(Result<String> response) {
                Toast.makeText(Update_GoodslActivity.this, "下 架 成 功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("data_return","deleted_goods");
                setResult(RESULT_OK,intent);
                Log.d("deleted_goods","deleted_goods");
                Update_GoodslActivity.this.finish();
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(Update_GoodslActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

}