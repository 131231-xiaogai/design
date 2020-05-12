package com.example.newapplication.me;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.newapplication.Adapter.OrderAdapter;
import com.example.newapplication.Adapter.Order_shou_Adapter;
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

public class Order_shou extends AppCompatActivity implements View.OnClickListener {

    ImageButton title_back;
    TextView fa,fu,tui,shou;
    private Order_shou_Adapter order_shou_adapter;
    RecyclerView s_recycle_view;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ImageView my_select_order_find;
    private EditText input_order_mygoodname;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_order_shou);
        title_back=findViewById(R.id.title_back);
        fa=findViewById(R.id.ord_fa);
        fu=findViewById(R.id.ord_fu);
        tui=findViewById(R.id.ord_tui);
        shou=findViewById(R.id.ord_shou);
        my_select_order_find=findViewById(R.id.my_select_order_find_shou);
        input_order_mygoodname=findViewById(R.id.input_order_mygoodname_shou);
        //
        s_recycle_view=findViewById(R.id.order_shou_recycle_view);
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LoData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        //-------------------------------/
        shou.setBackground(getResources().getDrawable(R.drawable.button_bg3));
        shou.setTextColor(this.getResources().getColor(R.color.white));
        //------------------------------------/
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        s_recycle_view.setLayoutManager(layoutManager);
        order_shou_adapter = new Order_shou_Adapter(this);
        s_recycle_view.setAdapter(order_shou_adapter);
        order_shou_adapter.setOnItemClickListener(new OnItemClickListener<OrderBean>() {
            @Override
            public void onItemClick(RecyclerViewHolder viewHolder, OrderBean data, int position) {
                Toast.makeText(Order_shou.this, data.getGoods_id(), Toast.LENGTH_SHORT).show();
                String da = data.getGoods_id();
                Intent intent = new Intent(Order_shou.this, ItemDetailActivity.class);
                intent.putExtra("hgoodid", da);
                startActivityForResult(intent,1);
            }
        });
        //
        LoData();
        OnClickListener();
    }

    private void LoData() {
        String userID = SharePrefrenceUtil.getObject(Order_shou.this, UsersBean.class).getUerid();
        String status = "3";
        Map map = new HashMap();
        map.put("user_id",userID);
        map.put("order_status",status);
        Log.d("用户编号为",userID);
        Log.d("订单状态为",status);

        OkHttp.get(this, Constant.select_order_by_UseridAndOrderStstus, map, new OkCallback<Result<List<OrderBean>>>() {
            @Override
            public void onResponse(Result<List<OrderBean>> response) {
                order_shou_adapter.setNewData(response.getData());
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(Order_shou.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void finish_reback(View v){
        Order_shou.this.finish();
    }

    private void OnClickListener() {
        fa.setOnClickListener(this);
        fu.setOnClickListener(this);
        tui.setOnClickListener(this);
        shou.setOnClickListener(this);
        my_select_order_find.setOnClickListener(this);

        //title_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ord_fa:
                startActivity(new Intent(Order_shou.this, Order_fa.class));
                Order_shou.this.finish();
                break;
            case R.id.ord_fu:
                startActivity(new Intent(Order_shou.this, Order_fu.class));
                Order_shou.this.finish();
                break;
            case R.id.ord_tui:
                startActivity(new Intent(Order_shou.this, Order_tui.class));
                Order_shou.this.finish();
                break;
            case R.id.ord_shou:
                Toast.makeText(Order_shou.this, "您 正 在 当 前 页 。", Toast.LENGTH_SHORT).show();
                break;
            case R.id.my_select_order_find_shou:
                Intent intent1 = new Intent(Order_shou.this, My_select_order_fa.class);
                intent1.putExtra("order_status","3");
                intent1.putExtra("find_name",input_order_mygoodname.getText().toString());
                startActivity(intent1);
                break;
        }



    }

}
