package com.example.newapplication.Adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newapplication.R;
import com.example.newapplication.entity.GoodBean;
import com.example.newapplication.viewhandle.RecyclerViewHolder;

public class TypeListAdapter extends BaseRecyclerViewAdapter<GoodBean, RecyclerViewHolder> {
    public TypeListAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerViewHolder holder, GoodBean data, int position, int viewType) {


        ImageView imageView = holder.getView(R.id.type_good_image);
        Glide.with(mContext).load(data.getGood_img()).into(imageView);

        TextView textView = holder.getView(R.id.type_good_name);
        textView.setText(data.getGoods_name());

        TextView price = holder.getView(R.id.type_good__price);
        price.setText("每件租金：￥"+data.getGoods_price()+"/ 押金：￥"+data.getGoods_yajin()+"/ 商品尺码："+data.getSize());

        TextView goodid = holder.getView(R.id.type_goodid);
        goodid.setText(data.getShop_name());
    }


    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.l_typelise_item;
    }

    @Override
    protected int getViewType(int position, GoodBean data) {
        return 0;
    }
}
