package com.example.newapplication.other;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.example.newapplication.entity.GoodBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;


/**
 * 购物车数据存储类
 */
public class CartProvider {
    public static final String JSON_CART = "json_cart";
    private Context context;
    //优化过的HashMap集合
    private SparseArray<GoodBean> datas;

    private static CartProvider cartProvider;

    private CartProvider(Context context) {
        this.context = context;
        datas = new SparseArray<>(100);
        listToSparse();
    }

    public static CartProvider getInstance() {

        if (cartProvider == null) {
            cartProvider = new CartProvider(MyAppliction.getContext());
        }

        return cartProvider;
    }

    private void listToSparse() {
        List<GoodBean> carts = getAllData();

        //放到sparseArry中
        if (carts != null && carts.size() > 0) {
            for (int i = 0; i < carts.size(); i++) {
                GoodBean goodBean = carts.get(i);
                datas.put(Integer.parseInt(goodBean.getGoods_price()), goodBean);
            }
        }
    }

    private List<GoodBean> parsesToList() {

        List<GoodBean> carts = new ArrayList<>();

        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                GoodBean shoppingCart = datas.valueAt(i);
                carts.add(shoppingCart);
            }
        }

        return carts;
    }

    public List<GoodBean> getAllData() {
        return getDataFromLocal();
    }

    //本地获取json数据，并且通过Gson解析成list列表数据
    public List<GoodBean> getDataFromLocal() {
        List<GoodBean> carts = new ArrayList<>();

        //从本地获取缓存数据
        String savaJson = CacheUtils.getString(context, JSON_CART);
        if (!TextUtils.isEmpty(savaJson)) {
            //把数据转换成列表
            carts = new Gson().fromJson(savaJson, new TypeToken<List<GoodBean>>() {
            }.getType());
        }

        return carts;
    }

    public void addData(GoodBean cart) {

        //添加数据
        GoodBean tempCart = datas.get(Integer.parseInt(cart.getGoods_id()));
        if (tempCart != null) {
            tempCart.setGoods_number(tempCart.getGoods_number() + cart.getGoods_number());
        } else {
            tempCart = cart;
            tempCart.setGoods_number("1");
        }

        datas.put(Integer.parseInt(tempCart.getGoods_id()), tempCart);

        //保存数据
        commit();
    }

    //保存数据
    private void commit() {
        //把parseArray转换成list
        List<GoodBean> carts = parsesToList();
        //把转换成String
        String json = new Gson().toJson(carts);

        // 保存
        CacheUtils.putString(context, JSON_CART, json);
    }


    public void deleteData(GoodBean cart) {

        //删除数据
        datas.delete(Integer.parseInt(cart.getGoods_id()));

        //保存数据
        commit();
    }

    public void updataData(GoodBean cart) {
        //修改数据
        datas.put(Integer.parseInt(cart.getGoods_id()), cart);

        //保存数据
        commit();
    }

    /**
     * 查找书籍
     * @param goods_bean
     * @return
     */
    public GoodBean findData(GoodBean goods_bean) {

        GoodBean goodsBean = datas.get(Integer.parseInt(goods_bean.getGoods_id()));
        if(goodsBean != null){
            return goods_bean;
        }

        return null;
    }
}
