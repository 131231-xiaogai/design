package com.example.newapplication.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.R;
import com.example.newapplication.newpage.Notice;

public class MyBodySizeActivility extends AppCompatActivity implements View.OnClickListener {

    ImageButton b_title_back,b_notice;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_bodysize);
        b_title_back=findViewById(R.id.b_title_back);
        b_notice = findViewById(R.id.b_notice);
        OnClickListener();
    }

    private void OnClickListener() {
        b_notice.setOnClickListener(this);
        b_title_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.b_notice:
                startActivity(new Intent(MyBodySizeActivility.this, Notice.class));
                break;
            case R.id.b_title_back:
                MyBodySizeActivility.this.finish();
                break;

        }

    }
}
