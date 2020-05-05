package com.example.newapplication.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

public class Update_bodyData  extends AppCompatActivity implements View.OnClickListener {
    private ImageButton b_title_back1,b_notice1;
    private TextView update_save_bodyData;
    private EditText update_weight, update_height,update_Bust, update_The_waist, update_Hipline,
            update_Shoulder_width, update_Clothing_length, update_trousers_length;
    private String user_id,sava;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_udata_bodysize);
        b_title_back1=findViewById(R.id.b_title_back1);
        b_notice1 = findViewById(R.id.b_notice1);

        update_save_bodyData=findViewById(R.id.update_save_bodyData);

        update_weight=findViewById(R.id.update_weight);
        update_height=findViewById(R.id.update_height);
        update_Bust=findViewById(R.id.update_Bust);
        update_The_waist=findViewById(R.id.update_The_waist);
        update_Hipline=findViewById(R.id.update_Hipline);
        update_Shoulder_width=findViewById(R.id.update_Shoulder_width);
        update_Clothing_length=findViewById(R.id.update_Clothing_length);
        update_trousers_length=findViewById(R.id.update_trousers_length);

        OnClickListener();
        LoData();
    }
    private void LoData() {
        user_id = SharePrefrenceUtil.getObject(Update_bodyData.this, UsersBean.class).getUerid();
        Map map = new HashMap();
        map.put("user_id",user_id);
        OkHttp.get(Update_bodyData.this, Constant.select_Bodydata_byuserId, map, new OkCallback<Result<ConsumerBean>>() {
            @Override
            public void onResponse(Result<ConsumerBean> response) {

                update_weight.setText(response.getData().getWeight());
                update_height.setText(response.getData().getHeight());
                update_Bust.setText(response.getData().getBust());
                update_The_waist.setText(response.getData().getThe_waist());
                update_Hipline.setText(response.getData().getHipline());
                update_Shoulder_width.setText(response.getData().getShoulder_width());
                update_Clothing_length.setText(response.getData().getClothing_length());
                update_trousers_length.setText(response.getData().getTrousers_length());

            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(Update_bodyData.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void OnClickListener() {
        b_notice1.setOnClickListener(this);
        b_title_back1.setOnClickListener(this);
        update_save_bodyData.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b_notice1:
                startActivity(new Intent(Update_bodyData.this, Notice.class));
                break;
            case R.id.b_title_back1:
                Update_bodyData.this.finish();
                break;
            case R.id.update_save_bodyData:
                update_data();
                break;
        }

    }



    private void update_data() {
        Map map = new HashMap();
        map.put("user_id",user_id);
        map.put("weight",update_weight.getText().toString());
        map.put("height",update_height.getText().toString());
        map.put("bust",update_Bust.getText().toString());
        map.put("the_waist",update_The_waist.getText().toString());
        map.put("hipline",update_Hipline.getText().toString());
        map.put("shoulder_width",update_Shoulder_width.getText().toString());
        map.put("clothing_length",update_Clothing_length.getText().toString());
        map.put("trousers_length",update_trousers_length.getText().toString());
        OkHttp.get(Update_bodyData.this, Constant.update_user_bodyData, map, new OkCallback<Result<String>>() {
            @Override
            public void onResponse(Result<String> response) {
                Toast.makeText(Update_bodyData.this, "修改个人参数成功。", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("data_return","my_addresses");
                setResult(RESULT_OK,intent);
                finish();

            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(Update_bodyData.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }


}

