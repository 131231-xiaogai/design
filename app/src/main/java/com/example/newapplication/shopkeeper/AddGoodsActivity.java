package com.example.newapplication.shopkeeper;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.newapplication.BuildConfig;
import com.example.newapplication.R;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.MyGlideEngine;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.newpage.Sk_Notice;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddGoodsActivity extends AppCompatActivity implements View.OnClickListener {
    private List<String> mSelected = new ArrayList<>();
    public static final int CHOOSE_PHOTO = 2;
    public static final int REQUEST_CODE_CHOOSE = 3;//跳转去选择图片时的code;
    private  ImageView add_img;
    private  EditText add_name, add_price, add_yajin, add_saize,add_number,
            Clothing_length, Sleeve_length, Shoulder_width, trousers_length;
    private  Button btn_add, btn_addimg;
    private  String shop_name,shop_id,good_type,good_actype;
    private ImageButton a_title_back,a_notice;

    private Spinner sp_cloth,add_type_activity;
    private String[] starAdapter_cloth ={"无","西装","唐装","卡通服","礼服","汉服","首饰","鞋子","其他"};
    private String[] starAdapter_activity ={"无","辩论赛","舞蹈类","音乐类","运动类","话剧/小品","其他"};
    private ArrayAdapter<String> marrayAdapter_colth;//marrayAdapter_colth
    private ArrayAdapter<String> marrayAdapter_activity;//marrayAdapter_colth

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sk_addgoods);
        add_img = findViewById(R.id.add_img);
        add_name = findViewById(R.id.add_name);
        add_price = findViewById(R.id.add_price);
        add_saize = findViewById(R.id.add_saize);
        add_yajin = findViewById(R.id.add_yajin);
        add_number = findViewById(R.id.add_number);
        btn_add = findViewById(R.id.btn_add);
        btn_addimg = findViewById(R.id.btn_addimg);
        a_title_back =findViewById(R.id.a_title_back);
        a_notice=findViewById(R.id.a_notice);
        a_notice.setOnClickListener(this);

        Clothing_length=findViewById(R.id.add_Clothing_length);
        Sleeve_length=findViewById(R.id.add_Sleeve_length);
        Shoulder_width=findViewById(R.id.add_Shoulder_width);
        trousers_length=findViewById(R.id.add_trousers_length);

        //
        Intent goodid_integer = getIntent();
        shop_name = goodid_integer.getStringExtra("myshop_name");
        shop_id = goodid_integer.getStringExtra("my_shop_id");
        Log.d("my_shop_id",shop_id);
        //
        OnClickListener();
        //----------------------//add_type_activity
         sp_cloth = findViewById(R.id.add_type);
         add_type_activity = findViewById(R.id.add_type_activity);
        marrayAdapter_colth = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,starAdapter_cloth);
        sp_cloth.setAdapter(marrayAdapter_colth);
        sp_cloth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AddGoodsActivity.this,"您选择的是"+starAdapter_cloth[position],Toast.LENGTH_LONG).show();
                if (starAdapter_cloth[position].equals("无")){ good_type="500"; }
                if (starAdapter_cloth[position].equals("西装")){ good_type="1"; }
                if (starAdapter_cloth[position]=="唐装"){ good_type="2"; }
                if (starAdapter_cloth[position]=="卡通服"){ good_type="3"; }
                if (starAdapter_cloth[position]=="礼服"){ good_type="4"; }
                if (starAdapter_cloth[position]=="汉服"){ good_type="5"; }
                if (starAdapter_cloth[position]=="首饰"){ good_type="6"; }
                if (starAdapter_cloth[position]=="鞋子"){ good_type="7"; }
                if (starAdapter_cloth[position]=="其他"){ good_type="8"; }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        marrayAdapter_activity = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,starAdapter_activity);
        add_type_activity.setAdapter(marrayAdapter_activity);
        add_type_activity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AddGoodsActivity.this,"您选择的是"+starAdapter_activity[position],Toast.LENGTH_LONG).show();
                if (starAdapter_activity[position].equals("无")){ good_actype="600"; }
                if (starAdapter_activity[position]=="辩论赛"){ good_actype="51"; }
                if (starAdapter_activity[position]=="舞蹈类"){ good_actype="52"; }
                if (starAdapter_activity[position]=="音乐类"){ good_actype="53"; }
                if (starAdapter_activity[position]=="运动类"){ good_actype="54"; }
                if (starAdapter_activity[position]=="话剧/小品"){ good_actype="55"; }
                if (starAdapter_activity[position]=="其他") { good_actype = "56"; }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void OnClickListener() {
        add_yajin.setOnClickListener(this);
        //add_type.setOnClickListener(this);
        add_saize.setOnClickListener(this);
        add_price.setOnClickListener(this);
        add_name.setOnClickListener(this);
        add_img.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_addimg.setOnClickListener(this);
        a_title_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_img:
                break;
            case R.id.add_name:
                break;
            case R.id.add_price:
                break;
            case R.id.add_yajin:
                break;
            case R.id.btn_add:
                add_good_type();
                break;
            case R.id.add_saize:
                break;
            case R.id.btn_addimg:
                choosephoto();
                break;
            case R.id.a_title_back:
                AddGoodsActivity.this.finish();
                break;
            case R.id.a_notice:
                Intent intent5 = new Intent(AddGoodsActivity.this, Sk_Notice.class);
                intent5.putExtra("my_shop_id",shop_id);
                startActivity(intent5);
                break;
        }
    }
    private void choosephoto() {
        Matisse.from(this)
                .choose(MimeType.ofAll())//资源的类型，比如现在这个设置是照片视频全部显示
                .countable(true)//显示选择图片的数量
                .capture(true)//使用拍照
                .maxSelectable(1)//最多选择几张图片
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)// 图像选择和预览活动所需的方向。
                .captureStrategy(new CaptureStrategy(true, BuildConfig.APPLICATION_ID + ".file_provider"))
                .thumbnailScale(0.85f)//缩放比例
                .imageEngine(new MyGlideEngine())//图片加载类，需要重写框架自带的不然会报错
                .forResult(REQUEST_CODE_CHOOSE);//请求码
    }

    private void add_good_type() {
        Map map=new HashMap<>();
        map.put("good_name",add_name.getText().toString());
        map.put("goods_price",add_price.getText().toString());
        map.put("goods_yajin",add_yajin.getText().toString());
        map.put("goods_size_id",add_saize.getText().toString());
        map.put("shop_id",shop_id);
        map.put("shop_name",shop_name);
        map.put("type_id",good_type);
        map.put("type_activity_id",good_actype);
        map.put("add_number",add_number.getText().toString());
        map.put("clothing_length",Clothing_length.getText().toString());
        map.put("sleeve_length",Sleeve_length.getText().toString());
        map.put("shoulder_width",Shoulder_width.getText().toString());
        map.put("trousers_length",trousers_length.getText().toString());
        Log.d("袖子长度", Sleeve_length.getText().toString());

        OkHttp.upload(this, Constant.publicgoods, map, mSelected, new OkCallback<Result<String>>() {
            @Override
            public void onResponse( Result<String> response) {
                Toast.makeText(AddGoodsActivity.this,"上传成功。",Toast.LENGTH_SHORT).show();
                add_yajin.getText().clear();
                add_saize.getText().clear();
                add_price.getText().clear();
                add_name.getText().clear();
                Clothing_length.getText().clear();
                Sleeve_length.getText().clear();
                Shoulder_width.getText().clear();
                trousers_length.getText().clear();
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(AddGoodsActivity.this,"上传失败。",Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void add_good_clothes(String good_cltype,String good_actype) {
        Map map=new HashMap<>();
        map.put("good_name",add_name.getText().toString());
        map.put("goods_price",add_price.getText().toString());
        map.put("goods_yajin",add_yajin.getText().toString());
        map.put("goods_size_id",add_saize.getText().toString());
        map.put("shop_id",shop_id);
        map.put("shop_name",shop_name);
        map.put("type_id",good_cltype);
        map.put("type_activity_id",good_actype);

        map.put("Clothing_length",Clothing_length.getText().toString());
        map.put("Sleeve_length",Sleeve_length.getText().toString());
        map.put("Shoulder_width",Shoulder_width.getText().toString());
        map.put("trousers_length",trousers_length.getText().toString());

        OkHttp.upload(this, Constant.publicgoods, map, mSelected, new OkCallback<Result<String>>() {
            @Override
            public void onResponse( Result<String> response) {
                Toast.makeText(AddGoodsActivity.this,"上传成功。",Toast.LENGTH_SHORT).show();
                add_yajin.getText().clear();
                add_saize.getText().clear();
                add_price.getText().clear();
                add_name.getText().clear();
                add_number.getText().clear();

            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(AddGoodsActivity.this,"上传失败。",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainPathResult(data);
            Glide.with(this).load(mSelected.get(0)).into(add_img);
        }
    }

}
