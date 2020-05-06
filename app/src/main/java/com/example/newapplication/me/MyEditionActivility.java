package com.example.newapplication.me;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.newapplication.Adapter.Order_evaluate_Adapter;
import com.example.newapplication.Adapter.Order_tui_Adapter;
import com.example.newapplication.R;
import com.example.newapplication.entity.OrderBean;
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

public class MyEditionActivility extends AppCompatActivity implements View.OnClickListener {

    private ImageButton e_title_back;
    private Order_evaluate_Adapter order_tui_adapter;
    private RecyclerView s_recycle_view;
    private SwipeRefreshLayout swipeRefreshLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_edition);
        e_title_back=findViewById(R.id.e_title_back);
        s_recycle_view=findViewById(R.id.evaluate_recycle_view);
        //
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        s_recycle_view.setLayoutManager(layoutManager);
        order_tui_adapter = new Order_evaluate_Adapter(this);
        s_recycle_view.setAdapter(order_tui_adapter);
        order_tui_adapter.setOnItemClickListener(new OnItemClickListener<OrderBean>() {
            @Override
            public void onItemClick(RecyclerViewHolder viewHolder, OrderBean data, int position) {
                Toast.makeText(MyEditionActivility.this, data.getGoods_id(), Toast.LENGTH_SHORT).show();
                String da = data.getGoods_id();
                Intent intent = new Intent(MyEditionActivility.this, ItemDetailActivity.class);
                intent.putExtra("hgoodid", da);
                startActivityForResult(intent,1);
            }
        });

        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout_evaluate);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LoData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        OnClickListener();
        LoData();
    }
    private void LoData() {
        String userID = SharePrefrenceUtil.getObject(MyEditionActivility.this, UsersBean.class).getUerid();
        String status = "4";
        Map map = new HashMap();
        map.put("user_id",userID);
        map.put("order_status",status);
        map.put("evaluate_status","0");//订单评价状态：1.已评价，0.未评价
        Log.d("用户编号为",userID);
        Log.d("订单状态为",status);

        OkHttp.get(this, Constant.select_order_by_UseridAndOrderStstus_evaluate_status, map, new OkCallback<Result<List<OrderBean>>>() {
            @Override
            public void onResponse(Result<List<OrderBean>> response) {
                order_tui_adapter.setNewData(response.getData());
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(MyEditionActivility.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void OnClickListener() {
        e_title_back.setOnClickListener(this);
    }
    public void finish_reback(View v){
        MyEditionActivility.this.finish();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.e_title_back:
                MyEditionActivility.this.finish();
                break;

        }

    }
}