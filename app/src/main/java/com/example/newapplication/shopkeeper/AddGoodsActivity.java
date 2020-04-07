package com.example.newapplication.shopkeeper;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    EditText add_name, add_price, add_yajin, add_saize, add_type;
    Button btn_add, btn_addimg;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sk_addgoods);
        add_img = findViewById(R.id.add_img);
        add_name = findViewById(R.id.add_name);
        add_price = findViewById(R.id.add_price);
        add_saize = findViewById(R.id.add_saize);

        add_yajin = findViewById(R.id.add_yajin);
        btn_add = findViewById(R.id.btn_add);
        btn_addimg = findViewById(R.id.btn_addimg);
        OnClickListener();
    }

    private void OnClickListener() {
        add_yajin.setOnClickListener(this);
        add_type.setOnClickListener(this);
        add_saize.setOnClickListener(this);
        add_price.setOnClickListener(this);
        add_name.setOnClickListener(this);
        add_img.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_addimg.setOnClickListener(this);
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
                Map map=new HashMap<>();
                map.put("good_title","aaa");
                OkHttp.upload(this, Constant.publicgoods, map, mSelected, new OkCallback<Result<String>>() {
                    @Override
                    public void onResponse( Result<String> response) {
                        Toast.makeText(AddGoodsActivity.this,"上传成功。",Toast.LENGTH_SHORT).show();
                        add_yajin.getText().clear();
                        add_type.getText().clear();
                        add_saize.getText().clear();
                        add_price.getText().clear();
                        add_name.getText().clear();
                    }
                    @Override
                    public void onFailure(String state, String msg) {
                        Toast.makeText(AddGoodsActivity.this,"上传失败。",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.add_saize:
                break;
            case R.id.btn_addimg:
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
                // choosephoto();
                break;
        }

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
