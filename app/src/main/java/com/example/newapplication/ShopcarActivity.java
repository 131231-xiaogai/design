package com.example.newapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newapplication.Adapter.BaseRecyclerViewAdapter;
import com.example.newapplication.Adapter.ShopcarNewAdapter;
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
import com.example.newapplication.shopcar.SettlementActivity;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.view.View.GONE;

public class ShopcarActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton btn_list, btn_date, btn_home, btn_me;
    ImageView btn_notice;
    private ImageView ivAllSelect, iv_all_select2;
    private TextView tvTotalPrice, sc_tv_del, sc_tv_ok;
    private Button btnPay;
    private boolean isAllSelect;
    private LinearLayout l1_pay, l1del, l_del, l_ok;

    private ShopcarNewAdapter shopcarAdapter;
    RecyclerView s_recycle_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopcar);

        sc_tv_ok = findViewById(R.id.sc_tv_ok);
        l_del = findViewById(R.id.l_de);
        l_ok = findViewById(R.id.l_ok);
        l1del = findViewById(R.id.l1_del);
        l1_pay = findViewById(R.id.l1_pay);
        sc_tv_del = findViewById(R.id.sc_tv_del);
        ivAllSelect = (ImageView) findViewById(R.id.iv_all_select);
        tvTotalPrice = (TextView) findViewById(R.id.tv_total_price);
        btnPay = (Button) findViewById(R.id.btn_pay);
        iv_all_select2 = findViewById(R.id.iv_all_select2);


        iv_all_select2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isAllSelect = !isAllSelect;
                if (isAllSelect) {
                    iv_all_select2.setImageResource(R.mipmap.select);
                    for (Shooping_carBean allDatum : shopcarAdapter.getAllData()) {
                        allDatum.setSelect(true);
                    }
                    shopcarAdapter.notifyDataSetChanged();
                    //double totalPrice = shopcarAdapter.totalPrice();
                    //tvTotalPrice.setText("￥" + totalPrice);
                } else {
                    iv_all_select2.setImageResource(R.mipmap.unselect);
                    for (Shooping_carBean allDatum : shopcarAdapter.getAllData()) {
                        allDatum.setSelect(false);
                    }
                    shopcarAdapter.notifyDataSetChanged();
                    //tvTotalPrice.setText("￥0.00");
                }

            }
        });


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
        sc_tv_del.setOnClickListener(this);
        sc_tv_ok.setOnClickListener(this);
        btnPay.setOnClickListener(this);

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
            case 2:
                if (resultCode == RESULT_OK) {
                    String data_return = data.getStringExtra("data_return");
                    Log.d("从结算页面返回", data_return);
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
        map.put("shop_car_status","0");//0未形成订单，1为已形成订单
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
            case R.id.sc_tv_ok:
                l1_pay.setVisibility(View.VISIBLE);
                l1del.setVisibility(GONE);
                l_del.setVisibility(View.VISIBLE);
                l_ok.setVisibility(GONE);
                break;
            case R.id.sc_tv_del:
                l1_pay.setVisibility(GONE);
                l1del.setVisibility(View.VISIBLE);
                l_del.setVisibility(GONE);
                l_ok.setVisibility(View.VISIBLE);
                break;
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
            case R.id.btn_pay:
                Intent intent = new Intent(ShopcarActivity.this, SettlementActivity.class);
                List<Shooping_carBean> shopCards = getShopCards();
                intent.putExtra("shopcards", (Serializable) shopCards);
                startActivityForResult(intent,2);
                break;
        }
    }


    private List<Shooping_carBean> getShopCards() {
        List<Shooping_carBean> shooping_carBeans = new ArrayList<>();
        for (Shooping_carBean allDatum : shopcarAdapter.getAllData()) {
            if (allDatum.isSelect()) {
                shooping_carBeans.add(allDatum);
            }
        }
        return shooping_carBeans;
    }


}
