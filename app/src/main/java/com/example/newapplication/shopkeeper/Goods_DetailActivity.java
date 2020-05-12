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
import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.new_utill.SharePrefrenceUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Goods_DetailActivity extends AppCompatActivity implements View.OnClickListener {
    TextView l_goodid,l_goodname,gdd_yajin,good_price,good_size,back,gdd_goood_type,gdd_good_number;
    ImageView l_img;
    Button go_to_update,to_deleted;
    private GoodBean data;
    String pagenumber,myshop_id;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_detail);

        l_goodid=findViewById(R.id.gdd_goodid);
        l_goodname=findViewById(R.id.gdd_goodname);
        l_img=findViewById(R.id.gdd_img);
        gdd_yajin=findViewById(R.id.gdd_goood_yajin);
        good_price=findViewById(R.id.gdd_goood_price);
        gdd_goood_type=findViewById(R.id.gdd_goood_type);
        back=findViewById(R.id.gdd_back);
        go_to_update =findViewById(R.id.go_to_update);
        gdd_good_number=findViewById(R.id.gdd_good_number);
        to_deleted = findViewById(R.id.to_deleted);
        //
        //从DeletedGoodsActivity接收数据
        Intent goodid_integer = getIntent();
        String  da = goodid_integer.getStringExtra("hgoodid");
        pagenumber =  goodid_integer.getStringExtra("my_pagenumber");
        myshop_id  =  goodid_integer.getStringExtra("myshop_id");
        l_goodid.setText(da);
        Log.d("Goods_DetailActivity",da);

        //
        if (pagenumber.equals("11")){
            to_deleted.setBackground(getResources().getDrawable(R.drawable.button_bg1));
        }
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
                    Glide.with(Goods_DetailActivity.this).load(data.getGood_img()).into(l_img);
                }
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(Goods_DetailActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
       });
    }

    private void OnClickListener() {
        back.setOnClickListener(this);
        go_to_update.setOnClickListener(this);
        to_deleted.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gdd_back:
                Goods_DetailActivity.this.finish();
                break;
            case R.id.to_deleted:
                if (pagenumber.equals("1")){
                    deleted_to_goods();
                }else {
                    Toast.makeText(Goods_DetailActivity.this, "请在“下架商品”页面使用此按钮。", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.go_to_update:
                Intent intent = new Intent(Goods_DetailActivity.this, Update_GoodslActivity.class);
                intent.putExtra("hgoodid", l_goodid.getText().toString());
                intent.putExtra("myshop_id",myshop_id);
                startActivityForResult(intent,1);
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
                Toast.makeText(Goods_DetailActivity.this, "下 架 成 功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("data_return","updata_goods");
                setResult(RESULT_OK,intent);
                Log.d("updata_goods","updata_goods");
                Goods_DetailActivity.this.finish();
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(Goods_DetailActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String data_return = data.getStringExtra("data_return");
                    Log.d("updata_goods",data_return);
                    loadData();
                }
                break;

            default:
        }
    }


}
