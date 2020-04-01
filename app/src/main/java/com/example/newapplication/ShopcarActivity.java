package com.example.newapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.newapplication.Adapter.PhotoAdapter;
import com.example.newapplication.Adapter.ShopcarAdapter;
import com.example.newapplication.entity.Photo;
import com.example.newapplication.entity.Shooping_carBean;
import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.home.ItemDetailActivity;
import com.example.newapplication.inteface.OnItemClickListener;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.new_utill.SharePrefrenceUtil;
import com.example.newapplication.newpage.Notice;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopcarActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton btn_list, btn_date, btn_home, btn_me;
    private ShopcarAdapter shopcarAdapter;
    ImageView btn_notice;
    RecyclerView s_recycle_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopcar);
        btn_home = (ImageButton)findViewById(R.id.b_home);
        btn_list = (ImageButton)findViewById(R.id.b_list);
        btn_date = (ImageButton)findViewById(R.id.b_date);
        btn_me = (ImageButton)findViewById(R.id.b_me);
        btn_notice = findViewById(R.id.btn_notice);
        btn_notice.setOnClickListener(this);
        btn_home.setOnClickListener(this);
        btn_list.setOnClickListener(this);
        btn_date.setOnClickListener(this);
        btn_me.setOnClickListener(this);
        s_recycle_view = findViewById(R.id.s_recycle_view);

        //添加适配器
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        s_recycle_view.setLayoutManager(layoutManager);
        shopcarAdapter = new ShopcarAdapter(this);
        s_recycle_view.setAdapter(shopcarAdapter);

        shopcarAdapter.setOnItemClickListener(new OnItemClickListener<Shooping_carBean>() {
            @Override
            public void onItemClick(RecyclerViewHolder viewHolder, Shooping_carBean data, int position) {
                Toast.makeText(ShopcarActivity.this, data.getGoods_id(), Toast.LENGTH_SHORT).show();
                String da = data.getGoods_id();
                Intent intent = new Intent(ShopcarActivity.this, ItemDetailActivity.class);
                intent.putExtra("hgoodid", da);
                startActivityForResult(intent,1);
            }
        });
        loadData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String data_return = data.getStringExtra("data_return");
                    Log.d("my_address",data_return);
                    loadData();
                }
                break;
            default:
        }
    }

    private void loadData() {
        String user_id = SharePrefrenceUtil.getObject(ShopcarActivity.this, UsersBean.class).getUerid();

        Map map = new HashMap();
        map.put("user_id",user_id);

        OkHttp.get(this, Constant.select_shopcar_by_userid, map, new OkCallback<Result<List<Shooping_carBean>>>() {
            @Override
            public void onResponse(Result<List<Shooping_carBean>> response) {
                shopcarAdapter.setNewData(response.getData());
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(ShopcarActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void finish_reback(View v){
        ShopcarActivity.this.finish();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b_home:
                startActivity(new Intent(ShopcarActivity.this,HomeActivity.class));
                break;
            case R.id.b_list:
                startActivity(new Intent(ShopcarActivity.this,ListActivity.class));
                break;
            case R.id.b_date:
                startActivity(new Intent(ShopcarActivity.this,DateActivity.class));
                break;
            case R.id.b_me:
                startActivity(new Intent(ShopcarActivity.this,MeActivity.class));
                break;
            case R.id.btn_notice:
                startActivity(new Intent(ShopcarActivity.this, Notice.class));
                break;
        }
    }

}
