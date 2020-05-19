package com.example.newapplication.date;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.R;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Event_ItemDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton evd_back;
    private EditText evd_title,evd_contant;
    private Button ved_save_update,evd_deleted;
    private TextView evd_date;
    private TextView evd_strstTime,evd_endtime;//显示时间
    private TextView tv_date_f,tv_time_f,tv_date_s,tv_time_s;//显示时间
    private String  event_id;

    private DateFormat format =  DateFormat.getDateTimeInstance();
    private  DateFormat format_f =  DateFormat.getDateTimeInstance();
    //获取日期格式器对象
    private Calendar calendar = Calendar.getInstance(Locale.CHINA);
    private Calendar calendar_f = Calendar.getInstance(Locale.CHINA);
    //获取日期格式器对象

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

        tv_time_s=findViewById(R.id.change_time_s);
        tv_date_s=findViewById(R.id.change_date_s);
        tv_date_f=findViewById(R.id.change_date_f);
        tv_time_f=findViewById(R.id.change_time_f);
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
        //
        updateTimeShow();
        //将页面TextView的显示更新为最新时间
        tv_date_s.setOnClickListener(new View.OnClickListener() {
            //设置按钮的点击事件监听器
            @Override
            public void onClick(View v) {
                //生成一个DatePickerDialog对象，并显示。显示的DatePickerDialog控件可以选择年月日，并设置
                DatePickerDialog datePickerDialog = new DatePickerDialog(Event_ItemDetailActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //修改日历控件的年，月，日
                        //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
                        calendar.set(Calendar.YEAR,year);
                        calendar.set(Calendar.MONTH,month);
                        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        updateTimeShow();
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                updateTimeShow();
                //将页面TextView的显示更新为最新时间
            }
        });
        tv_time_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(Event_ItemDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //同DatePickerDialog控件
                        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar.set(Calendar.MINUTE,minute);
                    }
                },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true);
                timePickerDialog.show();
                updateTimeShow();
                //将页面TextView的显示更新为最新时间
            }
        });

        tv_date_f.setOnClickListener(new View.OnClickListener() {
            //设置按钮的点击事件监听器
            @Override
            public void onClick(View v) {
                //生成一个DatePickerDialog对象，并显示。显示的DatePickerDialog控件可以选择年月日，并设置
                DatePickerDialog datePickerDialog = new DatePickerDialog(Event_ItemDetailActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //修改日历控件的年，月，日
                        //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
                        calendar_f.set(Calendar.YEAR,year);
                        calendar_f.set(Calendar.MONTH,month);
                        calendar_f.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                        updateTimeShow();
                    }
                }, calendar_f.get(Calendar.YEAR), calendar_f.get(Calendar.MONTH), calendar_f.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                updateTimeShow();
                //将页面TextView的显示更新为最新时间
            }
        });
        tv_time_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(Event_ItemDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //同DatePickerDialog控件
                        calendar_f.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        calendar_f.set(Calendar.MINUTE,minute);
                    }
                },calendar_f.get(Calendar.HOUR_OF_DAY),calendar_f.get(Calendar.MINUTE),true);
                timePickerDialog.show();
                updateTimeShow();
                //将页面TextView的显示更新为最新时间
            }
        });

        //

        Log.d("事 件 标 题 是",event_title);
        //
        OnClickListener();

    }

    private void updateTimeShow() {
        //将页面TextView的显示更新为最新时间
        evd_strstTime.setText(format.format(calendar.getTime()));
        evd_endtime.setText(format_f.format(calendar_f.getTime()));
    }

    private void OnClickListener() {
        evd_back.setOnClickListener(this);
        //evd_endtime.setOnClickListener(this);
        //evd_strstTime.setOnClickListener(this);
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
