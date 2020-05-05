package com.example.newapplication.me;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.newapplication.DateActivity;
import com.example.newapplication.HomeActivity;
import com.example.newapplication.ListActivity;
import com.example.newapplication.MeActivity;
import com.example.newapplication.R;
import com.example.newapplication.ShopcarActivity;
import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.new_utill.SharePrefrenceUtil;
import com.example.newapplication.newpage.Notice;
import com.example.newapplication.shopcar.ChooseAddressActivity;
import com.example.newapplication.shopcar.SettlementActivity;

import java.util.HashMap;
import java.util.Map;


public class WalletpagaActivity extends AppCompatActivity implements View.OnClickListener {
        private ImageButton P_notice;
        private TextView mpageneme,mbalance,add_balance,to_balance;
        private EditText chongzhi;
        private String m;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.m_walletpaga);
            mbalance=findViewById(R.id.balance);
            mpageneme=findViewById(R.id.pageneme);
            P_notice=findViewById(R.id.P_notice);
            add_balance=findViewById(R.id.add_balance);
            chongzhi=findViewById(R.id.chongzhi);
            to_balance=findViewById(R.id.to_balance);
            //
            Intent pagename_integer = getIntent();
            String  data = pagename_integer.getStringExtra("balance");
            mpageneme.setText(data);
            Log.d("WalletpagaActivity",data);
            //

            //
            OnClickListener();
            lodata();
        }

        private void lodata() {
            String balance = SharePrefrenceUtil.getObject(WalletpagaActivity.this, UsersBean.class).getBalance();
            if (balance == null || balance.isEmpty()) {
                mbalance.setText("0.00");
            } else {
                mbalance.setText("￥"+balance);
            }
                }

        private void OnClickListener(){
            P_notice.setOnClickListener(this);
            add_balance.setOnClickListener(this);
            to_balance.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.P_notice:
                    startActivity(new Intent(WalletpagaActivity.this, Notice.class));
                    break;
                case R.id.add_balance:
                    chongzhi.setVisibility(View.VISIBLE);
                    add_balance.setVisibility(View.GONE);
                    to_balance.setVisibility(View.VISIBLE);
                    break;
                case R.id.to_balance:
                    addBalance();
                    break;
            }
        }

    private void addBalance() {
        m = chongzhi.getText().toString();
        String uerid = SharePrefrenceUtil.getObject(WalletpagaActivity.this, UsersBean.class).getUerid();
        String old_balance = SharePrefrenceUtil.getObject(WalletpagaActivity.this, UsersBean.class).getBalance();
        String new_balance =Double.valueOf(m)+Double.valueOf(old_balance)+"";
        AlertDialog.Builder dialog;
        dialog = new AlertDialog.Builder(WalletpagaActivity.this);
        dialog.setTitle("提示");
        dialog.setMessage(String.format("是否充值以下金额：￥%s",m));
        dialog.setCancelable(false);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Map map = new HashMap();
                map.put("user_id", uerid);
                map.put("totalprice", new_balance);
                OkHttp.get(WalletpagaActivity.this, Constant.update_shop_balance, map, new OkCallback<Result<String>>() {
                    @Override
                    public void onResponse(Result<String> response) {
                        Toast.makeText(WalletpagaActivity.this, "充值成功。", Toast.LENGTH_SHORT).show();
                        chongzhi.getText().clear();
                    }
                    @Override
                    public void onFailure(String state, String msg) {
                        Toast.makeText(WalletpagaActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(WalletpagaActivity.this, "您已经取消充值。", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }
}




