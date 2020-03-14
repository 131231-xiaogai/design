package com.example.newapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.example.newapplication.Adapter.GoodAdapter;
import com.example.newapplication.Adapter.LoopAdapter;
import com.example.newapplication.Adapter.PhotoAdapter;
import com.example.newapplication.Adapter.Photo_Rec_Adapter;
import com.example.newapplication.entity.GoodBean;
import com.example.newapplication.entity.Photo;
import com.example.newapplication.home.ItemDetailActivity;
import com.example.newapplication.inteface.OnItemClickListener;
import com.example.newapplication.new_utill.Constant;
import com.example.newapplication.new_utill.OkCallback;
import com.example.newapplication.new_utill.OkHttp;
import com.example.newapplication.new_utill.Result;
import com.example.newapplication.newpage.MyViewPager;
import com.example.newapplication.newpage.Notice;
import com.example.newapplication.newpage.Phone_help;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, MyViewPager.OnViewPageTouchListener, ViewPager.OnPageChangeListener {
    ImageButton btn_list, btn_date, btn_shop, btn_me;
    MyViewPager loopPagers;
    LoopAdapter loopAdapter;
    RecyclerView recyclerView;
    ImageView btn_notice;
    private List<Photo> photoList = new ArrayList<>();
    //private List<Photo> mphotoList = new ArrayList<>();
    private static List<Integer> sPics = new ArrayList<>();
    static {
        sPics.add(R.drawable.image6);
        sPics.add(R.drawable.image7);
        sPics.add(R.drawable.image8);
        sPics.add(R.drawable.image9);
        sPics.add(R.drawable.image10);
    }
    private Handler handler;//
    private boolean mIsTouch = false;
    LinearLayout pointcontainer;
    private GoodAdapter goodAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        initView();
        handler = new Handler();
        btn_notice = (ImageView) findViewById(R.id.btn_notice);
         btn_notice.setOnClickListener(this);
        //list
        initPhoto();
        recyclerView = (RecyclerView) findViewById(R.id.h_recycle_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        goodAdapter = new GoodAdapter(this);
//      Photo_Rec_Adapter rec_adapter = new Photo_Rec_Adapter(photoList);
        recyclerView.setAdapter(goodAdapter);
        goodAdapter.setOnItemClickListener(new OnItemClickListener<GoodBean>() {
            @Override
            public void onItemClick(RecyclerViewHolder viewHolder, GoodBean data, int position) {
                Toast.makeText(HomeActivity.this, data.getGoods_id(), Toast.LENGTH_SHORT).show();
                String da = data.getGoods_id();
                Intent intent = new Intent(HomeActivity.this, ItemDetailActivity.class);
                intent.putExtra("hgoodid", da);
                startActivity(intent);
            }
        });
        loadData();
        //底部导航栏
        btn_list = (ImageButton) findViewById(R.id.b_list);
        btn_list.setOnClickListener(this);//添加监听器
        btn_date = (ImageButton) findViewById(R.id.b_date);
        btn_date.setOnClickListener(this);
        btn_shop = (ImageButton) findViewById(R.id.b_shopcar);
        btn_shop.setOnClickListener(this);
        btn_me = (ImageButton) findViewById(R.id.b_me);
        btn_me.setOnClickListener(this);

    }

    //列表
    private void initPhoto() {
        for (int i = 0; i < 2; i++) {
            Photo home = new Photo(
                    getRandomLengthName("home"), R.drawable.image6);
            photoList.add(home);
            Photo list = new Photo(
                    getRandomLengthName("list"), R.drawable.image7);
            photoList.add(list);
            Photo date = new Photo(
                    getRandomLengthName("date"), R.drawable.image8);
            photoList.add(date);
            Photo shopcar = new Photo(
                    getRandomLengthName("shopcar"), R.drawable.image9);
            photoList.add(shopcar);
            Photo me = new Photo(
                    getRandomLengthName("me"), R.drawable.image10);
            photoList.add(me);
        }
    }

    //随机名字
    private String getRandomLengthName(String home) {
        Random random = new Random();
        int length = random.nextInt(10) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(home);
        }
        return builder.toString();
    }

    //轮播图部分
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
        pointcontainer = (LinearLayout) this.findViewById(R.id.points_container);
        //添加点
        insertpoint();
        loopPagers.setCurrentItem(loopAdapter.getDataRealSize() * 100, false);
    }

    private void insertpoint() {
        for (int i = 0; i < sPics.size(); i++) {
            View point = new View(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(40, 40);
            point.setLayoutParams(layoutParams);
            layoutParams.leftMargin = 20;
            point.setBackground(getResources().getDrawable(R.drawable.shape_point_normal));
            pointcontainer.addView(point);
        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
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
            if (!mIsTouch) {
                int currentItem;
                currentItem = loopPagers.getCurrentItem();
                loopPagers.setCurrentItem(++currentItem, true);
            }
            handler.postDelayed(this, 3000);
        }
    };

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
        if (loopAdapter.getDataRealSize() != 0) {
            realPosition = position % loopAdapter.getDataRealSize();
        } else {
            realPosition = 0;
        }
        setSelectPoint(realPosition);
    }

    private void setSelectPoint(int realPosition) {
        for (int i = 0; i < pointcontainer.getChildCount(); i++) {
            View postion;
            postion = pointcontainer.getChildAt(i);
            if (i != realPosition) {
                //
                postion.setBackgroundResource(R.drawable.shape_point_normal);
            } else {
                postion.setBackgroundResource(R.drawable.shape_point_select);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void finish_reback(View v) {
        HomeActivity.this.finish();
    }

    //底部导航栏
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b_list:
                startActivity(new Intent(HomeActivity.this, ListActivity.class));
                break;
            case R.id.b_me:
                startActivity(new Intent(HomeActivity.this, MeActivity.class));
                break;
            case R.id.b_shopcar:
                startActivity(new Intent(HomeActivity.this, ShopcarActivity.class));
                break;
            case R.id.b_date:
                startActivity(new Intent(HomeActivity.this, DateActivity.class));
                break;
            case R.id.btn_notice:
                startActivity(new Intent(HomeActivity.this, Notice.class));
                break;
        }
    }


    private void loadData() {
        Map map = new HashMap();
        OkHttp.get(this, Constant.select_all_good, map, new OkCallback<Result<List<GoodBean>>>() {
            @Override
            public void onResponse(Result<List<GoodBean>> response) {
                goodAdapter.setNewData(response.getData());
            }
            @Override
            public void onFailure(String state, String msg) {
                Toast.makeText(HomeActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}



