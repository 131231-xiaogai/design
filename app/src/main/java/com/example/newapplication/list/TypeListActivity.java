package com.example.newapplication.list;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.newapplication.Adapter.TypeListAdapter;
import com.example.newapplication.R;
import com.example.newapplication.entity.GoodBean;
import com.example.newapplication.home.ItemDetailActivity;
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

public class TypeListActivity extends AppCompatActivity implements View.OnClickListener {

    TextView typename;
    RecyclerView recyclerView;
    private TypeListAdapter typeListAdapter;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_typelist);
        typename=findViewById(R.id.typename);
        //接收上一个页面的数据
        Intent integer = getIntent();
        String  data = integer.getStringExtra("typename");
        String  typeid = integer.getStringExtra("typeid");
        typename.setText(data);
        Log.d("TypeListActivity",data);
        //添加适配器
        recyclerView=findViewById(R.id.type_recycle_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        typeListAdapter = new TypeListAdapter(this);
        recyclerView.setAdapter(typeListAdapter);
        typeListAdapter.setOnItemClickListener(new OnItemClickListener<GoodBean>() {
            @Override
            public void onItemClick(RecyclerViewHolder viewHolder, GoodBean data, int position) {
                Toast.makeText(TypeListActivity.this, data.getGoods_id(), Toast.LENGTH_SHORT).show();
                String da = data.getGoods_id();
                Intent intent = new Intent(TypeListActivity.this, ItemDetailActivity.class);
                intent.putExtra("hgoodid", da);
                startActivity(intent);
            }
        });
        if (typeid.length()>1){
            loadData_a(typeid);
        }else {
            loadData(typeid);
        }


        OnClickListener();
    }

    private void loadData_a(String typeid) {
        Map map = new HashMap();
        map.put("mtype_activity_id",typeid);
        OkHttp.get(this, Constant.select_good_by_type_activityid, map, new OkCallback<Result<List<GoodBean>>>() {
            @Override
            public void onResponse(Result<List<GoodBean>> response) {
                typeListAdapter.setNewData(response.getData());
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(TypeListActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void finish_reback(View v) {
        TypeListActivity.this.finish();
    }
    public  void my_notice (View v){
        startActivity(new Intent(TypeListActivity.this, Notice.class));
    }

    private void loadData(String typeid) {

        Map map = new HashMap();
        map.put("mtypeid",typeid);
        OkHttp.get(this, Constant.select_good_by_typeid, map, new OkCallback<Result<List<GoodBean>>>() {
            @Override
            public void onResponse(Result<List<GoodBean>> response) {
                typeListAdapter.setNewData(response.getData());
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(TypeListActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void OnClickListener() {
        typename.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


    }
}
