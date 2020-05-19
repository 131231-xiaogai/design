package com.example.newapplication.shopcar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newapplication.Adapter.BaseRecyclerViewAdapter;
import com.example.newapplication.Adapter.ShopcarNewAdapter;
import com.example.newapplication.Adapter.Submit_OrderAdapter;
import com.example.newapplication.R;
import com.example.newapplication.ShopcarActivity;
import com.example.newapplication.entity.AddressBean;
import com.example.newapplication.entity.Shooping_carBean;
import com.example.newapplication.entity.UsersBean;
import com.example.newapplication.home.ItemDetailActivity;
import com.example.newapplication.inteface.OnItemChildClickListener;
import com.example.newapplication.inteface.OnItemClickListener;
import com.example.newapplication.me.MyaddressActivility;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.new_utill.SharePrefrenceUtil;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettlementActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView addoderaddress, name, phone, detail_address, total_address, sub_order;
    private Submit_OrderAdapter shopcarAdapter;
    private RecyclerView s_recycle_view;
    private String mname;
    private String mphone;
    private String detaila;
    private String total;
    private RadioGroup rg;
    private RadioButton rb1;
    private RadioButton rb2;
    private String address_id;
    private String deliver;
    private List shopcards;
    private ImageButton bk;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settlement);
        addoderaddress = findViewById(R.id.addoder_address);
        name = findViewById(R.id.addoder_name);
        phone = findViewById(R.id.addoder_phone);
        detail_address = findViewById(R.id.addoder_adresstatal);
        total_address = findViewById(R.id.addoder_adressdetial);
        addoderaddress.setOnClickListener(this);
        sub_order = findViewById(R.id.sub_order);
        s_recycle_view = findViewById(R.id.syb_order_recycle_view);
        sub_order.setOnClickListener(this);
        bk=findViewById(R.id.bk);
        bk.setOnClickListener(this);

        rg = (RadioGroup) findViewById(R.id.rg);
        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);
        //-----------
        Intent intent = getIntent();
        shopcards = (List<Shooping_carBean>) intent.getSerializableExtra("shopcards");
        //---------
        s_recycle_view.setLayoutManager(new LinearLayoutManager(this));
        shopcarAdapter = new Submit_OrderAdapter(this);
        shopcarAdapter.setNewData(shopcards);
        s_recycle_view.setAdapter(shopcarAdapter);
        shopcarAdapter.notifyDataSetChanged();
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb1) {
                    deliver = "2";
                } else {
                    deliver = "1";
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            mname = data.getStringExtra("contact_name");
            mphone = data.getStringExtra("contact_phone");
            detaila = data.getStringExtra("address_detail");
            total = data.getStringExtra("address_total");
            address_id = data.getStringExtra("address_id");
            name.setText(mname);
            phone.setText(mphone);
            detail_address.setText(detaila);
            total_address.setText(total);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addoder_address:
                Intent intent = new Intent(SettlementActivity.this, ChooseAddressActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.sub_order:
                ispay(shopcards);
                break;
            case R.id.bk:
                SettlementActivity.this.finish();
                break;
        }
    }
    //是否付款的弹窗提示
    private void ispay(List<Shooping_carBean> list) {
        double totalPrice = 0;
        String userId = "";
        for (Shooping_carBean shooping_carBean : list) {
            userId = shooping_carBean.getUser_id();
            totalPrice = Double.valueOf(shooping_carBean.getGood_number()) * Double.valueOf(shooping_carBean.getGood_price())+Double.valueOf(shooping_carBean.getGoods_yajin()) + totalPrice;
        }
        AlertDialog.Builder dialog;
        dialog = new AlertDialog.Builder(SettlementActivity.this);
        dialog.setTitle("提示");
        dialog.setMessage(String.format("是否支付以下金额：￥%s",totalPrice));
        dialog.setCancelable(false);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                double totalPrice = 0;
                String userId = "";
                for (Shooping_carBean shooping_carBean : list) {
                    userId = shooping_carBean.getUser_id();
                    totalPrice = Double.valueOf(shooping_carBean.getGood_number()) * Double.valueOf(shooping_carBean.getGood_price())+Double.valueOf(shooping_carBean.getGoods_yajin()) + totalPrice;
                }
                pay(list, userId, totalPrice + "");
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                double totalPrice = 0;
                String userId = "";
                for (Shooping_carBean shooping_carBean : list) {
                    userId = shooping_carBean.getUser_id();
                    totalPrice = Double.valueOf(shooping_carBean.getGood_number()) * Double.valueOf(shooping_carBean.getGood_price())+Double.valueOf(shooping_carBean.getGoods_yajin())+ totalPrice;
                }
                pay1(list, userId, totalPrice + "");
            }
        });
        dialog.show();
    }

    //付款未成功
    private void pay1(List<Shooping_carBean> list, String userId, String totalPrice) {
        Map map = new HashMap();
        map.put("uerid", userId);
        map.put("price", "0");
        OkHttp.get(this, Constant.update_user_balance, map, new OkCallback<Result<String>>() {
            @Override
            public void onResponse(Result<String> response) {
                submit_order1(list);
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(SettlementActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //付款成功的扣款
    private void pay(List<Shooping_carBean> list, String userId, String totalPrice) {
        Map map = new HashMap();
        map.put("uerid", userId);
        map.put("price", totalPrice);
        OkHttp.get(this, Constant.update_user_balance, map, new OkCallback<Result<String>>() {
            @Override
            public void onResponse(Result<String> response) {

                add_message(totalPrice );
                submit_order(list);
            }

            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(SettlementActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void add_message( String totalPrice) {

        String userID = SharePrefrenceUtil.getObject(SettlementActivity.this, UsersBean.class).getUerid();//顾客id
        String title ="付款成功提醒";
        Map map = new HashMap();

        map.put("shop_id", "0");//商家id
        map.put("message_title", title);
        map.put("message_context", "支付￥"+totalPrice+"成功。");
        map.put("message_status", "1");//1给用户看；2给商家看
        map.put("user_id", userID);
        map.put("message_type","1");//消息类型：1系统消息；2用户消息
        Log.d("id",title);
        OkHttp.get(SettlementActivity.this, Constant.insert_message, map, new OkCallback<Result<String>>() {
            @Override
            public void onResponse(Result<String> response) {
               // Toast.makeText(SettlementActivity.this, "付款成功提醒。", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(SettlementActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    //付款成功生成的订单
    private void submit_order(List<Shooping_carBean> shooping_carBeans) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        String orderCode = System.currentTimeMillis() + "";
        for (Shooping_carBean shooping_carBean : shooping_carBeans) {
            Map map = new HashMap();
            map.put("order_rent_validation_time", simpleDateFormat.format(date));
            map.put("order_rent_finesh_time", beforeAfterDate(5));
            map.put("order_creat_time", simpleDateFormat.format(date));
            map.put("order_getgoods_time", beforeAfterDate(1));
            map.put("order_status", "2");
            map.put("order_remark", "");
            map.put("user_id", shooping_carBean.getUser_id());
            map.put("goods_id", shooping_carBean.getGoods_id());
            map.put("address", address_id);
            map.put("deliver", deliver);
            map.put("good_name", shooping_carBean.getGood_name());
            map.put("good_number", shooping_carBean.getGood_number());
            map.put("good_price", shooping_carBean.getGood_price());
            map.put("total_price", Double.valueOf(shooping_carBean.getGood_number()) * Double.valueOf(shooping_carBean.getGood_price()) +Double.valueOf(shooping_carBean.getGoods_yajin())+ "");
            map.put("good_img", shooping_carBean.getGood_img());
            map.put("shop_id", shooping_carBean.getShop_id());
            map.put("order_code", orderCode);
            map.put("goods_yajin",shooping_carBean.getGoods_yajin());
            map.put("evaluate_status","0");
            OkHttp.get(this, Constant.add_order, map, new OkCallback<Result<List<AddressBean>>>() {
                @Override
                public void onResponse(Result<List<AddressBean>> response) {
                    // addressAdapter.setNewData(response.getData());
                    Toast.makeText(SettlementActivity.this, "付款成功。", Toast.LENGTH_SHORT).show();
                    String good_id=shooping_carBean.getGoods_id();
                    String car_good_number=shooping_carBean.getGood_number();
                    change_goodnumber(good_id,car_good_number);
                    change_shopcarStatus(shooping_carBean.getId());
                    //
                    Intent intent = new Intent();
                    intent.putExtra("data_return","从付款成功返回购物车页面");
                    setResult(RESULT_OK,intent);
                    Log.d("从付款成功返回购物车页面","shopcar");
                    SettlementActivity.this.finish();
                }
                @Override
                public void onFailure(String state, String msg) {
                    Toast.makeText(SettlementActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    //改变购物车状态
    private void change_shopcarStatus(String shopacrId) {
        Map map = new HashMap();
        map.put("shopacrId", shopacrId);
        map.put("shop_car_status","1");//0未形成订单，1为已形成订单
        OkHttp.get(this, Constant.update_shopcarStatus, map, new OkCallback<Result<String>>() {
            @Override
            public void onResponse(Result<String> response) {
                Log.d("形成订单的购物车编号",shopacrId);
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(SettlementActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    //修改商品数量
    private void change_goodnumber(String good_id,String car_good_number) {
        Map map = new HashMap();
        map.put("good_id", good_id);
        map.put("car_good_number", car_good_number);
        OkHttp.get(this, Constant.update_goodNumber, map, new OkCallback<Result<String>>() {
            @Override
            public void onResponse(Result<String> response) {
                Log.d("顾客租走的数量",car_good_number);
            }

            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(SettlementActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    //取消付款生成的订单
    private void submit_order1(List<Shooping_carBean> shooping_carBeans) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        String orderCode = System.currentTimeMillis() + "";
        for (Shooping_carBean shooping_carBean : shooping_carBeans) {
            Map map = new HashMap();
            map.put("order_rent_validation_time", simpleDateFormat.format(date));
            map.put("order_rent_finesh_time", beforeAfterDate(5));
            map.put("order_creat_time", simpleDateFormat.format(date));
            map.put("order_getgoods_time", beforeAfterDate(1));
            map.put("order_status", "1");
            map.put("order_remark", "");
            map.put("user_id", shooping_carBean.getUser_id());
            map.put("goods_id", shooping_carBean.getGoods_id());
            map.put("address", address_id);
            map.put("deliver", deliver);
            map.put("good_name", shooping_carBean.getGood_name());
            map.put("good_number", shooping_carBean.getGood_number());
            map.put("good_price", shooping_carBean.getGood_price());
            map.put("total_price", Double.valueOf(shooping_carBean.getGood_number()) * Double.valueOf(shooping_carBean.getGood_price())+Double.valueOf(shooping_carBean.getGoods_yajin()) + "");
            map.put("good_img", shooping_carBean.getGood_img());
            map.put("shop_id", shooping_carBean.getShop_id());
            map.put("order_code", orderCode);
            map.put("goods_yajin",shooping_carBean.getGoods_yajin());
            map.put("evaluate_status","0");
            OkHttp.get(this, Constant.add_order, map, new OkCallback<Result<List<AddressBean>>>() {
                @Override
                public void onResponse(Result<List<AddressBean>> response) {
                    // addressAdapter.setNewData(response.getData());
                    Toast.makeText(SettlementActivity.this, "您已经取消付款，可以到待付款界面查看该订单。", Toast.LENGTH_SHORT).show();
                    String good_id=shooping_carBean.getGoods_id();
                    String car_good_number=shooping_carBean.getGood_number();
                    change_goodnumber(good_id,car_good_number);
                    change_shopcarStatus(shooping_carBean.getId());
                    //

                    Intent intent = new Intent();
                    intent.putExtra("data_return","从取消付款返回购物车页面");
                    setResult(RESULT_OK,intent);
                    Log.d("从取消付款返回购物车页面","shopcar");
                    SettlementActivity.this.finish();
                }
                @Override
                public void onFailure(String state, String msg) {
                    Toast.makeText(SettlementActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    //获取当前时间前后几天的时间
    public static String beforeAfterDate(int days) {

        long nowTime = System.currentTimeMillis();

        long changeTimes = days * 24L * 60 * 60 * 1000;

        return getStrTime(String.valueOf(nowTime + changeTimes), "yyyy-MM-dd HH:mm");

    }

    //时间戳转字符串
    public static String getStrTime(String timeStamp, String format) {

        String timeString = null;

        SimpleDateFormat sdf = new SimpleDateFormat(format);

        long l = Long.valueOf(timeStamp);

        timeString = sdf.format(new Date(l));//单位秒

        return timeString;

    }


}
