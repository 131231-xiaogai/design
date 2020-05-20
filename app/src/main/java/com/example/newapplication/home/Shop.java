package com.example.newapplication.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.newapplication.Adapter.Shopkeeper_goodAdapter;
import com.example.newapplication.HomeActivity;
import com.example.newapplication.R;
import com.example.newapplication.entity.GoodBean;
import com.example.newapplication.inteface.OnItemClickListener;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.shopkeeper.DeletedGoodsActivity;
import com.example.newapplication.shopkeeper.Goods_DetailActivity;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shop extends AppCompatActivity implements View.OnClickListener{
    private TextView shop_back,shop_to_home,shop_shopname,shop_to_findgood;
    private RecyclerView shop_recycle_view;
    private String shop_id,shopname;
    private Shopkeeper_goodAdapter shopkeeper_goodAdapter;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop);
        shop_back=findViewById(R.id.shop_back);
        shop_back.setOnClickListener(this);
        shop_to_home=findViewById(R.id.shop_to_home);
        shop_to_home.setOnClickListener(this);
        shop_shopname=findViewById(R.id.shop_shopname);
        shop_recycle_view=findViewById(R.id.shop_recycle_view);
        shop_to_findgood=findViewById(R.id.shop_to_findgood);
        shop_to_findgood.setOnClickListener(this);
        //
        Intent goodid_integer = getIntent();
        shop_id = goodid_integer.getStringExtra("shop_id");
        shopname = goodid_integer.getStringExtra("shopname");
        shop_shopname.setText(shopname);
        Log.d("用户查看店铺",shop_id);
        //
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        shop_recycle_view.setLayoutManager(layoutManager);
        shopkeeper_goodAdapter = new Shopkeeper_goodAdapter(this);
        shop_recycle_view.setAdapter(shopkeeper_goodAdapter);

        shopkeeper_goodAdapter.setOnItemClickListener(new OnItemClickListener<GoodBean>() {
            @Override
            public void onItemClick(RecyclerViewHolder viewHolder, GoodBean data, int position) {

                String da = data.getGoods_id();
                Intent intent = new Intent(Shop.this, ItemDetailActivity.class);
                intent.putExtra("hgoodid", da);
                startActivity(intent);
            }
        });
        lodata();
    }

    private void lodata() {
        Map map = new HashMap();
        map.put("myshop_id",shop_id);

        OkHttp.get(this, Constant.select_good_by_shopid, map, new OkCallback<Result<List<GoodBean>>>() {
            @Override
            public void onResponse(Result<List<GoodBean>> response) {
                shopkeeper_goodAdapter.setNewData(response.getData());
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(Shop.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shop_back:
                Shop.this.finish();
                break;
            case R.id.shop_to_home:
                startActivity(new Intent(Shop.this, HomeActivity.class));
                Shop.this.finish();
                break;
            case R.id.shop_to_findgood:
                Intent intent2 = new Intent(Shop.this, shop_select_goods.class);
                intent2.putExtra("my_shop_id",shop_id);
                startActivity(intent2);
                Shop.this.finish();
                break;
        }

    }
}
