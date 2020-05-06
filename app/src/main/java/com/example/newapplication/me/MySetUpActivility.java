package com.example.newapplication.me;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newapplication.MeActivity;
import com.example.newapplication.R;
import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.new_utill.SharePrefrenceUtil;
import com.example.newapplication.newpage.Notice;

import java.util.HashMap;
import java.util.Map;

public class MySetUpActivility extends AppCompatActivity implements View.OnClickListener {

    ImageView mUser_image;
    TextView mUser_sex,muser_name;
    ImageButton u_title_back,u_notice;
    LinearLayout setup_name,setup_sex;
    Intent intent;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_setup);
        mUser_image =findViewById(R.id.user_image) ;
        mUser_sex=findViewById(R.id.user_sex) ;
        muser_name=findViewById(R.id.user_name) ;
        u_title_back=findViewById(R.id.u_title_back) ;
        u_notice=findViewById(R.id.u_notice) ;
        setup_name=findViewById(R.id.setup_name);
        setup_sex =findViewById(R.id.setup_sex);
        OnClickListener();
        loadData();
    }

    private void loadData() {
        String nickname = SharePrefrenceUtil.getObject(MySetUpActivility.this, UsersBean.class).getNickname();
        String usersex = SharePrefrenceUtil.getObject(MySetUpActivility.this, UsersBean.class).getSex();
        if (nickname == null || nickname.isEmpty()){
            muser_name.setText("暂无");
        }else{
            muser_name.setText(nickname);
        }
        if (usersex == null || usersex.isEmpty()){
            mUser_sex.setText("暂无");
        }else{
            mUser_sex.setText(usersex);
        }
       /* if (userimg == null || userimg.isEmpty()){
            mUser_sex.setText("暂未设置");
        }else{
            mUser_sex.setText(userimg);
        }*/
    }

    private void OnClickListener() {
        mUser_image.setOnClickListener(this);
        mUser_sex.setOnClickListener(this);
        muser_name.setOnClickListener(this);
        u_notice.setOnClickListener(this);
        u_title_back.setOnClickListener(this);
        setup_name.setOnClickListener(this);
        setup_sex.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String data_return = data.getStringExtra("data_return");
                    Log.d("name",data_return);
                    re_select_user();
                }
                break;
            default:
        }
    }

    private void re_select_user() {
        String user_ID = SharePrefrenceUtil.getObject(MySetUpActivility.this, UsersBean.class).getUerid();
        Log.d("用户编号",user_ID);
        Map map = new HashMap();
        map.put("muser_id",user_ID);

        OkHttp.get(this, Constant.select_user_by_id, map, new OkCallback<Result<UsersBean>>() {
            @Override
            public void onResponse(Result<UsersBean> response) {

                String nickname = response.getData().getNickname();
                String usersex = response.getData().getSex();
                if (response.getData() != null) {
                    //Toast.makeText(MySetUpActivility.this, "保存成功！", Toast.LENGTH_SHORT).show(); cleanObject
                    if (nickname == null || nickname.isEmpty()){
                        muser_name.setText("暂无");
                    }else{
                        muser_name.setText(nickname);
                    }
                    if (usersex == null || usersex.isEmpty()){
                        mUser_sex.setText("暂无");
                    }else{
                        mUser_sex.setText(usersex);
                    }
       /* if (userimg == null || userimg.isEmpty()){
            mUser_sex.setText("暂未设置");
        }else{
            mUser_sex.setText(userimg);
        }*/
                }
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(MySetUpActivility.this, msg, Toast.LENGTH_SHORT).show();
            }

        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.user_name:
                intent =new Intent(MySetUpActivility.this, SetUserNameActivity.class);
                startActivityForResult(intent,1);
                break;
            case R.id.u_title_back:
                MySetUpActivility.this.finish();
                break;
            case  R.id.user_sex:
                intent =new Intent(MySetUpActivility.this, SetUserSexActivity.class);
                startActivityForResult(intent,1);
                break;

        }
    }
}
