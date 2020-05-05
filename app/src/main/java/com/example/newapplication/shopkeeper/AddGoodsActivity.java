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
    ImageView add_img;
    EditText add_name, add_price, add_yajin, add_saize,add_number;
    Button btn_add, btn_addimg;
    String shop_name,shop_id,good_type,good_actype,good_cltype;
    private ImageButton a_title_back,a_notice;

    Spinner add_type;
    private String[] starAdapter ={"西装","唐装","卡通服","礼服","汉服","首饰","鞋子","其他一","辩论赛",
            "舞蹈类","音乐类","运动类","话剧/小品","其他二"};
    private ArrayAdapter<String> marrayAdapter;

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
        //
        Intent goodid_integer = getIntent();
        shop_name = goodid_integer.getStringExtra("myshop_name");
        shop_id = goodid_integer.getStringExtra("my_shop_id");
        //
        OnClickListener();
        //----------------------//
        Spinner sp = findViewById(R.id.add_type);
        marrayAdapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,starAdapter);
        sp.setAdapter(marrayAdapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AddGoodsActivity.this,"您选择的是"+starAdapter[position],Toast.LENGTH_LONG).show();
                if (starAdapter[position].equals("西装")){ good_type="1"; }
                if (starAdapter[position]=="唐装"){ good_type="2"; }
                if (starAdapter[position]=="卡通服"){ good_type="3"; }
                if (starAdapter[position]=="礼服"){ good_type="4"; }
                if (starAdapter[position]=="汉服"){ good_type="5"; }
                if (starAdapter[position]=="首饰"){ good_type="6"; }
                if (starAdapter[position]=="鞋子"){ good_type="7"; }
                if (starAdapter[position]=="其他一"){ good_type="8"; }
                if (starAdapter[position]=="辩论赛"){ good_type="51"; }
                if (starAdapter[position]=="舞蹈类"){ good_type="52"; }
                if (starAdapter[position]=="音乐类"){ good_type="53"; }
                if (starAdapter[position]=="运动类"){ good_type="54"; }
                if (starAdapter[position]=="话剧/小品"){ good_type="55"; }
                if (starAdapter[position]=="其他二") { good_type = "56"; }
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
                if (good_type.length()>1) {
                    good_cltype="0";
                    good_actype=good_type;
                    Log.d("新添加商品按活动分类编号为",good_actype);
                    Log.d("新添加商品按衣服分类编号为",good_cltype);
                    add_good_activity(good_cltype,good_actype);
                }else {
                    good_cltype=good_type;
                    good_actype="00";
                    Log.d("新添加商品按活动分类编号为",good_actype);
                    Log.d("新添加商品按衣服分类编号为",good_cltype);
                    add_good_clothes(good_cltype,good_actype);
                }

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

    private void add_good_activity(String good_cltype,String good_actype) {
        Map map=new HashMap<>();

        map.put("good_name",add_name.getText().toString());
        map.put("goods_price",add_price.getText().toString());
        map.put("goods_yajin",add_yajin.getText().toString());
        map.put("goods_size_id",add_saize.getText().toString());
        map.put("shop_id",shop_id);
        map.put("shop_name",shop_name);
        map.put("type_id",good_cltype);
        map.put("type_activity_id",good_actype);
        map.put("add_number",add_number.getText().toString());
        OkHttp.upload(this, Constant.publicgoods, map, mSelected, new OkCallback<Result<String>>() {
            @Override
            public void onResponse( Result<String> response) {
                Toast.makeText(AddGoodsActivity.this,"上传成功。",Toast.LENGTH_SHORT).show();
                add_yajin.getText().clear();
                add_saize.getText().clear();
                add_price.getText().clear();
                add_name.getText().clear();
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
