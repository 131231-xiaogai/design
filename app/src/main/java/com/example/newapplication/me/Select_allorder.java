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

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.newapplication.Adapter.All_OrderAdapter;
import com.example.newapplication.R;
import com.example.newapplication.entity.OrderBean;
import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.inteface.OnItemClickListener;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.new_utill.SharePrefrenceUtil;
import com.example.newapplication.newpage.Notice;
import com.example.newapplication.newpage.OrderDetailActivity;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Select_allorder extends AppCompatActivity implements View.OnClickListener {

    private TextView title_page;
    private ImageButton title_back,btn_notice;
    private String shop_id,status,my_find_name;
    private All_OrderAdapter sk_orderAdapter;
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


        //************-------------//
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        s_recycle_view.setLayoutManager(layoutManager);
        sk_orderAdapter = new All_OrderAdapter(this);
        s_recycle_view.setAdapter(sk_orderAdapter);
        sk_orderAdapter.setOnItemClickListener(new OnItemClickListener<OrderBean>() {
            @Override
            public void onItemClick(RecyclerViewHolder viewHolder, OrderBean data, int position) {
                String da = data.getGoods_id();
                Intent intent = new Intent(Select_allorder.this, OrderDetailActivity.class);
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
        //-------------------**----------------------------//


        OnClickListener();

    }

    private void loadData() {

        String userID = SharePrefrenceUtil.getObject(Select_allorder.this, UsersBean.class).getUerid();
        Map map = new HashMap();
        map.put("user_id",userID);
        map.put("good_name",sk_input_goodname.getText().toString());

        //select_shopOrder_byMonth
        OkHttp.get(this, Constant.select_order_byUseridAndlikeName, map, new OkCallback<Result<List<OrderBean>>>() {
            @Override
            public void onResponse(Result<List<OrderBean>> response) {
                sk_orderAdapter.setNewData(response.getData());
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(Select_allorder.this, msg, Toast.LENGTH_SHORT).show();
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
                Select_allorder.this.finish();
                break;
            case R.id.sk_select_order_notice:
                startActivity(new Intent(Select_allorder.this, Notice.class));
                break;

        }

    }
}
