package com.example.newapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.newapplication.Adapter.PhotoAdapter;
import com.example.newapplication.entity.Photo;

import java.util.ArrayList;
import java.util.List;

public class ShopcarActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton s_btn_home,s_btn_list,s_btn_date,s_btn_me;
    private List<Photo> photoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopcar);
        s_btn_home = (ImageButton)findViewById(R.id.s_bottom_home);
        s_btn_list = (ImageButton)findViewById(R.id.s_button_list);
        s_btn_date = (ImageButton)findViewById(R.id.s_button_date);
        s_btn_me = (ImageButton)findViewById(R.id.s_button_me);
        s_btn_home.setOnClickListener(this);
        s_btn_list.setOnClickListener(this);
        s_btn_date.setOnClickListener(this);
        s_btn_me.setOnClickListener(this);

        //
        initPhoto();
        PhotoAdapter adapter = new PhotoAdapter(ShopcarActivity.this,R.layout.shop_list,photoList);

        ListView listView = (ListView)findViewById(R.id.s_list_view);
        listView.setAdapter(adapter);
//        listView.setOnClickListener(new AdapterView.OnItemClickListener() {
//
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Photo photo =  photoList.get(position);
//                Toast.makeText(ShopcarActivity.this,photo.getName(),Toast.LENGTH_SHORT).show();
//            }
//        });

//        //listview适配器
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                ShopcarActivity.this,android.R.layout.simple_list_item_1,data);
//        ListView listView = (ListView)findViewById(R.id.s_list_view);
//        listView.setAdapter(adapter);
    }

    private void initPhoto() {
        for (int i= 0;i < 2;i++){

            Photo home = new Photo("home",R.mipmap.ic_home);
            photoList.add(home);
            Photo list = new Photo("list",R.mipmap.ic_list);
            photoList.add(list);
            Photo date = new Photo("date",R.mipmap.ic_wallet);
            photoList.add(date);
            Photo shopcar = new Photo("shopcar",R.mipmap.ic_shopcar);
            photoList.add(shopcar);
            Photo me = new Photo("me",R.mipmap.ic_me);
            photoList.add(me);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.s_bottom_home:
                startActivity(new Intent(ShopcarActivity.this,HomeActivity.class));
                break;
            case R.id.s_button_list:
                startActivity(new Intent(ShopcarActivity.this,ListActivity.class));
                break;
            case R.id.s_button_date:
                startActivity(new Intent(ShopcarActivity.this,DateActivity.class));
                break;
            case R.id.s_button_me:
                startActivity(new Intent(ShopcarActivity.this,MeActivity.class));
                break;
        }
    }
    //listview数组
    private String[] data = {"home","list","date","shopcar","me"};
}
