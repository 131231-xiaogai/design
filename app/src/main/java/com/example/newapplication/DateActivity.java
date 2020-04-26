package com.example.newapplication;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
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
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.newapplication.Adapter.Eventdapter;
import com.example.newapplication.date.Add_eventActivity;
import com.example.newapplication.date.Event_ItemDetailActivity;
import com.example.newapplication.entity.EventBean;
import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.inteface.OnItemClickListener;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.new_utill.SharePrefrenceUtil;
import com.example.newapplication.newpage.Notice;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DateActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "DateActivity";
    public static final int PERMISSION_RQUEST_CODE = 1;
    private Eventdapter eventdapter;
    RecyclerView s_recycle_view;
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
                int new_month=month+1;
                Toast.makeText(DateActivity.this,year+"年"+new_month+"月"+dayOfMonth+"日",Toast.LENGTH_SHORT).show();
                String  date = year+"-"+new_month+"-"+dayOfMonth;
                Log.d("添 加 事 件 日 期 为 ", date);
                Intent intent = new Intent(DateActivity.this, Add_eventActivity.class);
                intent.putExtra("date", date);
                startActivityForResult(intent,1);
                //select_event(date);
            }
        });
        //底部导航栏
        btn_home = findViewById(R.id.b_home);
        btn_list = findViewById(R.id.b_list);
        btn_me = findViewById(R.id.b_me);
        btn_shop = findViewById(R.id.b_shopcar);
        //
        btn_notice = findViewById(R.id.btn_notice);
        s_recycle_view=findViewById(R.id.dateEvent_recycle_view);
        //
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M)
        {
        checkCalendarPermission();}
        queryCalendars();
        //

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        s_recycle_view.setLayoutManager(layoutManager);
        eventdapter = new Eventdapter(this);
        s_recycle_view.setAdapter(eventdapter);

        eventdapter.setOnItemClickListener(new OnItemClickListener<EventBean>() {
            @Override
            public void onItemClick(RecyclerViewHolder viewHolder, EventBean data, int position) {
                Toast.makeText(DateActivity.this, data.getEvent_id(), Toast.LENGTH_SHORT).show();
                String event_date = data.getEvent_date();
                String event_title = data.getEvevt_title();
                String event_contant = data.getEvent_content();
                String event_startTine = data.getEvent_start_time();
                String event_endTime = data.getEvent_finish_time();
                String event_id = data.getEvent_id();
                Intent intent = new Intent(DateActivity.this, Event_ItemDetailActivity.class);
                intent.putExtra("event_date", event_date);
                intent.putExtra("event_title", event_title);
                intent.putExtra("event_contant", event_contant);
                intent.putExtra("event_startTine", event_startTine);
                intent.putExtra("event_endTime", event_endTime);
                intent.putExtra("event_id", event_id);
                startActivityForResult(intent,1);
            }
        });
        //
        OnClickListener();
        loadData();
    }

    private void OnClickListener() {
        s_recycle_view.setOnClickListener(this);
        btn_notice.setOnClickListener(this);
        btn_shop.setOnClickListener(this);
        btn_me.setOnClickListener(this);
        btn_list.setOnClickListener(this);
        btn_home.setOnClickListener(this);
    }

    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String data_return = data.getStringExtra("data_return");
                    Log.d("name",data_return);
                    loadData();
                }
                break;
            default:
        }
    }

    private void loadData() {
        String userid=SharePrefrenceUtil.getObject(DateActivity.this, UsersBean.class).getUerid();
        Map map = new HashMap();
        map.put("user_id", userid);
        Log.d("用 户 编 号 为 ", String.valueOf(userid));

        OkHttp.get(this, Constant.select_event_byUserID, map, new OkCallback<Result<List<EventBean>>>() {
            @Override
            public void onResponse(Result<List<EventBean>>response) {
                eventdapter.setNewData(response.getData());
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(DateActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void select_event(int date) {
        String userid=SharePrefrenceUtil.getObject(DateActivity.this, UsersBean.class).getUerid();
        Map map = new HashMap();
        map.put("event_date", date);
        map.put("user_id", userid);

        OkHttp.get(this, Constant.select_event, map, new OkCallback<Result<List<EventBean>>>() {
            @Override
            public void onResponse(Result<List<EventBean>>response) {

            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(DateActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
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

