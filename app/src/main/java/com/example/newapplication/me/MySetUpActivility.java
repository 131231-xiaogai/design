package com.example.newapplication.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.R;
import com.example.newapplication.newpage.Notice;

public class MySetUpActivility extends AppCompatActivity implements View.OnClickListener {

    ImageView mUser_image;
    TextView mUser_sex,mUser_name;
    ImageButton u_title_back,u_notice;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_setup);
        mUser_image =findViewById(R.id.user_image) ;
        mUser_sex=findViewById(R.id.user_sex) ;
        mUser_name=findViewById(R.id.user_name) ;
        u_title_back=findViewById(R.id.u_title_back) ;
        u_notice=findViewById(R.id.u_notice) ;
        OnClickListener();
    }

    private void OnClickListener() {
        mUser_image.setOnClickListener(this);
        mUser_sex.setOnClickListener(this);
        mUser_name.setOnClickListener(this);
        u_notice.setOnClickListener(this);
        u_title_back.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.u_notice:
                startActivity(new Intent(MySetUpActivility.this, Notice.class));
                break;
            case R.id.u_title_back:
                MySetUpActivility.this.finish();
                break;

        }
    }
}
