package com.example.newapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.newapplication.Adapter.LoopAdapter;
import com.example.newapplication.newpage.MyViewPager;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener, MyViewPager.OnViewPageTouchListener, ViewPager.OnPageChangeListener {
    ImageButton h_btn_list, h_btn_date, h_btn_shop, h_btn_me;
    MyViewPager loopPagers;
    LoopAdapter loopAdapter;
    private static List<Integer> sPics = new ArrayList<>();

    static {
        sPics.add(R.drawable.image1);
        sPics.add(R.drawable.image2);
        sPics.add(R.drawable.image3);
        sPics.add(R.drawable.image4);
        sPics.add(R.drawable.image5);
    }

    private Handler handler;//
    private boolean mIsTouch = false;

    LinearLayout pointcontainer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        initView();
//        Random random = new Random();
//        //准备轮播图数据
//        for(int i=1;i < 5;i++) {
//            sColor.add(Color.argb(random.nextInt(255),random.nextInt(255),random.nextInt(255),random.nextInt(255)));
//        }
//        给轮播图适配器设置数据
//        loopAdapter.setData(sColor);
//        loopAdapter.notifyDataSetChanged();
        handler = new Handler();

        //底部导航栏
        h_btn_list = (ImageButton) findViewById(R.id.h_button_list);
        h_btn_list.setOnClickListener(this);//添加监听器
        h_btn_date = (ImageButton) findViewById(R.id.h_button_date);
        h_btn_date.setOnClickListener(this);
        h_btn_shop = (ImageButton) findViewById(R.id.h_button_shopcar);
        h_btn_shop.setOnClickListener(this);
        h_btn_me = (ImageButton) findViewById(R.id.h_button_me);
        h_btn_me.setOnClickListener(this);
    }

    private void initView() {
        //找到轮播控件
        loopPagers = (MyViewPager) this.findViewById(R.id.looper_pagers);

        //设置适配器
        loopAdapter = new LoopAdapter();
        loopAdapter.setData(sPics);
        loopPagers.setAdapter(loopAdapter);
        loopPagers.addOnPageChangeListener(this);


        loopPagers.setOnViewPageTouchListener(this);
        //
        pointcontainer = (LinearLayout)this.findViewById(R.id.points_container);
        //添加点
        insertpoint();
        loopPagers.setCurrentItem(loopAdapter.getDataRealSize()* 100,false);

    }

    private void insertpoint() {
        for (int i=0;i< sPics.size();i++) {
            View point = new View(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(40,40);
            point.setLayoutParams(layoutParams);
            layoutParams.leftMargin =20;
            point.setBackground(getResources().getDrawable(R.drawable.shape_point_normal));
            pointcontainer.addView(point);

        }

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        //
        handler.post(looperTask);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        handler.removeCallbacks(looperTask);
    }

    private Runnable looperTask = new Runnable() {
        @Override
        public void run() {
            //
            if (!mIsTouch){
                int currentItem;
                currentItem = loopPagers.getCurrentItem();
                loopPagers.setCurrentItem(++currentItem, true);
            }
            handler.postDelayed(this, 3000);
        }
    };


@Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.h_button_list:
                    startActivity(new Intent(HomeActivity.this, ListActivity.class));
                    break;
                case R.id.h_button_me:
                    startActivity(new Intent(HomeActivity.this, MeActivity.class));
                    break;
                case R.id.h_button_shopcar:
                    startActivity(new Intent(HomeActivity.this, ShopcarActivity.class));
                    break;
                case R.id.h_button_date:
                    startActivity(new Intent(HomeActivity.this, DateActivity.class));

            }

        }

    @Override
    public void onPageTouch(boolean isTouch) {
        mIsTouch = isTouch;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    //
        int realPosition;
        if (loopAdapter.getDataRealSize()!=0 ) {
            realPosition = position % loopAdapter.getDataRealSize();
        }else {
            realPosition = 0;
        }
        setSelectPoint(realPosition);
    }

    private void setSelectPoint(int realPosition) {
        for (int i = 0; i < pointcontainer.getChildCount(); i++) {
           View postion;
            postion = pointcontainer.getChildAt(i);
            if (i!=realPosition){
               //
               postion.setBackgroundResource(R.drawable.shape_point_normal);
           }else {
               postion.setBackgroundResource(R.drawable.shape_point_select);
           }

        }

    }



    @Override
    public void onPageScrollStateChanged(int state) {

    }
}



