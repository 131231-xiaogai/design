package com.example.newapplication.shopkeeper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

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

    ImageButton skd_title_back;
    String  myshop_id,my_pagename;
    String pagenumber;
    private Shopkeeper_goodAdapter shopkeeper_goodAdapter;
    RecyclerView skd_recycle_view;
    TextView gdd_pageneme;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sk_deletedgoods);
        skd_title_back=findViewById(R.id.skd_title_back);
        skd_recycle_view=findViewById(R.id.skd_recycle_view);
        gdd_pageneme=findViewById(R.id.gdd_pageneme);
        //
         Intent pagename_integer = getIntent();
         myshop_id = pagename_integer.getStringExtra("myshop_id");
         my_pagename = pagename_integer.getStringExtra("my_pagename");
         gdd_pageneme.setText(my_pagename);
         pagenumber=  pagename_integer.getStringExtra("my_pagenumber");
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
        }

    }
}
