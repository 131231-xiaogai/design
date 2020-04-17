package com.example.newapplication.Administrators;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.R;

public class Adm_ItemDetailActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton adm_a_back,adm_d_deleted;
    TextView muserId,mname,mphone,mIdnumber,msex,mblance;
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.adm_itemdetail);

        adm_a_back=findViewById(R.id.adm_d_back);
        adm_d_deleted=findViewById(R.id.adm_d_deleted);
        muserId=findViewById(R.id.adm_d_user_id);
        mname=findViewById(R.id.adm_d_name);
        mphone=findViewById(R.id.adm_d_phone);
        mIdnumber=findViewById(R.id.adm_d_IDnumber);
        msex=findViewById(R.id.adm_d_sex);
        mblance=findViewById(R.id.adm_d_balance);
        //
        //接收上一个页面的数据
        Intent integer = getIntent();
        String  userId = integer.getStringExtra("userId");
        String  name = integer.getStringExtra("name");
        String  phone = integer.getStringExtra("phone");
        String  Idnumber = integer.getStringExtra("Idnumber");
        String  sex = integer.getStringExtra("sex");
        String  blance = integer.getStringExtra("blance");

        muserId.setText(userId);
        mname.setText(name);
        mphone.setText(phone);
        mIdnumber.setText(Idnumber);
        msex.setText(sex);
        mblance.setText(blance);
        //
        OnClickListener();

    }

    private void OnClickListener() {
        adm_a_back.setOnClickListener(this);
        adm_d_deleted.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.adm_d_back:
                Adm_ItemDetailActivity.this.finish();
                break;
            case R.id.adm_d_deleted:
                adm_deleted();
                break;
        }

    }


    private void adm_deleted() {



    }
}
