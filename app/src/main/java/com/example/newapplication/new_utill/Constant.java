package com.example.newapplication.new_utill;

public class Constant {

//    public static String BASE_URL = "http://192.168.0.105:8088/clothService";//换网络时记得换ip
    public static String BASE_URL = "http://192.168.0.108:8088/clothService";//换网络时记得换ip

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
    public static String select_shop_by_userid =BASE_URL+"/Select_shop_by_useridServlet";
    public static String regiest_shop =BASE_URL+"/Regiest_shopServlet";
    public static String delete_user_address =BASE_URL+"/Delete_user_addressServlet";
    public static String select_good_by_shopid =BASE_URL+"/Select_good_by_shopIdServlet";
    public static String deleted_goods_by_goodsid =BASE_URL+"/Deleted_goods_by_goodsIdServlet";
    public static String update_good_by_goodId =BASE_URL+"/Update_good_by_goodIdServlet";
    public static String deleted_user_byId =BASE_URL+"/Delete_user_by_userIDServlet";
    /**
     * 修改购物车数量
     */
    public static String updateShopCount=BASE_URL+"/UpdateShopCardCountServlet";
    public static String select_event =BASE_URL+"/Select_eventServlet";
    public static String select_event_byUserID =BASE_URL+"/Select_event_byUserIDServlet";
    public static String update_event_byEventID =BASE_URL+"/Update_event_byEventIDServlet";
    public static String deletede_event_byEventID =BASE_URL+"/Deletede_event_byEventIDServlet";
    public static String add_event =BASE_URL+"/Add_event";
    public static String select_order_by_UseridAndOrderStstus =BASE_URL+"/select_order_by_UseridAndOrderStstus";
    public static String select_order_by_ShopidAndOrderStstus =BASE_URL+"/select_order_by_ShopidAndOrderStstus";
    public static String updateOrder_status =BASE_URL+"/UpdateOrder_statusServlet";
    public static String add_order =BASE_URL+"/Add_order";
    public static String insert_message =BASE_URL+"/Insert_message";
    public static String update_user_balance =BASE_URL+"/Update_user_balance";
    public static String select_message =BASE_URL+"/Select_message";
    public static String select_message_byshopid =BASE_URL+"/Select_message_byshopid";
    public static String selece_order_totalprice =BASE_URL+"/Selece_order_totalprice";
    public static String select_user_byShopId =BASE_URL+"/Select_user_byShopId";
    public static String update_shop_balance =BASE_URL+"/Update_shop_balance";

    public static String update_goodNumber =BASE_URL+"/Update_goodNumber";
    public static String update_goodNumber_add =BASE_URL+"/Update_goodNumber_add";
    public static String update_shopcarStatus =BASE_URL+"/Update_shopcarStatus";

    //dody_data
    public static String insert_user_bodyData =BASE_URL+"/Insert_user_bodyData";
    public static String select_Bodydata_byuserId =BASE_URL+"/Select_Bodydata_byuserId";
    public static String update_user_bodyData =BASE_URL+"/Update_user_bodyData";
    public static String update_user_role =BASE_URL+"/Update_user_role";

    //add_order_p_content
    public static String add_order_p_content =BASE_URL+"/Add_order_p_content";
    //select_order_by_UseridAndOrderStstus_evaluate_status
    public static String select_order_by_UseridAndOrderStstus_evaluate_status =BASE_URL+"/Select_order_by_UseridAndOrderStstus_evaluate_status";
    //updata_Order_evaluateStatus
    public static String updata_Order_evaluateStatus =BASE_URL+"/Updata_Order_evaluateStatus";
    //selece_shop_evaluate
    public static String selece_shop_evaluate =BASE_URL+"/Selece_shop_evaluate";
    //select_allOrder_byUserid
    public static String select_allOrder_byUserid =BASE_URL+"/Select_allOrder_byUserid";
    //select_allOrder_byShopId
    public static String select_allOrder_byShopId =BASE_URL+"/Select_allOrder_byShopId";
    //select_address_by_addressId
    public static String select_address_by_addressId =BASE_URL+"/Select_address_by_addressId";
    //select_evaluate_by_orderid
    public static String select_evaluate_by_orderid =BASE_URL+"/Select_evaluate_by_orderid";
}
