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

import com.example.newapplication.Adapter.All_OrderAdapter;
import com.example.newapplication.R;
import com.example.newapplication.entity.OrderBean;
import com.example.newapplication.inteface.OnItemClickListener;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.newpage.Notice;
import com.example.newapplication.newpage.OrderDetailActivity;
import com.example.newapplication.newpage.Sk_Notice;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sk_more_maney extends AppCompatActivity implements View.OnClickListener  {

    private ImageButton title_back,notic,search;
    private All_OrderAdapter orderAdapter;
    private RecyclerView s_recycle_view;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String shop_id;
    private EditText input_yue;
    private ImageView money_to_find;
    private TextView money_yue_total;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sk_more_money);
        title_back=findViewById(R.id.more_money_back);
        notic=findViewById(R.id.more_money_notice);
        input_yue=findViewById(R.id.input_yue);
        money_to_find=findViewById(R.id.money_to_find);
        money_yue_total=findViewById(R.id.money_yue_total);
        //--********--//
        s_recycle_view=findViewById(R.id.more_money_recycle_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        s_recycle_view.setLayoutManager(layoutManager);
        orderAdapter = new All_OrderAdapter(this);
        s_recycle_view.setAdapter(orderAdapter);
        orderAdapter.setOnItemClickListener(new OnItemClickListener<OrderBean>() {
            @Override
            public void onItemClick(RecyclerViewHolder viewHolder, OrderBean data, int position) {
                Toast.makeText(Sk_more_maney.this, data.getOrder_id(), Toast.LENGTH_SHORT).show();
                String da = data.getGoods_id();
                Intent intent = new Intent(Sk_more_maney.this, OrderDetailActivity.class);
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
        Intent pagename_integer = getIntent();
        shop_id = pagename_integer.getStringExtra("my_shop_id");
        Log.d("商店编号",shop_id);

        OnClickListener();
    }

    private void LoData() {
        Map map = new HashMap();
        map.put("shop_id",shop_id);
        map.put("yue",input_yue.getText().toString());
        Log.d("商店编号为",shop_id);
        OkHttp.get(this, Constant.select_shopOrder_byMonth, map, new OkCallback<Result<List<OrderBean>>>() {
            @Override
            public void onResponse(Result<List<OrderBean>> response) {
                orderAdapter.setNewData(response.getData());
                double totalPrice=0;
                for (int i = 0; i < response.getData().size(); i++) {
                    totalPrice= totalPrice+Double.valueOf(response.getData().get(i).getGood_price());
                }
                money_yue_total.setText("该月收入总计：￥"+totalPrice);
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(Sk_more_maney.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void OnClickListener() {
        title_back.setOnClickListener(this);
        notic.setOnClickListener(this);
        money_to_find.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.more_money_back:
                Sk_more_maney.this.finish();
                break;
            case R.id.more_money_notice:
                Intent intent5 = new Intent(Sk_more_maney.this, Sk_Notice.class);
                intent5.putExtra("my_shop_id",shop_id);
                startActivity(intent5);
                break;
            case R.id.money_to_find:
                LoData();
                break;
        }
    }
}
