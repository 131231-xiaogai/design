package com.example.newapplication.date;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.MeActivity;
import com.example.newapplication.R;
import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.new_utill.SharePrefrenceUtil;

import java.util.HashMap;
import java.util.Map;

public class Add_eventActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton eva_back,eva_save;
    EditText eva_title,eva_contant,eva_strstTime,eva_endtime;
    TextView eva_date;
    String  date;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);

        eva_back=findViewById(R.id.eva_back);
        eva_endtime=findViewById(R.id.eva_endtime);
        eva_strstTime=findViewById(R.id.eva_strstTime);
        eva_title=findViewById(R.id.eva_title);
        eva_contant=findViewById(R.id.eva_contant);
        eva_save=findViewById(R.id.eva_save);
        eva_date=findViewById(R.id.eva_date);
        //
        Intent integer = getIntent();
        date = integer.getStringExtra("date");
        eva_date.setText(date);

        //
        OnClickListener();

    }

    private void OnClickListener() {
        eva_back.setOnClickListener(this);
        eva_endtime.setOnClickListener(this);
        eva_strstTime.setOnClickListener(this);
        eva_title.setOnClickListener(this);
        eva_contant.setOnClickListener(this);
        eva_date.setOnClickListener(this);
        eva_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.eva_back:
                Add_eventActivity.this.finish();
                break;
            case R.id.eva_save:
                save_addevent();
                break;
        }

    }

    private void save_addevent() {
        String user_id = SharePrefrenceUtil.getObject(Add_eventActivity.this, UsersBean.class).getUerid();
        Map map = new HashMap();
        map.put("user_id", user_id);
        map.put("evevt_date", date);
        map.put("evevt_title", eva_title.getText().toString());
        map.put("event_content", eva_contant.getText().toString());
        map.put("event_start_time", eva_strstTime.getText().toString());
        map.put("event_finish_time", eva_endtime.getText().toString());
        Log.d("添 加 用 户 的 编 号 为 ", String.valueOf(user_id));

        OkHttp.get(this, Constant.add_event, map, new OkCallback<Result<String>>() {
            @Override
            public void onResponse(Result<String>response) {
                Toast.makeText(Add_eventActivity.this, "添 加 事 件 成 功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("data_return","从 添 加 事 件 返 回");
                setResult(RESULT_OK,intent);
                Add_eventActivity.this.finish();
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(Add_eventActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
