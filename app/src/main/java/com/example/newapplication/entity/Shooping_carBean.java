package com.example.newapplication.entity;

import java.io.Serializable;

public class Shooping_carBean implements Serializable {

	private boolean isSelect;

	private String id;
	private String user_id;
	private String shop_id;
	private String goods_id;
	private String good_name;
	private String good_number;
	private String good_price;
	private String good_img;
	private String shop_name;
	private String add_time;
	private String cancle_time;
	private String shop_car_status;
	private String goods_yajin;

	public String getGoods_yajin() {
		return goods_yajin;
	}

	public void setGoods_yajin(String goods_yajin) {
		this.goods_yajin = goods_yajin;
	}

	public boolean isSelect() {
		return isSelect;
	}

	public void setSelect(boolean select) {
		isSelect = select;
	}

	private String isChildSelected;


	public String getIsChildSelected() {
		return isChildSelected;
	}
	public void setIsChildSelected(String isChildSelected) {
		this.isChildSelected = isChildSelected;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getShop_id() {
		return shop_id;
	}
	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getGood_name() {
		return good_name;
	}
	public void setGood_name(String good_name) {
		this.good_name = good_name;
	}
	public String getGood_number() {
		return good_number;
	}
	public void setGood_number(String good_number) {
		this.good_number = good_number;
	}
	public String getGood_price() {
		return good_price;
	}
	public void setGood_price(String good_price) {
		this.good_price = good_price;
	}
	public String getGood_img() {
		return good_img;
	}
	public void setGood_img(String good_img) {
		this.good_img = good_img;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getCancle_time() {
		return cancle_time;
	}
	public void setCancle_time(String cancle_time) {
		this.cancle_time = cancle_time;
	}

	public String getShop_car_status() {
		return shop_car_status;
	}

	public void setShop_car_status(String shop_car_status) {
		this.shop_car_status = shop_car_status;
	}
}
