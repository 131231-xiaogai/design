package com.example.newapplication.shopkeeper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.newapplication.Adapter.Sk_Order_fuAdapter;
import com.example.newapplication.R;
import com.example.newapplication.entity.GoodBean;
import com.example.newapplication.entity.OrderBean;
import com.example.newapplication.inteface.OnItemClickListener;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.newpage.Notice;
import com.example.newapplication.newpage.Sk_Notice;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sk_select_order extends AppCompatActivity implements View.OnClickListener {

    private TextView title_page;
    private ImageButton title_back,btn_notice;
    private String shop_id,status,my_find_name;
    private Sk_Order_fuAdapter sk_orderAdapter;
    private RecyclerView s_recycle_view;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ImageView sk_order_find;
    private EditText sk_input_goodname;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sk_select_orders);
        title_back =findViewById(R.id.sk_select_order_back);
        btn_notice=findViewById(R.id.sk_select_order_notice);
        sk_order_find=findViewById(R.id.sk_select_order_find);
        sk_input_goodname=findViewById(R.id.input_order_goodname);
        s_recycle_view=findViewById(R.id.sk_select_good_recycle_view);

        //接收数据
        Intent pagename_integer = getIntent();
        shop_id = pagename_integer.getStringExtra("my_shop_id");
        my_find_name= pagename_integer.getStringExtra("find_name");
        status= pagename_integer.getStringExtra("order_status");
        Log.d("商店编号",shop_id);
        sk_input_goodname.setText(my_find_name);

        //************-------------//
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        s_recycle_view.setLayoutManager(layoutManager);
        sk_orderAdapter = new Sk_Order_fuAdapter(this);
        s_recycle_view.setAdapter(sk_orderAdapter);
        sk_orderAdapter.setOnItemClickListener(new OnItemClickListener<OrderBean>() {
            @Override
            public void onItemClick(RecyclerViewHolder viewHolder, OrderBean data, int position) {
                Toast.makeText(Sk_select_order.this, data.getGoods_id(), Toast.LENGTH_SHORT).show();
            }
        });
        //-------------------**----------------------------//


        OnClickListener();

    }

    private void loadData() {

        Map map = new HashMap();
        map.put("shop_id",shop_id);
        map.put("order_status",status);
        map.put("good_name",sk_input_goodname.getText().toString());

        //select_shopOrder_byMonth
        OkHttp.get(this, Constant.select_order_byShopidAndOrderStstus_likeName, map, new OkCallback<Result<List<OrderBean>>>() {
            @Override
            public void onResponse(Result<List<OrderBean>> response) {
                sk_orderAdapter.setNewData(response.getData());
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(Sk_select_order.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void OnClickListener() {
            title_back.setOnClickListener(this);
            btn_notice.setOnClickListener(this);
            sk_order_find.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sk_select_order_find:
                loadData();
                break;
            case R.id.sk_select_order_back:
                Sk_select_order.this.finish();
                break;
            case R.id.sk_select_order_notice:
                Intent intent5 = new Intent(Sk_select_order.this, Sk_Notice.class);
                intent5.putExtra("my_shop_id",shop_id);
                startActivity(intent5);
                break;

        }

    }
}
