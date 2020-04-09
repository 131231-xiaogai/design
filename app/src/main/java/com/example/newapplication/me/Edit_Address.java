package com.example.newapplication.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.R;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;

import java.util.HashMap;
import java.util.Map;

public class Edit_Address extends AppCompatActivity implements View.OnClickListener {

    EditText edit_address_name,edit_address_phone,edit_address1,edit_address2;
    TextView edit_address_back,edit_address_dele;
    String  address_id;
    Button edit_address_save;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_edit_address);
        //绑定控件
        edit_address_name =findViewById(R.id.edit_address_name);
        edit_address_phone =findViewById(R.id.edit_address_phone);
        edit_address1 =findViewById(R.id.edit_address1);
        edit_address2 =findViewById(R.id.edit_address2);
        edit_address_back =findViewById(R.id.edit_address_back);
        edit_address_save =findViewById(R.id.edit_address_save);
        edit_address_dele =findViewById(R.id.edit_address_dele);

        //接收上一个页面的数据
        Intent integer = getIntent();
        String  contact_name = integer.getStringExtra("contact_name");
        String  contact_phone = integer.getStringExtra("contact_phone");
        String  address_detail = integer.getStringExtra("address_detail");
        String  address_total = integer.getStringExtra("address_total");
        address_id =integer.getStringExtra("address_id");
        edit_address_name.setText(contact_name);
        edit_address_phone.setText(contact_phone);
        edit_address1.setText(address_total);
        edit_address2.setText(address_detail);

        //添加监听器
        OnClickListener();
    }

    private void OnClickListener() {
        edit_address_back.setOnClickListener(this);
        edit_address_save.setOnClickListener(this);
        edit_address_dele.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edit_address_save:
                save_edit_address_Data();
                break;
            case R.id.edit_address_back:
                Edit_Address.this.finish();
                break;
            case R.id.edit_address_dele:
                dele_address_Data();
                break;
        }
    }

    private void dele_address_Data() {
        Map map = new HashMap();
        map.put("address_id",address_id);

        OkHttp.get(this, Constant.delete_user_address, map, new OkCallback<Result<String>>() {
            @Override
            public void onResponse(Result<String> response) {
                Toast.makeText(Edit_Address.this, "已 删 除。", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("data_return","my_addresses");
                setResult(RESULT_OK,intent);
                finish();
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(Edit_Address.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void save_edit_address_Data() {
        Map map = new HashMap();
        map.put("address_id",address_id);
        map.put("contact_name",edit_address_name.getText().toString());
        map.put("contact_phone",edit_address_phone.getText().toString());
        map.put("address_total",edit_address1.getText().toString());
        map.put("address_detail",edit_address2.getText().toString());

        OkHttp.get(this, Constant.update_user_address, map, new OkCallback<Result<String>>() {
            @Override
            public void onResponse(Result<String> response) {
                Toast.makeText(Edit_Address.this, "您的修改已经保存。", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("data_return","my_addresses");
                setResult(RESULT_OK,intent);
                finish();
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(Edit_Address.this, msg, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
