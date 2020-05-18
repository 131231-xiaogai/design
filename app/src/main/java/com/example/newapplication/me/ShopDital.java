package com.example.newapplication.me;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.newapplication.R;
import com.example.newapplication.ShopkeeperActivity;
import com.example.newapplication.entity.EvaluateBean;
import com.example.newapplication.entity.GoodBean;
import com.example.newapplication.entity.ShopBean;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.newpage.Notice;
import com.example.newapplication.newpage.Sk_Notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopDital extends AppCompatActivity implements View.OnClickListener {
    private String myshop_id,pagename,myshop_name,shop_id;
    private String pagenumber,isshop;
    private TextView my_shop_name,my_shop_address,me_shop_register_time,me_shop_phone,my_shop_sorc,sk_huan;
    private TextView k_add,k_delete,k_change,me_shop_name,me_shop_blance,s_totalprice,sk_all_order,sk_user_setup,k_select;
    private TextView more_maney;
    private ImageButton btn_notice,title_back;
    private TextView fu,fa,shou;
    private SwipeRefreshLayout swipeRefreshLayout;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopdetail);
        Intent pagename_integer = getIntent();
        shop_id = pagename_integer.getStringExtra("shop_id");
        Log.d("商店编号",shop_id);
        btn_notice=findViewById(R.id.btn_notice);
        btn_notice.setOnClickListener(this);
        title_back=findViewById(R.id.title_back);
        title_back.setOnClickListener(this);

        my_shop_name=findViewById(R.id.me_find_shopname);
        my_shop_address=findViewById(R.id.find_shop_address);
        me_shop_register_time=findViewById(R.id.find_shop_register_time);
        me_shop_phone=findViewById(R.id.find_shop_phone);
        my_shop_sorc=findViewById(R.id.find_shop_sorc);// 评分
        me_shop_name=findViewById(R.id.find_shop_username);
        LoData();
    }

    private void LoData() {
        Map map = new HashMap();
        map.put("shop_id",shop_id);
        OkHttp.get(this, Constant.select_user_byShopId, map, new OkCallback<Result<ShopBean>>() {
            @Override
            public void onResponse(Result<ShopBean> response) {
                if (response.getData() != null) {
                    my_shop_name.setText(response.getData().getShop_name());
                    my_shop_address.setText(response.getData().getShop_dresss());
                    me_shop_register_time.setText(response.getData().getShop_regist_time());
                    me_shop_phone.setText(response.getData().getShop_phone());
                   // my_shop_sorc.setText(response.getData().getShop_dresss());
                    me_shop_name.setText(response.getData().getUser_id());

                }
                shop_evaluate();
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(ShopDital.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back:
                ShopDital.this.finish();
                break;
            case R.id.btn_notice:
                Intent intent5 = new Intent(ShopDital.this, Notice.class);
                startActivity(intent5);
                break;
        }

    }

    private void shop_evaluate() {
        Map map = new HashMap();
        map.put("shop_id",shop_id);
        OkHttp.get(this, Constant.selece_shop_evaluate, map, new OkCallback<Result<List<EvaluateBean>>>() {
            @Override
            public void onResponse(Result<List<EvaluateBean>> response) {
                double evaluate = 0;
                for (int i = 0; i < response.getData().size(); i++) {
                    evaluate = evaluate + Double.valueOf(response.getData().get(i).getP_content());
                }
                evaluate = evaluate / response.getData().size();
                my_shop_sorc.setText(evaluate + "分");


            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(ShopDital.this, msg, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
