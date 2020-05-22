package com.example.newapplication.Administrators;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.newapplication.Adapter.Select_userAdapter;
import com.example.newapplication.R;
import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.inteface.OnItemClickListener;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.newpage.Notice;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.newapplication.new_utill.Constant.select_all_user_likeNane;

public class Ad_select_user_dj extends AppCompatActivity implements View.OnClickListener {
    private TextView title_page;
    private ImageButton title_back,btn_notice;
    private String user_id,status,my_find_name;
    private Select_userAdapter sk_orderAdapter;
    private RecyclerView s_recycle_view;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ImageView sk_order_find;
    private EditText sk_input_goodname;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ad_select_user);

        title_back =findViewById(R.id.ad_select_user_back);
        btn_notice=findViewById(R.id.ad_select_user_notice);
        sk_order_find=findViewById(R.id.ad_select_user_find);
        sk_input_goodname=findViewById(R.id.input_ad_select_userName);
        s_recycle_view=findViewById(R.id.ad_select_user_recycle_view);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        s_recycle_view.setLayoutManager(layoutManager);
        sk_orderAdapter = new Select_userAdapter(this);
        s_recycle_view.setAdapter(sk_orderAdapter);
        sk_orderAdapter.setOnItemClickListener(new OnItemClickListener<UsersBean>() {
            @Override
            public void onItemClick(RecyclerViewHolder viewHolder, UsersBean data, int position) {
                Toast.makeText(Ad_select_user_dj.this, data.getUerid(), Toast.LENGTH_SHORT).show();
                user_id=data.getUerid();
                AlertDialog.Builder dialog;
                dialog = new AlertDialog.Builder(Ad_select_user_dj.this);
                dialog.setTitle("提示");
                dialog.setMessage(String.format("是否冻结该用户？"));
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String role_id="4";
                        Map map = new HashMap();
                        map.put("user_id",user_id);
                        map.put("role_id",role_id);
                        Log.d("冻结的顾客编号",user_id);
                        OkHttp.get(Ad_select_user_dj.this, Constant.update_user_role, map, new OkCallback<Result<String>>() {
                            @Override
                            public void onResponse(Result<String> response) {
                                Toast.makeText(Ad_select_user_dj.this, "已经冻结该用户。", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent();
                                intent.putExtra("data_return","已经冻结该用户");
                                setResult(RESULT_OK,intent);
                                Ad_select_user_dj.this.finish();
                            }
                            @Override
                            public void onFailure(String state, String msg) {
                                Toast.makeText(Ad_select_user_dj.this, msg, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog.show();
            }
        });

        OnClickListener();

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

    private void OnClickListener() {
        title_back.setOnClickListener(this);
        btn_notice.setOnClickListener(this);
        sk_order_find.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ad_select_user_find:
                loadData();
                break;
            case R.id.ad_select_user_back:
                Ad_select_user_dj.this.finish();
                break;
            case R.id.ad_select_user_notice:
                startActivity(new Intent(Ad_select_user_dj.this, Notice.class));
                break;

        }

    }

    private void loadData() {
        String role_id="1";
        Map map = new HashMap();
        map.put("role_id",role_id);
        map.put("role_status","1");
        map.put("nickname",sk_input_goodname.getText().toString());
        Log.d("AllUserActivity",role_id);

        OkHttp.get(this, select_all_user_likeNane, map, new OkCallback<Result<List<UsersBean>>>() {
            @Override
            public void onResponse(Result<List<UsersBean>> response) {
                sk_orderAdapter.setNewData(response.getData());
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(Ad_select_user_dj.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
