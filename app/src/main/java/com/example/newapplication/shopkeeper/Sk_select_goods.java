package com.example.newapplication.shopkeeper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.newapplication.Adapter.Shopkeeper_goodAdapter;
import com.example.newapplication.R;
import com.example.newapplication.entity.GoodBean;
import com.example.newapplication.inteface.OnItemClickListener;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.newpage.Notice;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sk_select_goods extends AppCompatActivity implements View.OnClickListener  {

    private ImageButton title_back,notic;
    private RecyclerView s_recycle_view;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Shopkeeper_goodAdapter shopkeeper_goodAdapter;
    private String shop_id;
    private EditText input_yue;
    String  myshop_id,my_pagenumber;
    private ImageView sk_select_good_find;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sk_select_goods);

        title_back=findViewById(R.id.sk_select_good_back);
        notic=findViewById(R.id.sk_select_good_notice);
        input_yue=findViewById(R.id.input_goodname);
        s_recycle_view=findViewById(R.id.sk_select_good_recycle_view);
        sk_select_good_find=findViewById(R.id.sk_select_good_find);

        //接收数据
        Intent pagename_integer = getIntent();
        shop_id = pagename_integer.getStringExtra("my_shop_id");
        my_pagenumber= pagename_integer.getStringExtra("my_pagenumber");
        Log.d("商店编号",shop_id);
        //--********--//
        //添加适配器
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        s_recycle_view.setLayoutManager(layoutManager);
        shopkeeper_goodAdapter = new Shopkeeper_goodAdapter(this);
        s_recycle_view.setAdapter(shopkeeper_goodAdapter);

        shopkeeper_goodAdapter.setOnItemClickListener(new OnItemClickListener<GoodBean>() {
            @Override
            public void onItemClick(RecyclerViewHolder viewHolder, GoodBean data, int position) {
                Toast.makeText(Sk_select_goods.this, data.getGoods_id(), Toast.LENGTH_SHORT).show();
                String da = data.getGoods_id();
                Intent intent = new Intent(Sk_select_goods.this, Goods_DetailActivity.class);
                intent.putExtra("hgoodid", da);
                intent.putExtra("myshop_id", shop_id);
                intent.putExtra("my_pagenumber", my_pagenumber);
                startActivityForResult(intent,1);
            }
        });
        //----************----//



        OnClickListener();

    }

    private void loadData() {

        Map map = new HashMap();
        map.put("shop_id",shop_id);
        map.put("goods_name",input_yue.getText().toString());

        //select_shopOrder_byMonth
        OkHttp.get(this, Constant.select_good_bylikeName, map, new OkCallback<Result<List<GoodBean>>>() {
            @Override
            public void onResponse(Result<List<GoodBean>> response) {
                shopkeeper_goodAdapter.setNewData(response.getData());
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(Sk_select_goods.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void OnClickListener() {
        title_back.setOnClickListener(this);
        notic.setOnClickListener(this);
        sk_select_good_find.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sk_select_good_find:
                loadData();
                break;
            case R.id.sk_select_good_back:
                Sk_select_goods.this.finish();
                break;
            case R.id.sk_select_good_notice:
                startActivity(new Intent(Sk_select_goods.this, Notice.class));
                break;

        }
    }
}
