package com.example.newapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.newapplication.Adapter.PhotoAdapter;
import com.example.newapplication.entity.Photo;
import com.example.newapplication.newpage.Notice;

import java.util.ArrayList;
import java.util.List;

public class ShopcarActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton btn_list, btn_date, btn_home, btn_me;
    private List<Photo> photoList = new ArrayList<>();
    ImageView btn_notice;
    RecyclerView s_recycle_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopcar);
        btn_home = (ImageButton)findViewById(R.id.b_home);
        btn_list = (ImageButton)findViewById(R.id.b_list);
        btn_date = (ImageButton)findViewById(R.id.b_date);
        btn_me = (ImageButton)findViewById(R.id.b_me);
        btn_notice = findViewById(R.id.btn_notice);
        btn_notice.setOnClickListener(this);
        btn_home.setOnClickListener(this);
        btn_list.setOnClickListener(this);
        btn_date.setOnClickListener(this);
        btn_me.setOnClickListener(this);
        //
        initPhoto();
       // PhotoAdapter adapter = new PhotoAdapter(ShopcarActivity.this,R.layout.shop_list,photoList);

         s_recycle_view = findViewById(R.id.s_recycle_view);
       // listView.setAdapter(adapter);
    }

    public void finish_reback(View v){
        ShopcarActivity.this.finish();
    }

    private void initPhoto() {
        for (int i= 0;i < 2;i++){

            Photo home = new Photo("home",R.drawable.image6);
            photoList.add(home);
            Photo list = new Photo("list",R.drawable.image7);
            photoList.add(list);
            Photo date = new Photo("date",R.drawable.image8);
            photoList.add(date);
            Photo shopcar = new Photo("shopcar",R.drawable.image9);
            photoList.add(shopcar);
            Photo me = new Photo("me",R.drawable.image10);
            photoList.add(me);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b_home:
                startActivity(new Intent(ShopcarActivity.this,HomeActivity.class));
                break;
            case R.id.b_list:
                startActivity(new Intent(ShopcarActivity.this,ListActivity.class));
                break;
            case R.id.b_date:
                startActivity(new Intent(ShopcarActivity.this,DateActivity.class));
                break;
            case R.id.b_me:
                startActivity(new Intent(ShopcarActivity.this,MeActivity.class));
                break;
            case R.id.btn_notice:
                startActivity(new Intent(ShopcarActivity.this, Notice.class));
                break;
        }
    }
    //listview数组
    private String[] data = {"home","list","date","shopcar","me"};
}
