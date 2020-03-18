package com.example.newapplication.newpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.HomeActivity;
import com.example.newapplication.ListActivity;
import com.example.newapplication.R;
import com.example.newapplication.me.WalletpagaActivity;

public class PagetitleActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton p_title_back;
    ImageButton P_notice;
    TextView pageneme;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagetitle);
        p_title_back = findViewById(R.id.n_title_back);

        pageneme=findViewById(R.id.pageneme);
        OnClickListener();

    }

    private void OnClickListener() {
        p_title_back.setOnClickListener(this);

        pageneme.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.p_title_back:
                PagetitleActivity.this.finish();
                break;

        }
    }
}
