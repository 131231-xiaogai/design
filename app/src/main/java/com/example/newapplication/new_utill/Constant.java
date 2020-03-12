package com.example.newapplication.new_utill;

public class Constant {

    public static String BASE_URL = "http://192.168.0.111:8088/clothService";//换网络时记得换ip

    /**
     * 查询所有商品
     */
    public static String select_all_good=BASE_URL+"/SelectAllGoodServlet";
    public static String select_all_user=BASE_URL+"/SelectAllUserServlet";
    public static String select_user_by_phone=BASE_URL+"/Select_user_by_phoneServlet";
    public static String select_user_by_id=BASE_URL+"/Select_user_by_idServlet";
    public static String login =BASE_URL+"/LoginServlet";
    public static String register =BASE_URL+"/RegisterServlet";




}
