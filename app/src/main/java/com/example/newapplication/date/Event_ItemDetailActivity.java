package com.example.newapplication.date;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.R;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;

import java.util.HashMap;
import java.util.Map;

public class Event_ItemDetailActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton evd_back;
    EditText evd_title,evd_contant,evd_strstTime,evd_endtime;
    Button ved_save_update,evd_deleted;
    TextView evd_date;
    String  event_id;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_detail);

        evd_back=findViewById(R.id.evd_back);
        evd_endtime=findViewById(R.id.evd_endtime);
        evd_strstTime=findViewById(R.id.evd_strstTime);
        evd_title=findViewById(R.id.evd_title);
        evd_contant=findViewById(R.id.evd_contant);
        evd_date=findViewById(R.id.evd_date);
        ved_save_update=findViewById(R.id.ved_save_update);
        evd_deleted=findViewById(R.id.evd_deleted);
        //
        Intent integer = getIntent();
        String  event_date = integer.getStringExtra("event_date");
        String  event_title = integer.getStringExtra("event_title");
        String  event_contant = integer.getStringExtra("event_contant");
        String  event_startTine = integer.getStringExtra("event_startTine");
        String  event_endTime = integer.getStringExtra("event_endTime");
        event_id = integer.getStringExtra("event_id");
        evd_date.setText(event_date);
        evd_title.setText(event_title);
        evd_contant.setText(event_contant);
        evd_strstTime.setText(event_startTine);
        evd_endtime.setText(event_endTime);

        Log.d("事 件 标 题 是",event_title);
        //
        OnClickListener();

    }

    private void OnClickListener() {
        evd_back.setOnClickListener(this);
        evd_endtime.setOnClickListener(this);
        evd_strstTime.setOnClickListener(this);
        evd_title.setOnClickListener(this);
        evd_contant.setOnClickListener(this);
        evd_date.setOnClickListener(this);
        evd_deleted.setOnClickListener(this);
        ved_save_update.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.evd_back:
                Event_ItemDetailActivity.this.finish();
                break;
            case R.id.evd_deleted:
                delete_event();
                break;
            case R.id.ved_save_update:
                update_event();
                break;
        }
    }



    private void update_event() {
        Map map = new HashMap();
        map.put("event_id", event_id);
        map.put("evevt_title", evd_title.getText().toString());
        map.put("event_content", evd_contant.getText().toString());
        map.put("event_start_time", evd_strstTime.getText().toString());
        map.put("event_finish_time", evd_endtime.getText().toString());
        Log.d("要 修 改 的 事 件 编 号 为 ", String.valueOf(event_id));

        OkHttp.get(this, Constant.update_event_byEventID, map, new OkCallback<Result<String>>() {
            @Override
            public void onResponse(Result<String>response) {
                Toast.makeText(Event_ItemDetailActivity.this, "修 改 事 件 成 功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("data_return","从 修 改 事 件 返 回");
                setResult(RESULT_OK,intent);
                Event_ItemDetailActivity.this.finish();
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(Event_ItemDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void delete_event() {
        Map map = new HashMap();
        map.put("event_id", event_id);
        Log.d("要 删 除 的 事 件 编 号 为 ",  String.valueOf(event_id));

        OkHttp.get(this, Constant.deletede_event_byEventID, map, new OkCallback<Result<String>>() {
            @Override
            public void onResponse(Result<String>response) {
                Toast.makeText(Event_ItemDetailActivity.this, "删 除 事 件 成 功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("data_return","从 删 除 事 件 返 回");
                setResult(RESULT_OK,intent);
                Event_ItemDetailActivity.this.finish();
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(Event_ItemDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
