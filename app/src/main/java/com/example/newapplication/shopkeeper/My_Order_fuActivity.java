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

import com.example.newapplication.Adapter.Sk_OrderAdapter;
import com.example.newapplication.Adapter.Sk_Order_fuAdapter;
import com.example.newapplication.R;
import com.example.newapplication.entity.OrderBean;
import com.example.newapplication.inteface.OnItemClickListener;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.newpage.Sk_Notice;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class My_Order_fuActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView title_page;
    private ImageButton title_back,btn_notice;
    private String shop_id,status;

    private Sk_Order_fuAdapter sk_orderAdapter;
    private RecyclerView s_recycle_view;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sk_order);
        title_page=findViewById(R.id.title_page);
        title_back=findViewById(R.id.title_back);
        btn_notice=findViewById(R.id.btn_notice);
        s_recycle_view=findViewById(R.id.sk_order_recycleView);
        //
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        s_recycle_view.setLayoutManager(layoutManager);
        sk_orderAdapter = new Sk_Order_fuAdapter(this);
        s_recycle_view.setAdapter(sk_orderAdapter);
        sk_orderAdapter.setOnItemClickListener(new OnItemClickListener<OrderBean>() {
            @Override
            public void onItemClick(RecyclerViewHolder viewHolder, OrderBean data, int position) {
                Toast.makeText(My_Order_fuActivity.this, data.getGoods_id(), Toast.LENGTH_SHORT).show();
//                String da = data.getGoods_id();
//                Intent intent = new Intent(My_OrderActivity.this, ItemDetailActivity.class);
//                intent.putExtra("hgoodid", da);
//                startActivityForResult(intent,1);
            }
        });
        //
        Intent pagename_integer = getIntent();
        String  name = pagename_integer.getStringExtra("page_name");
        shop_id = pagename_integer.getStringExtra("my_shop_id");
        status = pagename_integer.getStringExtra("order_status");
        title_page.setText(name);
        Log.d("WalletpagaActivity",name);

        LoData();
        OnClickListener();

    }

    private void LoData() {
        Map map = new HashMap();
        map.put("shop_id",shop_id);
        map.put("order_status",status);
        Log.d("商家编号为",shop_id);
        Log.d("订单状态为",status);

        OkHttp.get(this, Constant.select_order_by_ShopidAndOrderStstus, map, new OkCallback<Result<List<OrderBean>>>() {
            @Override
            public void onResponse(Result<List<OrderBean>> response) {

              sk_orderAdapter.setNewData(response.getData());


            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(My_Order_fuActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void finish_reback(View v) {
        My_Order_fuActivity.this.finish();
    }


    private void OnClickListener() {
        title_back.setOnClickListener(this);
        btn_notice.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back:
                My_Order_fuActivity.this.finish();
                break;
            case R.id.btn_notice:
                Intent intent5 = new Intent(My_Order_fuActivity.this, Sk_Notice.class);
                intent5.putExtra("my_shop_id",shop_id);
                startActivity(intent5);
                break;
        }

    }
}
