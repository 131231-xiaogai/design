package com.example.newapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.newapplication.fragment.RightFragment;
import com.example.newapplication.fragment.RightFragment2;
import com.example.newapplication.fragment.RightFragmenttz;

public class ListActivity extends AppCompatActivity implements View.OnClickListener {

    Button button,btn_tz;
    ImageButton l_btn_home,l_btn_date,l_btn_shopcar,l_btn_me;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        //底部导航栏
        l_btn_home = findViewById(R.id.l_bottom_home);
        l_btn_home.setOnClickListener(this);
        l_btn_date = findViewById(R.id.l_button_date);
        l_btn_date.setOnClickListener(this);
        l_btn_shopcar = findViewById(R.id.l_button_shopcar);
        l_btn_shopcar.setOnClickListener(this);
        l_btn_me = findViewById(R.id.l_button_me);
        l_btn_me.setOnClickListener(this);

        //左侧碎片的按钮
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        btn_tz = (Button)findViewById(R.id.btn_tz);
        btn_tz.setOnClickListener(this);
        replaceFragment(new RightFragment());
    }
    //重置碎片的方法
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.right_layout,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //左侧碎片的按钮
            case R.id.button:
                replaceFragment(new RightFragment2());
                break;
            case R.id.btn_tz:
                replaceFragment(new RightFragmenttz());
                break;
                //底部导航栏按钮
            case R.id.l_bottom_home:
                startActivity(new Intent(ListActivity.this,HomeActivity.class));
                break;
            case R.id.l_button_date:
                startActivity(new Intent(ListActivity.this,DateActivity.class));
                break;
            case R.id.l_button_shopcar:
                startActivity(new Intent(ListActivity.this,ShopcarActivity.class));
                break;
            case R.id.l_button_me:
                startActivity(new Intent(ListActivity.this,MeActivity.class));
                break;
        }

    }
}
