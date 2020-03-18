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
        // imageView.setIm(data.getGood_img());
        Glide.with(mContext).load(data.getGood_img()).into(imageView);

        TextView textView = holder.getView(R.id.type_good_name);
        textView.setText(data.getGoods_name());

        TextView price = holder.getView(R.id.type_good__price);
        price.setText(data.getGoods_price());

        TextView goodid = holder.getView(R.id.type_goodid);
        goodid.setText(data.getGoods_id());
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
