package com.example.newapplication.Administrators;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.newapplication.Adapter.Select_userAdapter;
import com.example.newapplication.R;
import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.inteface.OnItemClickListener;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.example.newapplication.new_utill.Constant.select_all_user;

public class AllUserActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton adm_back;
    private Select_userAdapter select_userAdapter;
    RecyclerView recyclerView;
    TextView adm_pageneme;
    String deleted_number;
    UsersBean data;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adm_re_view);
        adm_back=findViewById(R.id.adm_title_back);
        adm_pageneme=findViewById(R.id.adm_pageneme);
        //
        Intent pagename_integer = getIntent();
        String  data = pagename_integer.getStringExtra("p_pagename");
        deleted_number =pagename_integer.getStringExtra("p_deleted_number");
        adm_pageneme.setText(data);
        Log.d("AllUserActivity",data);
        //添加适配器
        recyclerView =findViewById(R.id.adm_recycle_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        select_userAdapter = new Select_userAdapter(this);
        recyclerView.setAdapter(select_userAdapter);

        select_userAdapter.setOnItemClickListener(new OnItemClickListener<UsersBean>() {
            @Override
            public void onItemClick(RecyclerViewHolder viewHolder, UsersBean data, int position) {
                if (deleted_number.equals("1")){
                    String userId = data.getUerid();
                    String name = data.getNickname();
                    String phone =data.getPhone();
                    String Idnumber = data.getId_number();
                    String sex =data.getSex();
                    String blance = data.getBalance();
                    Intent intent = new Intent(AllUserActivity.this, Adm_ItemDetailActivity.class);
                    intent.putExtra("userId",userId);
                    intent.putExtra("name", name);
                    intent.putExtra("phone", phone);
                    intent.putExtra("Idnumber",Idnumber);
                    intent.putExtra("sex",sex);
                    intent.putExtra("blance",blance);
                    startActivityForResult(intent,1);
                }

            }
        });

        //
        OnClickListener();
        loadData();

    }

    private void OnClickListener() {
        adm_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.adm_title_back:
                AllUserActivity.this.finish();
                break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String data_return = data.getStringExtra("data_return");
                    Log.d("my_address",data_return);
                    loadData();
                }
                break;
            default:
        }
    }

    private void loadData() {
        String role_id="1";
        Map map = new HashMap();
        map.put("role_id",role_id);
        Log.d("AllUserActivity",role_id);

        OkHttp.get(this, select_all_user, map, new OkCallback<Result<List<UsersBean>>>() {
            @Override
            public void onResponse(Result<List<UsersBean>> response) {
                select_userAdapter.setNewData(response.getData());
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(AllUserActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });



    }
}
