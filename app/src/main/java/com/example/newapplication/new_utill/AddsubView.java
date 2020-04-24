package com.example.newapplication.new_utill;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.newapplication.R;

public class AddsubView extends LinearLayout implements View.OnClickListener {
    private ImageView iv_add,iv_del;
    private TextView vzlue;

    private int value =1,max_value=10,min_value=1;



    public AddsubView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.add_sub,this);
        iv_add = findViewById(R.id.iv_add);
        iv_del = findViewById(R.id.iv_del);
        vzlue = findViewById(R.id.value);

        int value = getValue();
        setValue(value);

        //点击事件
        iv_del.setOnClickListener(this);
        iv_add.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_add:
                add_number();
                break;

            case R.id.iv_del:
                del_number();
                break;
        }

    }

    private void del_number() {
        if (value >= min_value){
            value --;
        }
        setValue(value);

        if (onNumberChangeListener != null){
            onNumberChangeListener.onNumberChange(value);
        }

    }

    private void add_number() {
        if (value < max_value){
            value ++;
        }
        setValue(value);

        if (onNumberChangeListener != null){
            onNumberChangeListener.onNumberChange(value);
        }

    }

    public int getValue() {
       String vstr= vzlue.getText().toString().trim();
       if (!TextUtils.isEmpty(vstr)){
           value = Integer.parseInt(vstr);
       }
        return value;

    }


    public void setValue(int value) {
        this.value = value;
        vzlue.setText(value+"");
    }

    public int getMax_value() {
        return max_value;
    }

    public void setMax_value(int max_value) {
        this.max_value = max_value;
    }

    public int getMin_value() {
        return min_value;
    }

    public void setMin_value(int min_value) {
        this.min_value = min_value;
    }




    public  interface OnNumberChangeListener{

//        当数据发生变化的时候回调接口
        public void onNumberChange(int value);
    }
    private OnNumberChangeListener  onNumberChangeListener;


    public void setOnNumberChangeListener(OnNumberChangeListener onNumberChangeListener) {
        this.onNumberChangeListener = onNumberChangeListener;
    }
}
