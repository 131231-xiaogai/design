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
        ImageView xizhuang = view.findViewById(R.id.xizhuang);
        xizhuang.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.xizhuang:
                String data = " 西  装 ";
                Intent xiz = new Intent();
                xiz.setClass(getActivity(), TypeListActivity.class);
                xiz.putExtra("typename", data);
                startActivity(xiz);
                break;
        }
    }
}

