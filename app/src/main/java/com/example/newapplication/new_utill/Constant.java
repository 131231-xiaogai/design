package com.example.psychologicalcounseling.okhttp;

public class Constant {

    public static String BASE_URL = "http://192.168.0.103:8088/psychologicalCounseling_war_exploded";//换网络时记得换ip
//    public static String BASE_URL = "http://192.168.61.104:8088/psychologicalCounseling_war_exploded";//换网络时记得换ip
    /**
     * 验证码登录
     *
     * @param phone
     * @param code
     */
    public static String login_by_code = BASE_URL + "/LoginByCodeServlet";
    /**
     * 生成验证码
     *
     * @param phone
     */
    public static String create_code = BASE_URL + "/CreateCodeServlet";
    /**
     * 密码登录
     *
     * @param phone
     * @param password
     */
    public static String login_by_password = BASE_URL + "/LoginByPasswordServlet";
    /**
     * 密码注册
     *
     * @param phone
     * @param password
     */
    public static String regist_by_password = BASE_URL + "/RegistByPasswordServlet";
    /**
     * 验证码注册
     *
     * @param phone
     * @param code
     */
    public static String regist_by_code = BASE_URL + "/RegistByCodeServlet";

    /**
     * 查询不同分类的测试集
     *
     * @param 分类
     */
    public static String test_select_servlet = BASE_URL + "/TestSelectServlet";

    /**
     * 查询某一个ID的测试
     */
    public static String test_select_by_id = BASE_URL + "/TestSelectByIdServlet";
    /**
     * 查询一个用户的反馈
     */
    public static String select_feedback_by_user_id = BASE_URL + "/SelectFeedbackByUserIdServlet";
    /**
     * 登录成功后保存下来查询到的用户到sp
     */
    public static String select_user_by_phone = BASE_URL + "/SelectUserByPhoneServlet";

    //=============壹心理测试题接口================================
    /**
     * 壹心理各种分类心理测试集接口api
     * 点进去的详情自己带过去
     * 如 情感 https://cpapi.xinli001.com/cp/mservice/category/getCategoryScales?category=qg&pageVo.page=1&sortType=TIME&pageVo.pageSize=10&channelId=501
     * category ff:付费 qg:情感 xg：性格 以拼音首字母类推
     * pageVo.page 页码
     * sortType:按什么排序
     * pageVo.pageSize：一页几条记录
     * channelId:不懂是什么字段，但是好像固定是501
     */
    public static String get_category_scales = "https://cpapi.xinli001.com/cp/mservice/category/getCategoryScales";
    /**
     * 测试集的问题
     * channelId：501（可能得从列表页传过来）
     * id:从列表页带过来
     * userAwaitTestScaleId：0
     * appCode：1
     */
    public static String get_fun_question_options = "https://qu.xinli001.com/lingxi/fun/getFunQuestionsOptions";
    /**
     * 解析测试json数据，保存到数据库
     */
    public static String create_data = BASE_URL + "/CreateTestServlet";
    /**
     * 解析问题json数据，保存到数据库
     */
    public static String create_question = BASE_URL + "/CreateQuestionServlet";

    /**
     * 查询所有问题，根据测试题id ，包括选项
     */
    public static String select_all_question = BASE_URL + "/SelectQuestionServlet";

    //=============壹心理测试题接口=====================================


    //----------------咨询师、功能室begin-----------------------
    /**
     * 查询所有的咨询师
     */
    public static String select_all_counselor = BASE_URL + "/SelectAllCounselorServlet";

    /**
     * 根据id查询咨询师的个人信息
     */
    public static String select_counselor_by_id = BASE_URL + "/SelectCounselorByIdServlet";

    /**
     * 将预约时间保存到数据库
     */
    public static String add_time = BASE_URL + "/AddTimeServlet";

    /**
     * 预约咨询师
     */
    public static String publish_order_counselor = BASE_URL + "/PublishOrderCounselorServlet";

    /**
     * 按类型查找功能室
     */
    public static String select_room = BASE_URL + "/SelectRoomServlet";
    /**
     * 查询咨询师的日程（被预约的个人的订单），根据不同的状态
     */
    public static String select_all_order_by_counselor_id = BASE_URL + "/SelectAllOrderByCounselorIdServlet";
    /**
     * 查询咨询师的日程（被预约的功能室的订单），根据不同的状态
     */
    public static String select_all_room_order_by_counselor_id = BASE_URL + "/SelectAllRoomOrderByCounselorIdServlet";
    /**
     * 查询预约者下的预约订单，根据不同的状态
     */
    public static String select_all_order_by_user_id = BASE_URL + "/SelectAllOrderByUserIdServlet";
    /**
     * 查询预约者下的功能室预约订单，根据不同的状态
     */
    public static String select_all_room_order_by_user_id = BASE_URL + "/SelectAllRoomOrderByUserIdServlet";
    /**
     * 检查余额够不够支付，不够提醒去充值
     */
    public static String check_pay = BASE_URL + "/CheckPayServlet";
    /**
     * 检查余额够不够支付，不够提醒去充值
     */
    public static String check_room_pay = BASE_URL + "/CheckRoomPayServlet";

    /**
     * 支付
     */
    public static String pay = BASE_URL + "/PayServlet";
    /**
     * 预约功能室 支付
     */
    public static String room_pay = BASE_URL + "/RoomPayServlet";
    /**
     * 删除订单
     */
    public static String delete_order = BASE_URL + "/DeleteOrderServlet";
    /**
     * 查看预约咨询师的订单详情
     */
    public static String select_order_by_order_id = BASE_URL + "/SelectOrderByOrderIdServlet";
    /**
     * 查看预约功能室的订单详情
     */
    public static String select_room_order_by_order_id = BASE_URL + "/SelectRoomOrderByOrderIdServlet";
    /**
     * 取消订单
     */
    public static String cancel_order = BASE_URL + "/CancelOrderServlet";
    /**
     * 完成咨询
     */
    public static String finish_order = BASE_URL + "/FinishOrderServlet";
    /**
     * 新建功能室
     */
    public static String publish_room = BASE_URL + "/PublishRoomServlet";
    /**
     * 预约功能室
     */
    public static String order_room = BASE_URL + "/OrderRoomServlet";

    /**
     * 查看结果报告
     */
    public static String result_detail = BASE_URL + "/ResultDetailServlet";
    /**
     * 填写报告
     */
    public static String add_result = BASE_URL + "/AddResultServlet";


    //----------------咨询师、功能室end-----------------------


    //------------------反馈begin---------------------------
    /**
     * 提交反馈/回复
     */
    public static String publish_feedback = BASE_URL + "/PublishFeedBackServlet";

    /**
     * 回复反馈聊天记录
     */
    public static String select_chat_feedback = BASE_URL + "/SelectChatFeedbackServlet";

    /**
     * 查询管理员收到的反馈（不含回复的）
     */
    public static String getSelect_feedback_by_adminuser_id = BASE_URL + "/SelectFeedbackByAdminUserIdServlet";

    //-----------------反馈end------------------------------
    //------------------我的钱包begin------------------------
    /**
     * 充值
     */
    public static String update_balance = BASE_URL + "/UpdateBalanceServlet";
    /**
     * 查询余额
     */
    public static String select_balance = BASE_URL + "/SelectBalanceServlet";
    //------------------我的钱包end------------------------

    /**
     * 查询前十个咨询师，根据咨询人数排序
     */
    public static String select_top_ten_counselor = BASE_URL + "/SelectTopTenServlet";
    /**
     * 编辑用户资料
     */
    public static String update_user_info = BASE_URL + "/UpdateUserInfoServlet";

    //------------------时间begin--------------------------------
    /**
     * 查询某个咨询师的某天的时间是否可预约
     * 从订单表里查，有订单的就是不可预约，没有订单就是可预约
     */
    public static String select_time_by_counselor_id = BASE_URL + "/SelectTimeByCounselorIdServlet";
    /**
     * 查询某个功能室的某天的时间是否可预约
     * 从订单表里查，有订单的就是不可预约，没有订单就是可预约
     */
    public static String select_time_by_room_id = BASE_URL + "/SelectTimeByRoomIdServlet";
    //----------------时间end-----------------------------------
    //----------------公告begin-------------------------------
    /**
     * 查询公告/通知
     */
    public static String select_announce = BASE_URL + "/SelectAnnounceServlet";
    /**
     * 发布新公告/通知
     */
    public static String add_announce = BASE_URL + "/AddAnnounceServlet";
    /**
     * 删除公告 没有删除通知的功能 而且公告也只有管理员能删除
     */
    public static String delAnnounce = BASE_URL + "/DelAnnounceServlet";
    //-----------------公告end-----------------------------
}
