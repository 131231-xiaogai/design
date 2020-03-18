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

public class RightFragmenttz extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.l_btn_activity,container,false);//动态加载布局
        //添加控件
        ImageView bianlunsai =view.findViewById(R.id.bianlunsai);
        ImageView wudaolei =view.findViewById(R.id.wudaolei);
        ImageView yinyuelei = view.findViewById(R.id.yinyuelei);
        ImageView yundonglei = view.findViewById(R.id.yundonglei);
        ImageView huaju_xiaopin = view.findViewById(R.id.huaju_xiaopin);
        ImageView a_qita =view.findViewById(R.id.a_qita);
        //给控件注册监听器
        bianlunsai.setOnClickListener(this);
        wudaolei.setOnClickListener(this);
        yinyuelei.setOnClickListener(this);
        yundonglei.setOnClickListener(this);
        huaju_xiaopin.setOnClickListener(this);
        a_qita.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bianlunsai:
                search_bianlunsai();
                break;
            case R.id.wudaolei:
                search_wudaolei();
                break;
            case R.id.yinyuelei:
                search_yinyuelei();
                break;
            case R.id.yundonglei:
                search_yundonglei();
                break;
            case R.id.huaju_xiaopin:
                search_huaju_xiaopin();
                break;
            case R.id.a_qita:
                search_a_qita();
                break;
        }

    }

    private void search_a_qita() {
        String data = " 其 他 ";
        String typeid = "56";
        Intent intent = new Intent();
        intent.setClass(getActivity(), TypeListActivity.class);
        intent.putExtra("typename", data);
        intent.putExtra("typeid", typeid);
        startActivity(intent);

    }

    private void search_huaju_xiaopin() {
        String data = " 话 剧 / 小 品 ";
        String typeid = "55";
        Intent intent = new Intent();
        intent.setClass(getActivity(), TypeListActivity.class);
        intent.putExtra("typename", data);
        intent.putExtra("typeid", typeid);
        startActivity(intent);
    }

    private void search_yundonglei() {
        String data = " 运 动 类 ";
        String typeid = "54";
        Intent intent = new Intent();
        intent.setClass(getActivity(), TypeListActivity.class);
        intent.putExtra("typename", data);
        intent.putExtra("typeid", typeid);
        startActivity(intent);

    }

    private void search_yinyuelei() {
        String data = " 音 乐 类 ";
        String typeid = "53";
        Intent intent = new Intent();
        intent.setClass(getActivity(), TypeListActivity.class);
        intent.putExtra("typename", data);
        intent.putExtra("typeid", typeid);
        startActivity(intent);
    }

    private void search_wudaolei() {
        String data = " 舞 蹈 类 ";
        String typeid = "52";
        Intent intent = new Intent();
        intent.setClass(getActivity(), TypeListActivity.class);
        intent.putExtra("typename", data);
        intent.putExtra("typeid", typeid);
        startActivity(intent);

    }

    private void search_bianlunsai() {
        String data = " 辩 论 赛 ";
        String typeid = "51";
        Intent intent = new Intent();
        intent.setClass(getActivity(), TypeListActivity.class);
        intent.putExtra("typename", data);
        intent.putExtra("typeid", typeid);
        startActivity(intent);
    }
}
