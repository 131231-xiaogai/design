package com.example.newapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AdministratorsActivity extends AppCompatActivity implements View.OnClickListener {

    TextView Ad_user;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administrators);
        Ad_user=findViewById(R.id.Ad_user);


        //
        OnClickListener();

    }

    private void OnClickListener() {
        Ad_user.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
