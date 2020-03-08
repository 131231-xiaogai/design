package com.example.newapplication;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.newpage.Notice;

import java.util.Calendar;

public class DateActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "DateActivity";
    public static final int PERMISSION_RQUEST_CODE = 1;
    ImageButton btn_list, btn_home, btn_shop, btn_me;
    CalendarView calendarView;
    ContentResolver contentResolver;
    //
    private static String CALANDER_URL = "content://com.android.calendar/calendars";
    private static String CALANDER_EVENT_URL = "content://com.android.calendar/events";
    private static String CALANDER_REMIDER_URL = "content://com.android.calendar/reminders";
    Cursor query;
    ImageView btn_notice;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date);
        calendarView = findViewById(R.id.calendarView);

        //日历布局添加监听器
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(DateActivity.this,year+"年"+month+"月"+dayOfMonth+"日",Toast.LENGTH_SHORT).show();
            }
        });
        //底部导航栏
        btn_home = findViewById(R.id.b_home);
        btn_home.setOnClickListener(this);
        btn_list = findViewById(R.id.b_list);
        btn_list.setOnClickListener(this);
        btn_me = findViewById(R.id.b_me);
        btn_me.setOnClickListener(this);
        btn_shop = findViewById(R.id.b_shopcar);
        btn_shop.setOnClickListener(this);
        btn_notice = (ImageView)findViewById(R.id.btn_notice);
        btn_notice.setOnClickListener(this);

        //
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M)
        {
        checkCalendarPermission();}
        queryCalendars();
    }
//    public void addAlterEvent(View view){
//        long calID =1;
//        Calendar beginTime = Calendar.getInstance();
//        beginTime.set(2020,10,10,0,0);
//        long be
//
//    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkCalendarPermission() {
       int readPermission = checkSelfPermission(Manifest.permission.READ_CALENDAR);
        int writePermission = checkSelfPermission(Manifest.permission.WRITE_CALENDAR);
        if (readPermission == PackageManager.PERMISSION_GRANTED && writePermission==PackageManager.PERMISSION_GRANTED){
            //表示没有权限
            Log.d(TAG,"requestPermissions..");

        }else {
            //获取权限
            requestPermissions(new String[]{Manifest.permission.READ_CALENDAR,Manifest.permission.WRITE_CALENDAR},PERMISSION_RQUEST_CODE);
            //提示
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==PERMISSION_RQUEST_CODE){
            //
            if (grantResults.length==2 && grantResults[0]==PackageManager.PERMISSION_GRANTED&&grantResults[1]==PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG,"有权限");
            }else {
                Log.d(TAG,"无权限");
                finish();
            }
        }
    }

    private void queryCalendars() {
        //动态调用日历
        Uri uri = Uri.parse(CALANDER_URL) ;
        contentResolver = getContentResolver();
        query =contentResolver.query(uri,null,null,null,null);
        String[] columnNames = query.getColumnNames();
        while (query.moveToNext()) {


            Log.d(TAG,"====================");
            for (String columnName : columnNames) {
                Log.d(TAG,columnName+"===="+query.getString(query.getColumnIndex(columnName)));
            }
            Log.d(TAG,"====================");
        }
    }

    public void finish_reback(View v){
        DateActivity.this.finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b_home:
                startActivity(new Intent(DateActivity.this,HomeActivity.class));
                break;
            case R.id.b_list:
                startActivity(new Intent(DateActivity.this,ListActivity.class));
                break;
            case R.id.b_me:
                startActivity(new Intent(DateActivity.this,MeActivity.class));
                break;
            case R.id.b_shopcar:
                startActivity(new Intent(DateActivity.this,ShopcarActivity.class));
                break;
            case R.id.btn_notice:
                startActivity(new Intent(DateActivity.this, Notice.class));
                break;

        }

    }
//    private static int checkCalendarAccount(Context context) {
//        Cursor userCursor = context.getContentResolver().query(Uri.parse(CALANDER_URL), null, null, null, null);
//        try {
//            if (userCursor == null)//查询返回空值
//                 return -1;
//            int count = userCursor.getCount();
//            if (count > 0) {//存在现有账户，取第一个账户的id返回
//                userCursor.moveToFirst();
//                return userCursor.getInt(userCursor.getColumnIndex(CalendarContract.Calendars._ID));
//            } else {
//                return -1;
//            }
//        } finally {
//            if (userCursor != null) {
//                userCursor.close();
//            }
//        }
//    }
}

