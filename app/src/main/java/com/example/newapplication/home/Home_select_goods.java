package com.example.newapplication.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.newapplication.Adapter.GoodAdapter;
import com.example.newapplication.Adapter.ShopAdapter;
import com.example.newapplication.Adapter.Shopkeeper_goodAdapter;
import com.example.newapplication.R;
import com.example.newapplication.entity.GoodBean;
import com.example.newapplication.entity.ShopBean;
import com.example.newapplication.inteface.OnItemClickListener;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.newpage.Notice;
import com.example.newapplication.shopkeeper.Goods_DetailActivity;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Home_select_goods extends AppCompatActivity implements View.OnClickListener  {

    private ImageButton title_back,notic;
    private RecyclerView s_recycle_view,sk_select_shop_recycle_view;
    private SwipeRefreshLayout swipeRefreshLayout;
    private GoodAdapter shopkeeper_goodAdapter;
    private ShopAdapter shop_Adapter;
    private String shop_id;
    private EditText input_yue,input_shopaddress, input_shopname, input_shopid;//输入框
    private String  myshop_id,my_pagenumber;
    private ImageView sk_select_good_find,sk_select_shopaddress, sk_select_shopname, sk_select_shopid;//搜索图标
    private TextView home_good_total,home_shopaddress, home_shopname, home_shopid;//搜索后的统计
    private LinearLayout ll_et_goodname, ll_et_shopaddress, ll_et_shopname, ll_et_shopid;
    private TextView select_good_bygoodname, select_shop_byaddress, select_shop_byshopname, select_shop_byshopid;//选择搜索方式

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hpme_select_goods);

        ll_et_goodname=findViewById(R.id.ll_et_goodname);
        ll_et_shopaddress=findViewById(R.id.ll_et_shopaddress);
        ll_et_shopname=findViewById(R.id.ll_et_shopname);
        ll_et_shopid=findViewById(R.id.ll_et_shopid);//


        input_yue=findViewById(R.id.input_goodname1);//输入框
        input_shopaddress=findViewById(R.id.input_shopaddress);
        input_shopname=findViewById(R.id.input_shopname);
        input_shopid=findViewById(R.id.input_shopid);

        sk_select_good_find=findViewById(R.id.sk_select_good_find1);//搜索图标
        sk_select_shopaddress =findViewById(R.id.sk_select_shopaddress);
        sk_select_shopname =findViewById(R.id.sk_select_shopname);
        sk_select_shopid =findViewById(R.id.sk_select_shopid);

        home_good_total=findViewById(R.id.home_good_total1);//搜索后的统计
        home_shopaddress =findViewById(R.id.home_shopaddress);
        home_shopname =findViewById(R.id.home_shopname);
        home_shopid =findViewById(R.id.home_shopid);

        //选择搜索方式
        select_good_bygoodname =findViewById(R.id.select_good_bygoodname);
        select_shop_byaddress =findViewById(R.id.select_shop_byaddress);
        select_shop_byshopname =findViewById(R.id.select_shop_byshopname);
        select_shop_byshopid =findViewById(R.id.select_shop_byshopid);

        s_recycle_view=findViewById(R.id.sk_select_good_recycle_view1);
        sk_select_shop_recycle_view=findViewById(R.id.sk_select_shop_recycle_view);//瀑布列表
        title_back=findViewById(R.id.sk_select_good_back1);//返回按钮
        notic=findViewById(R.id.sk_select_good_notice1);//通知

        //添加适配器
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        s_recycle_view.setLayoutManager(layoutManager);
        shopkeeper_goodAdapter = new GoodAdapter(this);
        s_recycle_view.setAdapter(shopkeeper_goodAdapter);
        //在店铺地址适配器
        StaggeredGridLayoutManager layoutManager_shopaddress = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        sk_select_shop_recycle_view.setLayoutManager(layoutManager_shopaddress);
        shop_Adapter = new ShopAdapter(this);
        sk_select_shop_recycle_view.setAdapter(shop_Adapter);


        //----************----//



        OnClickListener();

    }

    private void loadData() {
        Map map = new HashMap();
        map.put("goods_name",input_yue.getText().toString());
        OkHttp.get(this, Constant.select_all_good_likeName, map, new OkCallback<Result<List<GoodBean>>>() {
            @Override
            public void onResponse(Result<List<GoodBean>> response) {
                shopkeeper_goodAdapter.setNewData(response.getData());
                home_good_total.setText("查询到"+response.getData().size()+"件商品");
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(Home_select_goods.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        shopkeeper_goodAdapter.setOnItemClickListener(new OnItemClickListener<GoodBean>() {
            @Override
            public void onItemClick(RecyclerViewHolder viewHolder, GoodBean data, int position) {
                Toast.makeText(Home_select_goods.this, data.getGoods_id(), Toast.LENGTH_SHORT).show();
                String da = data.getGoods_id();
                Intent intent = new Intent(Home_select_goods.this, ItemDetailActivity.class);
                intent.putExtra("hgoodid", da);
                startActivityForResult(intent,1);
            }
        });
    }

    private void OnClickListener() {
        title_back.setOnClickListener(this);
        notic.setOnClickListener(this);
        sk_select_good_find.setOnClickListener(this);
        sk_select_shopaddress.setOnClickListener(this);

        select_good_bygoodname.setOnClickListener(this);
        select_shop_byaddress.setOnClickListener(this);
        select_shop_byshopname.setOnClickListener(this);
        select_shop_byshopid.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sk_select_good_find1:
                loadData();
                break;
            case R.id.sk_select_good_back1:
                Home_select_goods.this.finish();
                break;
            case R.id.sk_select_good_notice1:
                startActivity(new Intent(Home_select_goods.this, Notice.class));
                break;
            case R.id.select_good_bygoodname:
                select_good_bygoodname.setBackground(getResources().getDrawable(R.color.teal));
                select_good_bygoodname.setTextColor(this.getResources().getColor(R.color.white));
                select_shop_byaddress.setBackground(getResources().getDrawable(R.color.white));
                select_shop_byaddress.setTextColor(this.getResources().getColor(R.color.teal));
                ll_et_goodname.setVisibility(View.VISIBLE);
                ll_et_shopaddress.setVisibility(View.GONE);
                s_recycle_view.setVisibility(View.VISIBLE);
                sk_select_shop_recycle_view.setVisibility(View.GONE);
                break;
            case R.id.select_shop_byaddress:
                select_good_bygoodname.setBackground(getResources().getDrawable(R.color.white));
                select_good_bygoodname.setTextColor(this.getResources().getColor(R.color.teal));
                select_shop_byaddress.setBackground(getResources().getDrawable(R.color.teal));
                select_shop_byaddress.setTextColor(this.getResources().getColor(R.color.white));
                ll_et_goodname.setVisibility(View.GONE);
                ll_et_shopaddress.setVisibility(View.VISIBLE);
                s_recycle_view.setVisibility(View.GONE);
                sk_select_shop_recycle_view.setVisibility(View.VISIBLE);

                break;
            case R.id.sk_select_shopaddress:
                shop_byaddress();
                break;
            case R.id.select_shop_byshopname:

                break;
            case R.id.select_shop_byshopid:

                break;

        }
    }

    private void shop_byaddress() {
        Map map = new HashMap();
        map.put("shop_dresss",input_shopaddress.getText().toString());
        OkHttp.get(this, Constant.select_shop_likeaddress, map, new OkCallback<Result<List<ShopBean>>>() {
            @Override
            public void onResponse(Result<List<ShopBean>> response) {
                shop_Adapter.setNewData(response.getData());
                home_shopaddress.setText("查询到"+response.getData().size()+"家商店");
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(Home_select_goods.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        shop_Adapter.setOnItemClickListener(new OnItemClickListener<ShopBean>() {
            @Override
            public void onItemClick(RecyclerViewHolder viewHolder, ShopBean data, int position) {
                Intent intent1 = new Intent(Home_select_goods.this, Shop.class);
                intent1.putExtra("shop_id",data.getShop_id());
                intent1.putExtra("shopname",data.getShop_name());
                startActivity(intent1);
            }
        });

    }
}
