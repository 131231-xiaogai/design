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

import com.example.newapplication.Adapter.OrderAdapter;
import com.example.newapplication.Adapter.Order_fu_Adapter;
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

public class Order_fu extends AppCompatActivity implements View.OnClickListener {
    ImageButton title_back;
    TextView ord_fu;
    TextView fa,tui,shou;

    private Order_fu_Adapter order_fu_adapter;
    RecyclerView s_recycle_view;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_order_fu);
        ord_fu=findViewById(R.id.ord_fu);
        fa=findViewById(R.id.ord_fa);
        s_recycle_view=findViewById(R.id.orderFU_recycle_view);
        tui=findViewById(R.id.ord_tui);
        shou=findViewById(R.id.ord_shou);
        //-------------------------------/
        ord_fu.setBackground(getResources().getDrawable(R.drawable.button_bg3));
        ord_fu.setTextColor(this.getResources().getColor(R.color.white));
        //------------------------------------/
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        s_recycle_view.setLayoutManager(layoutManager);
        order_fu_adapter = new Order_fu_Adapter(this);
        s_recycle_view.setAdapter(order_fu_adapter);
        order_fu_adapter.setOnItemClickListener(new OnItemClickListener<OrderBean>() {
            @Override
            public void onItemClick(RecyclerViewHolder viewHolder, OrderBean data, int position) {
                Toast.makeText(Order_fu.this, data.getGoods_id(), Toast.LENGTH_SHORT).show();
                String da = data.getGoods_id();
                Intent intent = new Intent(Order_fu.this, ItemDetailActivity.class);
                intent.putExtra("hgoodid", da);
                startActivityForResult(intent,1);


            }
        });
        //------------------------------------/
        LoData();
        OnClickListener();
    }
    private void LoData() {
        String userID = SharePrefrenceUtil.getObject(Order_fu.this, UsersBean.class).getUerid();
        String status = "1";
        Map map = new HashMap();
        map.put("user_id",userID);
        map.put("order_status",status);
        Log.d("用户编号为",userID);
        Log.d("订单状态为",status);

        OkHttp.get(this, Constant.select_order_by_UseridAndOrderStstus, map, new OkCallback<Result<List<OrderBean>>>() {
            @Override
            public void onResponse(Result<List<OrderBean>> response) {
                order_fu_adapter.setNewData(response.getData());
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(Order_fu.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void finish_reback(View v){
        Order_fu.this.finish();
    }

    private void OnClickListener() {
        fa.setOnClickListener(this);
        ord_fu.setOnClickListener(this);
        tui.setOnClickListener(this);
        shou.setOnClickListener(this);


        //title_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ord_fa:
                startActivity(new Intent(Order_fu.this, Order_fa.class));
                break;
            case R.id.ord_fu:
                Toast.makeText(Order_fu.this, "您 正 在 当 前 页 。", Toast.LENGTH_SHORT).show();

                break;
            case R.id.ord_tui:
                startActivity(new Intent(Order_fu.this, Order_tui.class));
                break;
            case R.id.ord_shou:
                startActivity(new Intent(Order_fu.this, Order_shou.class));
                break;
        }


    }
}
