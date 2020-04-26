package com.example.newapplication.shopcar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.newapplication.Adapter.AddressAdapter;
import com.example.newapplication.R;
import com.example.newapplication.entity.AddressBean;
import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.inteface.OnItemClickListener;
import com.example.newapplication.me.Edit_Address;
import com.example.newapplication.me.Insert_Address;
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

public class ChooseAddressActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton a_title_back, a_add;
    TextView maddress;
    RecyclerView recyclerView;
    private AddressAdapter addressAdapter;
    private AddressBean data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_address);
        //maddress=findViewById(R.id.address);
        a_title_back = findViewById(R.id.a_title_back);
        a_add = findViewById(R.id.a_add);
        //添加适配器
        recyclerView = findViewById(R.id.ma_recycle_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        addressAdapter = new AddressAdapter(this);
        recyclerView.setAdapter(addressAdapter);

        addressAdapter.setOnItemClickListener(new OnItemClickListener<AddressBean>() {
            @Override
            public void onItemClick(RecyclerViewHolder viewHolder, AddressBean data, int position) {
                Toast.makeText(ChooseAddressActivity.this, data.getAddress_id(), Toast.LENGTH_SHORT).show();
                String contact_name = data.getContact_name();
                String contact_phone = data.getContact_phone();
                String address_detail = data.getAddress_detail();
                String address_total = data.getAddress_total();
                String address_id = data.getAddress_id();
                Intent intent = new Intent(ChooseAddressActivity.this, SettlementActivity.class);
                intent.putExtra("contact_name", contact_name);
                intent.putExtra("contact_phone", contact_phone);
                intent.putExtra("address_detail", address_detail);
                intent.putExtra("address_total", address_total);
                intent.putExtra("address_id", address_id);
                setResult(RESULT_OK, intent);
                finish();

            }
        });
        OnClickListener();
        loadData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String data_return = data.getStringExtra("data_return");
                    Log.d("my_address", data_return);
                    loadData();
                }
                break;
            default:
        }
    }

    private void OnClickListener() {
        a_add.setOnClickListener(this);
        a_title_back.setOnClickListener(this);
    }

    private void loadData() {
        String muserid = SharePrefrenceUtil.getObject(ChooseAddressActivity.this, UsersBean.class).getUerid();
        Map map = new HashMap();
        map.put("muserid", muserid);

        OkHttp.get(this, Constant.select_address_by_userid, map, new OkCallback<Result<List<AddressBean>>>() {
            @Override
            public void onResponse(Result<List<AddressBean>> response) {
                addressAdapter.setNewData(response.getData());
            }

            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(ChooseAddressActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.a_add:
                Intent intent = new Intent(ChooseAddressActivity.this, Insert_Address.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.a_title_back:
                ChooseAddressActivity.this.finish();
                break;

        }

    }


}