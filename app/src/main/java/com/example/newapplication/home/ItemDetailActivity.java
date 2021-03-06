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

import com.bumptech.glide.Glide;
import com.example.newapplication.HomeActivity;
import com.example.newapplication.R;
import com.example.newapplication.ShopcarActivity;
import com.example.newapplication.entity.GoodBean;
import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.me.MyBodySizeActivility;
import com.example.newapplication.me.ShopDital;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.new_utill.SharePrefrenceUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView l_goodid,l_goodname,l_shopname,good_price,good_size,back,to_shopcar,
            gdd_good_clothing_length, gdd_good_sleeve_length, gdd_good_shoulder_width, gdd_good_trousers_length,
            to_shop,to_mysixe,to_shop_mes;
    private ImageView l_img;
    private  Button add_to_shopcar,to_pay;
    private GoodBean data;
    private String  da,shop_id,shopname;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemdetail);
        l_goodname=findViewById(R.id.l_goodname);
        l_img=findViewById(R.id.l_img);
        l_shopname=findViewById(R.id.l_shopname);
        good_price=findViewById(R.id.l_goodprice);
        gdd_good_clothing_length=findViewById(R.id.l_clothing_length);
        gdd_good_sleeve_length=findViewById(R.id.l_sleeve_length);
        gdd_good_shoulder_width=findViewById(R.id.l_shoulder_width);
        gdd_good_trousers_length=findViewById(R.id.l_trousers_length);
        good_size=findViewById(R.id.good_size);
        back=findViewById(R.id.back);
        add_to_shopcar =findViewById(R.id.add_to_shopcar);
        to_shopcar=findViewById(R.id.to_shopcar);
        to_shop=findViewById(R.id.to_shop);
        to_mysixe=findViewById(R.id.to_mysixe);
        to_shop_mes=findViewById(R.id.to_shop_mes);


        //
        Intent goodid_integer = getIntent();
        da = goodid_integer.getStringExtra("hgoodid");
        Log.d("ItemDetailActivity",da);
        lodata();
        OnClickListener();
    }

    private void lodata() {
        Map map = new HashMap();
        map.put("goods_id",da);
        OkHttp.get(this, Constant.select_good_by_id, map, new OkCallback<Result<GoodBean>>() {
            @Override
            public void onResponse(Result<GoodBean> response) {
                data = response.getData();
                if (data != null) {
                    String gname = data.getGoods_name();
                    String pice = data.getGoods_price();
                    String size = data.getSize();
                     shopname = data.getShop_name();
                    shop_id=data.getShop_id();

                    if (shopname == null || shopname.isEmpty()) {
                        l_shopname.setText("无");
                    } else {
                        l_shopname.setText(shopname);
                    }
                    if (gname == null || gname.isEmpty()) {
                        l_goodname.setText("无");
                    } else {
                        l_goodname.setText(gname);
                    }
                    if (pice == null || pice.isEmpty()) {
                        good_price.setText("无");
                    } else {
                        good_price.setText("租金（件）：￥"+pice+"/"+"押金"+data.getGoods_yajin());
                    }
                    if (size == null || size.isEmpty()) {
                        good_size.setText("无");
                    } else {
                        good_size.setText("商品尺码"+size);
                    }
                    if (data.getClothing_length()==null || data.getClothing_length().isEmpty()){
                        gdd_good_clothing_length.setText("衣服长度：暂无该参数");
                    }else {gdd_good_clothing_length.setText("衣服长度："+data.getClothing_length()+"cm");
                    }//1
                    if (data.getSleeve_length()==null || data.getSleeve_length().isEmpty()){
                        gdd_good_sleeve_length.setText("袖子长度：暂无该参数");
                    }else {gdd_good_sleeve_length.setText("袖子长度："+data.getSleeve_length()+"cm");
                    }//2
                    if (data.getShoulder_width()==null || data.getShoulder_width().isEmpty()){
                        gdd_good_shoulder_width.setText("肩膀宽度：暂无该参数");
                    }else {
                        gdd_good_shoulder_width.setText("肩膀宽度："+data.getShoulder_width()+"cm");
                    }//3
                    if (data.getTrousers_length()==null || data.getTrousers_length().isEmpty()){
                        gdd_good_trousers_length.setText("裤子长度：暂无该参数");
                    }else {
                        gdd_good_trousers_length.setText("裤子长度："+data.getTrousers_length()+"cm");
                    }//4
                    Glide.with(ItemDetailActivity.this).load(data.getGood_img()).into(l_img);

                    if(Integer.valueOf(data.getGoods_number())<0 ){
                        add_to_shopcar.setBackground(getResources().getDrawable(R.drawable.button_bg1));
                        add_to_shopcar.setText("该商品已售罄");
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
        to_shopcar.setOnClickListener(this);
        to_shop.setOnClickListener(this);
        to_mysixe.setOnClickListener(this);
        to_shop_mes.setOnClickListener(this);
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
            case R.id.to_shopcar:
                startActivity(new Intent(ItemDetailActivity.this, ShopcarActivity.class));
                ItemDetailActivity.this.finish();
                break;
            case R.id.to_shop:
                Intent intent1 = new Intent(ItemDetailActivity.this, Shop.class);
                intent1.putExtra("shop_id",shop_id);
                //shopname
                intent1.putExtra("shopname",shopname);
                startActivity(intent1);
                ItemDetailActivity.this.finish();
                break;
            case R.id.to_mysixe:
                //MyBodySizeActivility
                startActivity(new Intent(ItemDetailActivity.this, MyBodySizeActivility.class));
                break;
            case R.id.to_shop_mes:
                Intent intent2 = new Intent(ItemDetailActivity.this, ShopDital.class);
                intent2.putExtra("shop_id",shop_id);
                startActivity(intent2);
                break;

        }
    }

    private void addto_shopcar() {
        String userid=SharePrefrenceUtil.getObject(ItemDetailActivity.this, UsersBean.class).getUerid();
        String goodid=da;
        String good_number ="1";
        Map map = new HashMap();
        map.put("user_id",userid);
        map.put("goods_id",goodid);
        map.put("good_number",good_number);
        map.put("shop_id",shop_id);
        map.put("good_name",data.getGoods_name());
        map.put("good_price",data.getGoods_price());
        map.put("good_img",data.getGood_img());
        map.put("shop_name",shopname);
        map.put("shop_car_status","0");//0未形成订单，1为已形成订单
        map.put("goods_yajin",data.getGoods_yajin());
        map.put("good_size",data.getSize());
        OkHttp.get(this, Constant.add_to_shopcar, map, new OkCallback<Result<String>>() {
            @Override
            public void onResponse(Result<String> response) {
                Toast.makeText(ItemDetailActivity.this, "已添加", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("data_return","shopcar");
                setResult(RESULT_OK,intent);
                Log.d("my_address","shopcar");
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(ItemDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

}
