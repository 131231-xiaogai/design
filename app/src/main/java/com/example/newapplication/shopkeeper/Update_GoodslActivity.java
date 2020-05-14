package com.example.newapplication.shopkeeper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.newapplication.Adapter.Shopkeeper_goodAdapter;
import com.example.newapplication.R;
import com.example.newapplication.entity.GoodBean;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;

import java.util.HashMap;
import java.util.Map;

public class Update_GoodslActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText l_goodname,gdd_yajin,good_price,good_size,gdd_goood_type,gdd_good_number,
            clothing_length, sleeve_length, shoulder_width, trousers_length;
    private TextView l_goodid;
    private ImageView l_img;
    private ImageButton back;
    private  Button to_save_update,to_deleted;
    private GoodBean data;
    private  String myshop_id,da;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_update);
        l_goodid=findViewById(R.id.gup_goodid);
        l_goodname=findViewById(R.id.gup_goodname);
        l_img=findViewById(R.id.gup_img);
        gdd_yajin=findViewById(R.id.gup_goood_yajin);
        good_price=findViewById(R.id.gup_goood_price);
        gdd_goood_type=findViewById(R.id.gup_goood_type);
        back=findViewById(R.id.gup_back);
        to_save_update =findViewById(R.id.to_save_update);
        gdd_good_number=findViewById(R.id.gup_good_number);
        clothing_length=findViewById(R.id.gup_good_clothing_length);
        sleeve_length=findViewById(R.id.gup_good_sleeve_length);
        shoulder_width=findViewById(R.id.gup_good_shoulder_width);
        trousers_length=findViewById(R.id.gup_good_trousers_length);

        to_deleted = findViewById(R.id.to_deleted);
        to_deleted.setBackground(getResources().getDrawable(R.drawable.button_bg1));
        //
        Intent goodid_integer = getIntent();
        da = goodid_integer.getStringExtra("hgoodid");
        myshop_id =goodid_integer.getStringExtra("myshop_id");
        l_goodid.setText(da);
        Log.d("Goods_DetailActivity",da);
        //

        //
        OnClickListener();
        loadData();
    }

   private void loadData() {
        Map map = new HashMap();
        map.put("goods_id",l_goodid.getText().toString());

       OkHttp.get(this, Constant.select_good_by_id, map, new OkCallback<Result<GoodBean>>() {
            @Override
            public void onResponse(Result<GoodBean> response) {
                data = response.getData();
                if (data != null) {
                    String gname = data.getGoods_name();
                    String pice = data.getGoods_price();
                    String goood_type = data.getType_id();
                    String yajin = data.getGoods_yajin();
                    String good_number = data.getGoods_number();
                    if (good_number == null || good_number.isEmpty()) {
                        gdd_good_number.setText("无");
                    } else {
                        gdd_good_number.setText(good_number);
                    }
                    if (yajin == null || yajin.isEmpty()) {
                        gdd_yajin.setText("无");
                    } else {
                        gdd_yajin.setText(yajin);
                    }
                    if (gname == null || gname.isEmpty()) {
                        l_goodname.setText("无");
                    } else {
                        l_goodname.setText(gname);
                    }
                    if (pice == null || pice.isEmpty()) {
                        good_price.setText("无");
                    } else {
                        good_price.setText(pice);
                    }
                    if (goood_type == null || goood_type.isEmpty()) {
                        gdd_goood_type.setText("无");
                    } else {
                        gdd_goood_type.setText(goood_type);
                    }
                    if (data.getClothing_length()==null || data.getClothing_length().isEmpty()){
                        clothing_length.setText("暂无该参数");
                    }else {clothing_length.setText(data.getClothing_length());
                    }//1
                    if (data.getSleeve_length()==null || data.getSleeve_length().isEmpty()){
                        sleeve_length.setText("暂无该参数");
                    }else {sleeve_length.setText(data.getSleeve_length());
                    }//2
                    if (data.getShoulder_width()==null || data.getShoulder_width().isEmpty()){
                        shoulder_width.setText("暂无该参数");
                    }else {
                        shoulder_width.setText(data.getShoulder_width());
                    }//3
                    if (data.getTrousers_length()==null || data.getTrousers_length().isEmpty()){
                        trousers_length.setText("暂无该参数");
                    }else {
                        trousers_length.setText(data.getTrousers_length());
                    }//4
                    Glide.with(Update_GoodslActivity.this).load(data.getGood_img()).into(l_img);
                }
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(Update_GoodslActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
       });
    }

    private void OnClickListener() {
        back.setOnClickListener(this);
        to_save_update.setOnClickListener(this);
        to_deleted.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gup_back:
                Update_GoodslActivity.this.finish();

                break;
            case R.id.to_deleted:
                Toast.makeText(Update_GoodslActivity.this, "请在“下架商品”页面使用此按钮。", Toast.LENGTH_SHORT).show();
                break;
            case R.id.to_save_update:
                save_update();
                break;
        }
    }

    private void save_update() {
        Map map = new HashMap();

        map.put("goods_id",l_goodid.getText().toString());
        map.put("goods_name",l_goodname.getText().toString());
        map.put("goods_price",good_price.getText().toString());
        map.put("goods_yajin",gdd_yajin.getText().toString());
        map.put("goods_number",gdd_good_number.getText().toString());
        map.put("size",gdd_goood_type.getText().toString());
        map.put("clothing_length",clothing_length.getText().toString());
        map.put("sleeve_length",sleeve_length.getText().toString());
        map.put("shoulder_width",shoulder_width.getText().toString());
        map.put("trousers_length",trousers_length.getText().toString());

        OkHttp.get(this, Constant.update_good_by_goodId, map, new OkCallback<Result<String>>() {
            @Override
            public void onResponse(Result<String> response) {
                Toast.makeText(Update_GoodslActivity.this, "修 改 成 功 ！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("data_return","deleted_goods");
                setResult(RESULT_OK,intent);
                Log.d("deleted_goods","deleted_goods");
                Update_GoodslActivity.this.finish();
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(Update_GoodslActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

}
