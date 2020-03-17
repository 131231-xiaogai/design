package com.example.newapplication.list;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.R;

public class TypeListActivity extends AppCompatActivity implements View.OnClickListener {

    TextView typename;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_typelist);
        typename=findViewById(R.id.typename);
        //接收上一个页面的数据
        Intent typename_integer = getIntent();
        String  data = typename_integer.getStringExtra("typename");
        typename.setText(data);
        Log.d("TypeListActivity",data);
        //


        OnClickListener();
    }

    private void OnClickListener() {
        typename.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


    }
}
