package com.example.newapplication.Adapter;

        import android.content.Context;
        import android.widget.CheckBox;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.bumptech.glide.Glide;
        import com.example.newapplication.R;
        import com.example.newapplication.entity.GoodBean;
        import com.example.newapplication.entity.Shooping_carBean;
        import com.example.newapplication.new_utill.AddsubView;
        import com.example.newapplication.viewhandle.RecyclerViewHolder;

public class ShopcarAdapter extends BaseRecyclerViewAdapter<Shooping_carBean, RecyclerViewHolder> {
    public ShopcarAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(RecyclerViewHolder holder, Shooping_carBean data, int position, int viewType) {
        ImageView imageView = holder.getView(R.id.sc_good_image);
        Glide.with(mContext).load(data.getGood_img()).into(imageView);

//        TextView shop_ame = holder.getView(R.id.ms_shop_ame);
//        shop_ame.setText(data.getShop_name());

        TextView price = holder.getView(R.id.sc_goood_price);
        price.setText(data.getGood_price());

        TextView goodname = holder.getView(R.id.sc_goodname);
        goodname.setText(data.getGood_name());

        CheckBox cb_gov =holder.getView(R.id.cb_gov);

        AddsubView good_number = holder.getView(R.id.sc_good_number);
        int mn =Integer.parseInt(data.getGood_number());
        good_number.setValue(mn);
    }


    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.shop_list;
    }

    @Override
    protected int getViewType(int position, Shooping_carBean data) {
        return 0;
    }


}

