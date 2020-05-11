package com.example.newapplication.newpage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.newapplication.Adapter.AddressAdapter;
import com.example.newapplication.Adapter.MessageAdapter;
import com.example.newapplication.MainActivity;
import com.example.newapplication.R;
import com.example.newapplication.ShopcarActivity;
import com.example.newapplication.entity.AddressBean;
import com.example.newapplication.entity.MssageBean;
import com.example.newapplication.entity.Shooping_carBean;
import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.inteface.OnItemClickListener;
import com.example.newapplication.me.Edit_Address;
import com.example.newapplication.me.MyaddressActivility;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.new_utill.SharePrefrenceUtil;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Notice extends AppCompatActivity implements View.OnClickListener {
    private ImageButton n_title_back;
    private TextView your_notic,n_sys_notic;
    private RecyclerView sys_notic_recycle_view;
    private RecyclerView  my_notic_recycle_view;
    private MessageAdapter messageAdapter1,messageAdapter2;
    private Button login_out;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);
        n_title_back = findViewById(R.id.n_title_back);
        your_notic = findViewById(R.id.n_your_notic);
        my_notic_recycle_view =findViewById(R.id.my_notic_recycle_view);
        sys_notic_recycle_view =findViewById(R.id.sys_notic_recycle_view);
        n_sys_notic =findViewById(R.id.n_sys_notic);
        login_out=findViewById(R.id.login_out);
        //添加适配器
        StaggeredGridLayoutManager layoutManager1 = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        StaggeredGridLayoutManager layoutManager2 = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        my_notic_recycle_view.setLayoutManager(layoutManager1);
        sys_notic_recycle_view.setLayoutManager(layoutManager2);
        messageAdapter1 = new MessageAdapter(this);
        messageAdapter2 =new MessageAdapter(this);
        my_notic_recycle_view.setAdapter(messageAdapter1);
        sys_notic_recycle_view.setAdapter(messageAdapter2);

        //

        OnClickListener();
    }

    private void OnClickListener() {
        n_title_back.setOnClickListener(this);
        your_notic.setOnClickListener(this);
        n_sys_notic.setOnClickListener(this);
        login_out.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.n_title_back:
            Notice.this.finish();
            break;
            case R.id.n_your_notic:
                select_maynoties();
                break;
            case R.id.n_sys_notic:
                select_systemnoties();
                break;
            case R.id.login_out:
                check_login_out();
                break;
        }
    }

    private void check_login_out() {

        AlertDialog.Builder dialog;
        dialog = new AlertDialog.Builder(Notice.this);
        dialog.setTitle("提示");
        dialog.setMessage(String.format("确定要退出登陆吗？"));
        dialog.setCancelable(false);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Notice.this, MainActivity.class));
                Notice.this.finish();
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }

    //顾客查询系统消息 1-1
    //message_status：消息状态：1顾客要读的信息；2.商家要读的信息
    //message_type：消息类型：1系统消息；2用户消息
    private void select_systemnoties() {
        my_notic_recycle_view.setVisibility(View.GONE);
        sys_notic_recycle_view.setVisibility(View.VISIBLE);
        String user_id = SharePrefrenceUtil.getObject(Notice.this, UsersBean.class).getUerid();
        Map map = new HashMap();
        map.put("user_id", user_id);
        map.put("message_status", "1");
        map.put("message_type", "1");

        OkHttp.get(this, Constant.select_message, map, new OkCallback<Result<List<MssageBean>>>() {
            @Override
            public void onResponse(Result<List<MssageBean>> response) {
                messageAdapter2.setNewData(response.getData());
            }

            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(Notice.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }



    //顾客查询用户消息 1-2
    //message_status：消息状态：1顾客要读的信息；2.商家要读的信息
    //message_type：消息类型：1系统消息；2用户消息
    private void select_maynoties() {
        my_notic_recycle_view.setVisibility(View.VISIBLE);
        sys_notic_recycle_view.setVisibility(View.GONE);
        String user_id = SharePrefrenceUtil.getObject(Notice.this, UsersBean.class).getUerid();
        Map map = new HashMap();
        map.put("user_id", user_id);
        map.put("message_status", "1");
        map.put("message_type", "2");

        OkHttp.get(this, Constant.select_message, map, new OkCallback<Result<List<MssageBean>>>() {
            @Override
            public void onResponse(Result<List<MssageBean>> response) {
                messageAdapter1.setNewData(response.getData());
            }

            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(Notice.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
