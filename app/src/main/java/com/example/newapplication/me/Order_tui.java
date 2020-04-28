package com.example.newapplication.me;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.newapplication.Adapter.OrderAdapter;
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

public class Order_tui extends AppCompatActivity implements View.OnClickListener {

    ImageButton title_back;
    TextView fa,fu,tui,shou;
    private Order_tui_Adapter order_tui_adapter;
    RecyclerView s_recycle_view;
    private SwipeRefreshLayout swipeRefreshLayout;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_order_tui);
        title_back=findViewById(R.id.title_back);
        fa=findViewById(R.id.ord_fa);
        fu=findViewById(R.id.ord_fu);
        tui=findViewById(R.id.ord_tui);
        shou=findViewById(R.id.ord_shou);
        s_recycle_view=findViewById(R.id.order_tui_recycle_view);
        //-------------------------------/
        tui.setBackground(getResources().getDrawable(R.drawable.button_bg3));
        tui.setTextColor(this.getResources().getColor(R.color.white));
        //------------------------------------/
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        s_recycle_view.setLayoutManager(layoutManager);
        order_tui_adapter = new Order_tui_Adapter(this);
        s_recycle_view.setAdapter(order_tui_adapter);
        order_tui_adapter.setOnItemClickListener(new OnItemClickListener<OrderBean>() {
            @Override
            public void onItemClick(RecyclerViewHolder viewHolder, OrderBean data, int position) {
                Toast.makeText(Order_tui.this, data.getGoods_id(), Toast.LENGTH_SHORT).show();
                String da = data.getGoods_id();
                Intent intent = new Intent(Order_tui.this, ItemDetailActivity.class);
                intent.putExtra("hgoodid", da);
                startActivityForResult(intent,1);
            }
        });
        //-------------------------------/
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout_tui);
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
        String userID = SharePrefrenceUtil.getObject(Order_tui.this, UsersBean.class).getUerid();
        String status = "4";
        Map map = new HashMap();
        map.put("user_id",userID);
        map.put("order_status",status);
        Log.d("用户编号为",userID);
        Log.d("订单状态为",status);

        OkHttp.get(this, Constant.select_order_by_UseridAndOrderStstus, map, new OkCallback<Result<List<OrderBean>>>() {
            @Override
            public void onResponse(Result<List<OrderBean>> response) {
                order_tui_adapter.setNewData(response.getData());
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(Order_tui.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void OnClickListener() {
        fa.setOnClickListener(this);
        fu.setOnClickListener(this);
        tui.setOnClickListener(this);
        shou.setOnClickListener(this);
       // title_back.setOnClickListener(this);
    }
    public void finish_reback(View v){
        Order_tui.this.finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ord_fa:
                startActivity(new Intent(Order_tui.this, Order_fa.class));
                break;
            case R.id.ord_fu:
                startActivity(new Intent(Order_tui.this, Order_fu.class));
                break;
            case R.id.ord_tui:
                Toast.makeText(Order_tui.this, "您 正 在 当 前 页 。", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ord_shou:
                startActivity(new Intent(Order_tui.this, Order_shou.class));
                break;
        }

    }
}
