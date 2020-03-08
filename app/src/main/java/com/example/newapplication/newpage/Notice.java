package com.example.newapplication.newpage;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.R;

public class Notice extends AppCompatActivity implements View.OnClickListener {
    ImageButton n_title_back;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notices);
        n_title_back = findViewById(R.id.n_title_back);
        n_title_back.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Notice.this.finish();


    }
}
