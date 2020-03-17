package com.example.newapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.newapplication.R;
import com.example.newapplication.list.TypeListActivity;


public class RightFragment extends Fragment implements View.OnClickListener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.l_btn_cloth,container,false);//动态加载布局
        //添加控件
        ImageView xizhuang = view.findViewById(R.id.xizhuang);
        ImageView tangzhuang = view.findViewById(R.id.tangzhuang);
        ImageView katongfu =view.findViewById(R.id.katongfu);
        ImageView lifu = view.findViewById(R.id.lifu);
        ImageView hanfu = view.findViewById(R.id.hanfu);
        ImageView shoushi =view.findViewById(R.id.shoushi);
        ImageView xiezi =view.findViewById(R.id.xiezi);
        ImageView qita =view.findViewById(R.id.qita);
       //给控件添加监听
        xizhuang.setOnClickListener(this);
        tangzhuang.setOnClickListener(this);
        katongfu.setOnClickListener(this);
        lifu.setOnClickListener(this);
        hanfu.setOnClickListener(this);
        shoushi.setOnClickListener(this);
        xiezi.setOnClickListener(this);
        qita.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.xizhuang:
                search_xizhuang();
                break;
            case R.id.tangzhuang:
                search_tangzhuang();
                break;
            case R.id.katongfu:
                search_katongfu();
                break;
            case R.id.lifu:
                search_lifu();
                break;
            case R.id.hanfu:
                search_hanfu();
                break;
            case R.id.shoushi:
                search_shoushi();
                break;
            case R.id.xiezi:
                search_xiezi();
                break;
            case R.id.qita:
                search_qita();
                break;
        }
    }

    private void search_qita() {
        String data = " 其  他 ";
        String typeid = "8";
        Intent xiz = new Intent();
        xiz.setClass(getActivity(), TypeListActivity.class);
        xiz.putExtra("typename", data);
        xiz.putExtra("typeid", typeid);
        startActivity(xiz);
    }

    private void search_xiezi() {
        String data = " 鞋  子 ";
        String typeid = "7";
        Intent xiz = new Intent();
        xiz.setClass(getActivity(), TypeListActivity.class);
        xiz.putExtra("typename", data);
        xiz.putExtra("typeid", typeid);
        startActivity(xiz);
    }

    private void search_shoushi() {
        String data = " 首  饰 ";
        String typeid = "6";
        Intent xiz = new Intent();
        xiz.setClass(getActivity(), TypeListActivity.class);
        xiz.putExtra("typename", data);
        xiz.putExtra("typeid", typeid);
        startActivity(xiz);
    }

    private void search_hanfu() {
        String data = " 汉  服 ";
        String typeid = "5";
        Intent xiz = new Intent();
        xiz.setClass(getActivity(), TypeListActivity.class);
        xiz.putExtra("typename", data);
        xiz.putExtra("typeid", typeid);
        startActivity(xiz);
    }

    private void search_lifu() {
        String data = " 礼  服 ";
        String typeid = "4 ";
        Intent xiz = new Intent();
        xiz.setClass(getActivity(), TypeListActivity.class);
        xiz.putExtra("typename", data);
        xiz.putExtra("typeid", typeid);
        startActivity(xiz);
    }

    private void search_katongfu() {
        String data = " 卡 通 服 ";
        String typeid = "3";
        Intent xiz = new Intent();
        xiz.setClass(getActivity(), TypeListActivity.class);
        xiz.putExtra("typename", data);
        xiz.putExtra("typeid", typeid);
        startActivity(xiz);
    }

    private void search_tangzhuang() {
        String data = " 唐  装 ";
        String typeid = "2";
        Intent xiz = new Intent();
        xiz.setClass(getActivity(), TypeListActivity.class);
        xiz.putExtra("typename", data);
        xiz.putExtra("typeid", typeid);
        startActivity(xiz);

    }

    private void search_xizhuang() {
        String data = " 西  装 ";
        String typeid = "1";
        Intent xiz = new Intent();
        xiz.setClass(getActivity(), TypeListActivity.class);
        xiz.putExtra("typename", data);
        xiz.putExtra("typeid", typeid);
        startActivity(xiz);
    }
}

