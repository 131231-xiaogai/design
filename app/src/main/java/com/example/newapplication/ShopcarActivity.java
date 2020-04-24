package com.example.newapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newapplication.Adapter.BaseRecyclerViewAdapter;
import com.example.newapplication.Adapter.PhotoAdapter;
import com.example.newapplication.Adapter.ShopcarAdapter;
import com.example.newapplication.Adapter.ShopcarNewAdapter;
import com.example.newapplication.entity.Photo;
import com.example.newapplication.entity.Shooping_carBean;
import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.home.ItemDetailActivity;
import com.example.newapplication.inteface.OnItemChildClickListener;
import com.example.newapplication.inteface.OnItemClickListener;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.new_utill.SharePrefrenceUtil;
import com.example.newapplication.newpage.Notice;
import com.example.newapplication.other.ShopCartAdapter;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopcarActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton btn_list, btn_date, btn_home, btn_me;
    ImageView btn_notice;
    private ImageView ivAllSelect;
    private TextView tvTotalPrice;
    private Button btnPay;
    private boolean isAllSelect;

    private ShopcarNewAdapter shopcarAdapter;
    RecyclerView s_recycle_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopcar);


        ivAllSelect = (ImageView) findViewById(R.id.iv_all_select);
        tvTotalPrice = (TextView) findViewById(R.id.tv_total_price);
        btnPay = (Button) findViewById(R.id.btn_pay);

        ivAllSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAllSelect = !isAllSelect;
                if (isAllSelect) {
                    ivAllSelect.setImageResource(R.mipmap.select);
                    for (Shooping_carBean allDatum : shopcarAdapter.getAllData()) {
                        allDatum.setSelect(true);
                    }
                    shopcarAdapter.notifyDataSetChanged();
                    double totalPrice = shopcarAdapter.totalPrice();
                    tvTotalPrice.setText("￥" + totalPrice);
                } else {
                    ivAllSelect.setImageResource(R.mipmap.unselect);
                    for (Shooping_carBean allDatum : shopcarAdapter.getAllData()) {
                        allDatum.setSelect(false);
                    }
                    shopcarAdapter.notifyDataSetChanged();
                    tvTotalPrice.setText("￥0.00");
                }


            }
        });

        btn_home = (ImageButton) findViewById(R.id.b_home);
        btn_list = (ImageButton) findViewById(R.id.b_list);
        btn_date = (ImageButton) findViewById(R.id.b_date);
        btn_me = (ImageButton) findViewById(R.id.b_me);
        btn_notice = findViewById(R.id.btn_notice);
        btn_notice.setOnClickListener(this);
        btn_home.setOnClickListener(this);
        btn_list.setOnClickListener(this);
        btn_date.setOnClickListener(this);
        btn_me.setOnClickListener(this);
        s_recycle_view = findViewById(R.id.s_recycle_view);

        s_recycle_view.setLayoutManager(new LinearLayoutManager(this));
        shopcarAdapter = new ShopcarNewAdapter(this);
        s_recycle_view.setAdapter(shopcarAdapter);
        shopcarAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseRecyclerViewAdapter adapter, View view, int position) {
                Shooping_carBean shopcarAdapterItem = shopcarAdapter.getItem(position);
                if (view.getId() == R.id.iv_is_select) {
                    if (shopcarAdapterItem.isSelect()) {
                        shopcarAdapterItem.setSelect(false);
                    } else {
                        shopcarAdapterItem.setSelect(true);
                    }
                    shopcarAdapter.notifyDataSetChanged();
                    double totalPrice = shopcarAdapter.totalPrice();
                    tvTotalPrice.setText(totalPrice + "");

                }
            }
        });
        shopcarAdapter.setOnItemClickListener(new OnItemClickListener<Shooping_carBean>() {
            @Override
            public void onItemClick(RecyclerViewHolder viewHolder, Shooping_carBean data, int position) {
                Toast.makeText(ShopcarActivity.this, data.getGoods_id(), Toast.LENGTH_SHORT).show();
                String da = data.getGoods_id();
                Intent intent = new Intent(ShopcarActivity.this, ItemDetailActivity.class);
                intent.putExtra("hgoodid", da);
                startActivityForResult(intent, 1);
            }
        });
        loadData();
        OnClickListener();
    }

    private void OnClickListener() {
        btn_notice.setOnClickListener(this);
        btn_home.setOnClickListener(this);
        btn_list.setOnClickListener(this);
        btn_date.setOnClickListener(this);
        btn_me.setOnClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String data_return = data.getStringExtra("data_return");
                    Log.d("my_address", data_return);
                    loadData();
                }
                break;
            default:
        }
    }

    private void loadData() {
        String user_id = SharePrefrenceUtil.getObject(ShopcarActivity.this, UsersBean.class).getUerid();

        Map map = new HashMap();
        map.put("user_id", user_id);

        OkHttp.get(this, Constant.select_shopcar_by_userid, map, new OkCallback<Result<List<Shooping_carBean>>>() {
            @Override
            public void onResponse(Result<List<Shooping_carBean>> response) {
                shopcarAdapter.setNewData(response.getData());
            }

            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(ShopcarActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void finish_reback(View v) {
        ShopcarActivity.this.finish();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b_home:
                startActivity(new Intent(ShopcarActivity.this, HomeActivity.class));
                break;
            case R.id.b_list:
                startActivity(new Intent(ShopcarActivity.this, ListActivity.class));
                break;
            case R.id.b_date:
                startActivity(new Intent(ShopcarActivity.this, DateActivity.class));
                break;
            case R.id.b_me:
                startActivity(new Intent(ShopcarActivity.this, MeActivity.class));
                break;
            case R.id.btn_notice:
                startActivity(new Intent(ShopcarActivity.this, Notice.class));
                break;
        }
    }

}
