package com.example.newapplication.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class LoopAdapter extends PagerAdapter {
    ImageView imageView;
    int realPosition;

    private List<Integer> sPices = null;

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

         realPosition = position % sPices.size();

       ImageView imageView =new ImageView(container.getContext());
        imageView.setImageResource(sPices.get(realPosition));
        //imageView.setBackgroundColor(sPices.get(position));
        //设置完颜色添加到容器里面
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        if (sPices != null){
            return Integer.MAX_VALUE;
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {


        return view == object;
    }

    public void setData(List<Integer> sColor) {

        this.sPices = sColor;
    }
    public  int  getDataRealSize(){

        if (sPices != null){
           return sPices.size();
        }
        return 0;
    }
}
