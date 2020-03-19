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
    public static String publicgoods =BASE_URL+"/PublicgoodServlet";
    public static String select_good_by_id =BASE_URL+"/Select_good_by_idServlet";
    public static String insert_user_nickname =BASE_URL+"/Insert_user_nicknameServlet";
    public static String insert_user_sex =BASE_URL+"/Insert_user_sexServlet";
    public static String select_address_by_userid =BASE_URL+"/Select_address_by_useridServlet";
    public static String select_good_by_typeid =BASE_URL+"/Select_good_by_typeidServlet";
    public static String select_good_by_type_activityid =BASE_URL+"/Select_good_by_type_activityidServlet";
    public static String add_to_shopcar =BASE_URL+"/add_to_shopcarServlet";
    public static String update_user_address =BASE_URL+"/Update_user_addressServlet";
    public static String insert_user_address =BASE_URL+"/Insert_user_addressServlet";
    public static String select_shopcar_by_userid =BASE_URL+"/Select_shopcar_by_useridServlet";


}
