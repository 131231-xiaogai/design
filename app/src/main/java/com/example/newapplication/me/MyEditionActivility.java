package com.example.newapplication.me;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.R;
import com.example.newapplication.newpage.Notice;

public class MyEditionActivility extends AppCompatActivity implements View.OnClickListener {

    ImageButton e_title_back;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_edition);
        e_title_back=findViewById(R.id.e_title_back);

        OnClickListener();
    }

    private void OnClickListener() {
        e_title_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.e_title_back:
                MyEditionActivility.this.finish();
                break;

        }

    }
}