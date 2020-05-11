package com.example.newapplication.me;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.newapplication.Adapter.OrderAdapter;
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

public class Order_fa extends AppCompatActivity implements View.OnClickListener {

    private  ImageButton title_back;
    private  TextView fa,fu,tui,shou;
    private  Button btn_fa;
    private OrderAdapter orderAdapter;
    private  RecyclerView s_recycle_view;
    private SwipeRefreshLayout swipeRefreshLayout;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_order_fa);
        title_back=findViewById(R.id.title_back);
        fa=findViewById(R.id.ord_fa);
        fu=findViewById(R.id.ord_fu);
        tui=findViewById(R.id.ord_tui);
        shou=findViewById(R.id.ord_shou);
        s_recycle_view=findViewById(R.id.order_recycle_view);
        //
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        s_recycle_view.setLayoutManager(layoutManager);
        orderAdapter = new OrderAdapter(this);
        s_recycle_view.setAdapter(orderAdapter);
        orderAdapter.setOnItemClickListener(new OnItemClickListener<OrderBean>() {
            @Override
            public void onItemClick(RecyclerViewHolder viewHolder, OrderBean data, int position) {
                Toast.makeText(Order_fa.this, data.getGoods_id(), Toast.LENGTH_SHORT).show();
                String da = data.getGoods_id();
                Intent intent = new Intent(Order_fa.this, ItemDetailActivity.class);
                intent.putExtra("hgoodid", da);
                startActivityForResult(intent,1);
            }
        });
        //
        fa.setBackground(getResources().getDrawable(R.drawable.button_bg3));
        fa.setTextColor(this.getResources().getColor(R.color.white));
        //
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout_fa);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LoData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        LoData();
        //
        OnClickListener();
    }

    private void LoData() {
        String userID = SharePrefrenceUtil.getObject(Order_fa.this, UsersBean.class).getUerid();
        String status = "2";
        Map map = new HashMap();
        map.put("user_id",userID);
        map.put("order_status",status);
        Log.d("用户编号为",userID);
        Log.d("订单状态为",status);

        OkHttp.get(this, Constant.select_order_by_UseridAndOrderStstus, map, new OkCallback<Result<List<OrderBean>>>() {
            @Override
            public void onResponse(Result<List<OrderBean>> response) {
                orderAdapter.setNewData(response.getData());
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(Order_fa.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void OnClickListener() {
        fa.setOnClickListener(this);
        fu.setOnClickListener(this);
        tui.setOnClickListener(this);
        shou.setOnClickListener(this);


    }
    public void finish_reback(View v){
        Order_fa.this.finish();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ord_fa:
                Toast.makeText(Order_fa.this, "您 正 在 当 前 页 。", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ord_fu:
                startActivity(new Intent(Order_fa.this, Order_fu.class));
                Order_fa.this.finish();
                break;
            case R.id.ord_tui:
                startActivity(new Intent(Order_fa.this, Order_tui.class));
                Order_fa.this.finish();
                break;
            case R.id.ord_shou:
                startActivity(new Intent(Order_fa.this, Order_shou.class));
                Order_fa.this.finish();
                break;
        }

    }
}
