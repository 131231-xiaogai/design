package com.example.newapplication.other;

import android.content.Context;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newapplication.R;
import com.example.newapplication.entity.GoodBean;
import com.example.newapplication.new_utill.AddsubView;
import com.example.newapplication.new_utill.Constant;

import java.util.Iterator;
import java.util.List;

/**
 * 购物车适配器
 */
public class ShopCartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private CartProvider cartProvider;

    private Context mContext;
    private List<GoodBean> datas;
    private TextView tvShopcartTotal;
    private CheckBox checkboxAll;
    private CheckBox cb_all;

    public ShopCartAdapter(Context context, final List<GoodBean> datas, TextView tvShopcartTotal, CartProvider cartProvider, CheckBox checkboxAll, CheckBox cb_all) {
        this.mContext = context;
        this.datas = datas;
        this.tvShopcartTotal = tvShopcartTotal;
        this.cartProvider = cartProvider;
        this.checkboxAll = checkboxAll;
        this.cb_all = cb_all;

        //首次加载数据
        showTotalPrice();
        checkboxAll.setChecked(true);
        for (int i = 0; i < datas.size(); i++) {
            datas.get(i).setChildSelected(true);
        }

        showTotalPrice();

        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                GoodBean goodsBean = datas.get(position);
                goodsBean.setChildSelected(!goodsBean.isChildSelected());
                notifyItemChanged(position);
                checkAll();
                showTotalPrice();
            }
        });

        //设置全选点击事件
        checkboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = getCheckboxAll().isChecked();
                checkAll_none(checked);
                showTotalPrice();
            }
        });

        cb_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = getCb_all().isChecked();
                checkAll_none(checked);
                showTotalPrice();
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(mContext, R.layout.shop_list, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setData(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void checkAll_none(boolean checked) {
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                datas.get(i).setChildSelected(checked);
                checkboxAll.setChecked(checked);
                notifyItemChanged(i);
            }
        } else {
            checkboxAll.setChecked(false);
        }
    }

    public void deleteData() {

        if (datas != null && datas.size() > 0) {
            for (Iterator iterator = datas.iterator(); iterator.hasNext(); ) {

                GoodBean cart = (GoodBean) iterator.next();

                if (cart.isChildSelected()) {

                    //这行代码放在前面
                    int position = datas.indexOf(cart);
                    //1.删除本地缓存的
                    cartProvider.deleteData(cart);

                    //2.删除当前内存的
                    iterator.remove();

                    //3.刷新数据
                    notifyItemRemoved(position);
                }
            }
        }
    }

    public void checkAll() {

        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                if (!datas.get(i).isChildSelected()) {
                    checkboxAll.setChecked(false);
                    cb_all.setChecked(false);
                    return;
                } else {
                    checkboxAll.setChecked(true);
                    cb_all.setChecked(true);
                }
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox cbGov;
        private ImageView ivGov;
        private TextView tvDescGov;
        private TextView tvPriceGov;
        private AddsubView numberAddSubView;

        ViewHolder(View itemView) {
            super(itemView);
            cbGov = (CheckBox) itemView.findViewById(R.id.cb_gov);
            ivGov = (ImageView) itemView.findViewById(R.id.sc_good_image);
            tvDescGov = (TextView) itemView.findViewById(R.id.sc_goodname);
            tvPriceGov = (TextView) itemView.findViewById(R.id.sc_goood_price);
            numberAddSubView =  itemView.findViewById(R.id.sc_good_number);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClickListener(v, getLayoutPosition());
                    }
                }
            });
        }

        public void setData(final GoodBean goodBean) {
            cbGov.setChecked(goodBean.isChildSelected());

            Glide.with(mContext)
                    .load(Constant.BASE_URL + goodBean.getGood_img())
                    .into(ivGov);

            tvDescGov.setText(goodBean.getGoods_number());
            tvPriceGov.setText("￥" + goodBean.getGoods_price());

            //设置数字加减回调
            int mn =Integer.parseInt(goodBean.getGoods_number());
            numberAddSubView.setValue(mn);

            //-------------------------------------------
            //cartProvider = new CartProvider(mContext);

            numberAddSubView.setOnNumberChangeListener(new AddsubView.OnNumberChangeListener() {

                @Override
                public void onNumberChange(int value) {

                }

                public void add_number(View view, int value) {
                    String va=String.valueOf(value);
                    goodBean.setGoods_number(va);
                    cartProvider.updataData(goodBean);

                    showTotalPrice();
                }

                public void del_number(View view, int value) {
                    String va = String.valueOf(value);
                    goodBean.setGoods_number(va);
                    cartProvider.updataData(goodBean);

                    showTotalPrice();
                }
            });
        }
    }

    public void showTotalPrice() {
        tvShopcartTotal.setText(getTotalPrice() + "");
    }

    private double getTotalPrice() {
        double total = 0;

        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                GoodBean goodBean = datas.get(i);
                if (goodBean.isChildSelected())
                    total += Double.parseDouble(goodBean.getGoods_price()) * Double.parseDouble(goodBean.getGoods_number() + "");
            }
        }

        return total;
    }

    //回调点击事件的监听
    private OnItemClickListener onItemClickListener;

    interface OnItemClickListener {
        void onItemClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public CheckBox getCb_all() {
        return cb_all;
    }

    public void setCb_all(CheckBox cb_all) {
        this.cb_all = cb_all;
    }

    public CheckBox getCheckboxAll() {
        return checkboxAll;
    }

    public void setCheckboxAll(CheckBox checkboxAll) {
        this.checkboxAll = checkboxAll;
    }
}
