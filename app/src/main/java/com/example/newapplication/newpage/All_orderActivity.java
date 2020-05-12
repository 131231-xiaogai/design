package com.example.newapplication.newpage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.newapplication.Adapter.All_OrderAdapter;
import com.example.newapplication.Adapter.OrderAdapter;
import com.example.newapplication.R;
import com.example.newapplication.entity.OrderBean;
import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.home.ItemDetailActivity;
import com.example.newapplication.inteface.OnItemClickListener;
import com.example.newapplication.me.Order_fa;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.new_utill.SharePrefrenceUtil;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class All_orderActivity extends AppCompatActivity implements View.OnClickListener {

    private  ImageButton title_back,notic,search;
    private All_OrderAdapter orderAdapter;
    private RecyclerView s_recycle_view;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String shop_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_order);
        title_back=findViewById(R.id.order_all_back);
        notic=findViewById(R.id.order_all_notice);
        //--********--//
        s_recycle_view=findViewById(R.id.all_order_recycle_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        s_recycle_view.setLayoutManager(layoutManager);
        orderAdapter = new All_OrderAdapter(this);
        s_recycle_view.setAdapter(orderAdapter);
        orderAdapter.setOnItemClickListener(new OnItemClickListener<OrderBean>() {
            @Override
            public void onItemClick(RecyclerViewHolder viewHolder, OrderBean data, int position) {
                Toast.makeText(All_orderActivity.this, data.getOrder_id(), Toast.LENGTH_SHORT).show();
                String da = data.getGoods_id();
                Intent intent = new Intent(All_orderActivity.this, OrderDetailActivity.class);
                intent.putExtra("hgoodid", da);
                intent.putExtra("img",data.getGood_img());
                intent.putExtra("name",data.getGood_name());
                intent.putExtra("price",data.getGood_price());
                intent.putExtra("yajin",data.getGoods_yajin());
                intent.putExtra("number",data.getGood_number());
                intent.putExtra("totalprice",data.getTotal_price());
                intent.putExtra("address",data.getAddress());
                Log.d("地址编号为",data.getAddress());
                intent.putExtra("shop_name",data.getShop_id());
                intent.putExtra("deliver",data.getDeliver());
                intent.putExtra("crateTime",data.getOrder_creat_time());
                intent.putExtra("startTime",data.getOrder_rent_validation_time());
                intent.putExtra("finishTime",data.getOrder_rent_finesh_time());
                intent.putExtra("order_code",data.getOrder_code());
                intent.putExtra("order_id",data.getOrder_id());

                startActivityForResult(intent,1);
            }
        });
       //----************----//
        LoData();
        OnClickListener();
    }

    private void LoData() {
        String userID = SharePrefrenceUtil.getObject(All_orderActivity.this, UsersBean.class).getUerid();
        Map map = new HashMap();
        map.put("user_id",userID);
        Log.d("用户编号为",userID);
        OkHttp.get(this, Constant.select_allOrder_byUserid, map, new OkCallback<Result<List<OrderBean>>>() {
            @Override
            public void onResponse(Result<List<OrderBean>> response) {
                orderAdapter.setNewData(response.getData());
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(All_orderActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void OnClickListener() {
        notic.setOnClickListener(this);
        title_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.order_all_back:
                All_orderActivity.this.finish();
                break;
            case R.id.order_all_notice:
                startActivity(new Intent(All_orderActivity.this, Notice.class));
                break;
        }
    }
}
