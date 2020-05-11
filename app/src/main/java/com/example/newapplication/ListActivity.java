package com.example.newapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.newapplication.fragment.RightFragment;
import com.example.newapplication.fragment.RightFragmenttz;
import com.example.newapplication.list.TypeListActivity;
import com.example.newapplication.newpage.Notice;

public class ListActivity extends AppCompatActivity implements View.OnClickListener {

    Button l_btn_activity,l_btn_cloth;
    ImageButton btn_home, btn_date, btn_shop, btn_me;
    ImageView btn_notice,xizhuang;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        //底部导航栏
        btn_home = findViewById(R.id.b_home);
        btn_date = findViewById(R.id.b_date);
        btn_shop = findViewById(R.id.b_shopcar);
        btn_me = findViewById(R.id.b_me);
        //左侧碎片的按钮
        l_btn_cloth = (Button) findViewById(R.id.l_btn_cloth);
        l_btn_activity = (Button)findViewById(R.id.l_btn_activity);
        replaceFragment(new RightFragment());
        //右侧碎片的按钮
        //xizhuang =findViewById(R.id.xizhuang);
        //
        btn_notice=findViewById(R.id.btn_notice);
        OnClickListener();
    }

    private void OnClickListener() {
        btn_notice.setOnClickListener(this);
        l_btn_activity.setOnClickListener(this);
        btn_home.setOnClickListener(this);
        btn_date.setOnClickListener(this);
        l_btn_cloth.setOnClickListener(this);
        btn_shop.setOnClickListener(this);
        btn_me.setOnClickListener(this);
       // xizhuang.setOnClickListener(this);
    }

    //重置碎片的方法
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.right_layout,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void finish_reback(View v){
        ListActivity.this.finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //左侧碎片的按钮
            case R.id.l_btn_cloth:
                replaceFragment(new RightFragment());
                break;
            case R.id.l_btn_activity:
                replaceFragment(new RightFragmenttz());
                break;
                //底部导航栏按钮
            case R.id.b_home:
                startActivity(new Intent(ListActivity.this,HomeActivity.class));
                ListActivity.this.finish();
                break;
            case R.id.b_date:
                startActivity(new Intent(ListActivity.this,DateActivity.class));
                ListActivity.this.finish();
                break;
            case R.id.b_shopcar:
                startActivity(new Intent(ListActivity.this,ShopcarActivity.class));
                ListActivity.this.finish();
                break;
            case R.id.b_me:
                startActivity(new Intent(ListActivity.this,MeActivity.class));
                ListActivity.this.finish();
                break;
            case R.id.btn_notice:
                startActivity(new Intent(ListActivity.this, Notice.class));
                ListActivity.this.finish();
                break;
        }

    }
}
