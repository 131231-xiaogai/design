package com.example.newapplication.me;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.R;
import com.example.newapplication.entity.ConsumerBean;
import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.new_utill.SharePrefrenceUtil;
import com.example.newapplication.newpage.Notice;

import java.util.HashMap;
import java.util.Map;

public class MyBodySizeActivility extends AppCompatActivity implements View.OnClickListener {

    private  ImageButton b_title_back,b_notice;
    private TextView save_bodyData;
    private TextView weight, height,Bust, The_waist, Hipline, Shoulder_width, Clothing_length, trousers_length;
    private String user_id;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_bodysize);
        b_title_back=findViewById(R.id.b_title_back);
        b_notice = findViewById(R.id.b_notice);
        save_bodyData=findViewById(R.id.save_bodyData);

        weight=findViewById(R.id.weight);
        height=findViewById(R.id.height);
        Bust=findViewById(R.id.Bust);
        The_waist=findViewById(R.id.The_waist);
        Hipline=findViewById(R.id.Hipline);
        Shoulder_width=findViewById(R.id.Shoulder_width);
        Clothing_length=findViewById(R.id.Clothing_length);
        trousers_length=findViewById(R.id.trousers_length);

        OnClickListener();
        LoData();
    }
    private void LoData() {
        user_id = SharePrefrenceUtil.getObject(MyBodySizeActivility.this, UsersBean.class).getUerid();
        Map map = new HashMap();
        map.put("user_id",user_id);
        OkHttp.get(MyBodySizeActivility.this, Constant.select_Bodydata_byuserId, map, new OkCallback<Result<ConsumerBean>>() {
            @Override
            public void onResponse(Result<ConsumerBean> response) {
                    weight.setText(response.getData().getWeight());
                    height.setText(response.getData().getHeight());
                    Bust.setText(response.getData().getBust());
                    The_waist.setText(response.getData().getThe_waist());
                    Hipline.setText(response.getData().getHipline());
                    Shoulder_width.setText(response.getData().getShoulder_width());
                    Clothing_length.setText(response.getData().getClothing_length());
                    trousers_length.setText(response.getData().getTrousers_length());
                }

            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(MyBodySizeActivility.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String data_return = data.getStringExtra("data_return");
                    Log.d("my_address",data_return);
                    LoData();
                }
                break;
            default:
        }
    }

    private void OnClickListener() {
        b_notice.setOnClickListener(this);
        b_title_back.setOnClickListener(this);
        save_bodyData.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b_notice:
                startActivity(new Intent(MyBodySizeActivility.this, Notice.class));
                break;
            case R.id.b_title_back:
                MyBodySizeActivility.this.finish();
                break;
            case R.id.save_bodyData:
                Intent intent = new Intent(MyBodySizeActivility.this, Update_bodyData.class);
                startActivityForResult(intent,1);
                break;
        }
    }
}
