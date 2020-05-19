package com.example.newapplication.date;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
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

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Add_eventActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton eva_back,eva_save;
    private EditText eva_title,eva_contant,eva_strstTime,eva_endtime;
    private TextView eva_date,timeShow_s,tv_date_s,tv_time_s;
    private TextView timeShow_f,tv_date_f,tv_time_f;
    private String  date;
    private  DateFormat format =  DateFormat.getDateTimeInstance();
    private  DateFormat format_f =  DateFormat.getDateTimeInstance();
    //获取日期格式器对象
    private Calendar calendar = Calendar.getInstance(Locale.CHINA);
    private Calendar calendar_f = Calendar.getInstance(Locale.CHINA);
    //获取日期格式器对象

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);

        eva_back=findViewById(R.id.eva_back);
        //eva_endtime=findViewById(R.id.eva_endtime);
        timeShow_s=findViewById(R.id.timeShow); //定义一个TextView控件对象,显示得到的时间日期
        eva_title=findViewById(R.id.eva_title);
        eva_contant=findViewById(R.id.eva_contant);
        eva_save=findViewById(R.id.eva_save);
        eva_date=findViewById(R.id.eva_date);
        timeShow_f=findViewById(R.id.timeShow_f);

        tv_time_s=findViewById(R.id.tv_time_s);
        tv_date_s=findViewById(R.id.tv_date_s);
        tv_date_f=findViewById(R.id.tv_date_f);
        tv_time_f=findViewById(R.id.tv_time_f);

        updateTimeShow();
        //将页面TextView的显示更新为最新时间
        tv_date_s.setOnClickListener(new View.OnClickListener() {
            //设置按钮的点击事件监听器
            @Override
            public void onClick(View v) {
                //生成一个DatePickerDialog对象，并显示。显示的DatePickerDialog控件可以选择年月日，并设置
                DatePickerDialog datePickerDialog = new DatePickerDialog(Add_eventActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(Add_eventActivity.this, new TimePickerDialog.OnTimeSetListener() {
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(Add_eventActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(Add_eventActivity.this, new TimePickerDialog.OnTimeSetListener() {
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
        Intent integer = getIntent();
        date = integer.getStringExtra("date");
        eva_date.setText(date);

        //
        OnClickListener();

    }

    private void updateTimeShow() {
        //将页面TextView的显示更新为最新时间
        timeShow_s.setText(format.format(calendar.getTime()));
        timeShow_f.setText(format_f.format(calendar_f.getTime()));
    }

    private void OnClickListener() {
        eva_back.setOnClickListener(this);
        //eva_endtime.setOnClickListener(this);
       // eva_strstTime.setOnClickListener(this);
        eva_title.setOnClickListener(this);
        eva_contant.setOnClickListener(this);
        eva_date.setOnClickListener(this);
        eva_save.setOnClickListener(this);
        //tv_time_s.setOnClickListener(this);
       // tv_date_s.setOnClickListener(this);

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
        map.put("event_start_time", timeShow_s.getText().toString());
        map.put("event_finish_time", timeShow_f.getText().toString());
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
