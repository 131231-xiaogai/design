package com.example.newapplication.shopkeeper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.newapplication.Adapter.Shopkeeper_goodAdapter;
import com.example.newapplication.R;
import com.example.newapplication.entity.GoodBean;
import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.home.ItemDetailActivity;
import com.example.newapplication.inteface.OnItemClickListener;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.new_utill.SharePrefrenceUtil;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeletedGoodsActivity extends AppCompatActivity implements View.OnClickListener  {

    private ImageButton skd_title_back,sk_addgood;
    private String  myshop_id,my_pagename,myshop_name;
    private String pagenumber;
    private Shopkeeper_goodAdapter shopkeeper_goodAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView skd_recycle_view;
    private TextView gdd_pageneme;
    private ImageView sk_de_good_find;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sk_deletedgoods);
        skd_title_back=findViewById(R.id.skd_title_back);
        skd_recycle_view=findViewById(R.id.skd_recycle_view);
        gdd_pageneme=findViewById(R.id.gdd_pageneme);
        sk_de_good_find=findViewById(R.id.sk_de_good_find);
        sk_addgood=findViewById(R.id.sk_addgood);
        //

        //从ShopkeeperActivity接受数据
         Intent pagename_integer = getIntent();
         myshop_id = pagename_integer.getStringExtra("myshop_id");
         my_pagename = pagename_integer.getStringExtra("my_pagename");
         gdd_pageneme.setText(my_pagename);
         pagenumber=  pagename_integer.getStringExtra("my_pagenumber");//myshop_name
        myshop_name=pagename_integer.getStringExtra("myshop_name");
         Log.d("DeletedGoodsActivity",myshop_id);

        //添加适配器
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        skd_recycle_view.setLayoutManager(layoutManager);
        shopkeeper_goodAdapter = new Shopkeeper_goodAdapter(this);
        skd_recycle_view.setAdapter(shopkeeper_goodAdapter);

        shopkeeper_goodAdapter.setOnItemClickListener(new OnItemClickListener<GoodBean>() {
            @Override
            public void onItemClick(RecyclerViewHolder viewHolder, GoodBean data, int position) {
                Toast.makeText(DeletedGoodsActivity.this, data.getGoods_id(), Toast.LENGTH_SHORT).show();
                String da = data.getGoods_id();
                Intent intent = new Intent(DeletedGoodsActivity.this, Goods_DetailActivity.class);
                intent.putExtra("hgoodid", da);
                intent.putExtra("myshop_id", myshop_id);
                intent.putExtra("my_pagenumber", pagenumber);
                startActivityForResult(intent,1);
            }
        });
        //
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout_goodDital);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        //
        loadData();

        OnClickListener();
    }

    private void loadData() {
        //String user_id = SharePrefrenceUtil.getObject(DeletedGoodsActivity.this, UsersBean.class).getUerid();

        Map map = new HashMap();
        map.put("myshop_id",myshop_id);

        OkHttp.get(this, Constant.select_good_by_shopid, map, new OkCallback<Result<List<GoodBean>>>() {
            @Override
            public void onResponse(Result<List<GoodBean>> response) {
                shopkeeper_goodAdapter.setNewData(response.getData());
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(DeletedGoodsActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void OnClickListener() {

        skd_title_back.setOnClickListener(this);
        sk_de_good_find.setOnClickListener(this);
        sk_addgood.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String data_return = data.getStringExtra("data_return");
                    Log.d("deleted_goods",data_return);
                    loadData();
                }
                break;

            default:
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.skd_title_back:
                DeletedGoodsActivity.this.finish();
                break;
            case R.id.sk_de_good_find:
                Intent intent = new Intent(DeletedGoodsActivity.this, Sk_select_goods.class);
                intent.putExtra("my_shop_id", myshop_id);
                intent.putExtra("my_pagenumber", pagenumber);
                startActivityForResult(intent,1);
                break;
            case R.id.sk_addgood:
                Intent intent1 = new Intent(DeletedGoodsActivity.this, AddGoodsActivity.class);
                intent1.putExtra("my_shop_id",myshop_id);
                intent1.putExtra("myshop_name",myshop_name);
                startActivity(intent1);
                DeletedGoodsActivity.this.finish();
                break;
        }

    }
}
