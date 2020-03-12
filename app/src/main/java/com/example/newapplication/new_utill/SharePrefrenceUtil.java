package com.example.newapplication.new_utill;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePrefrenceUtil {


    private SharePrefrenceUtil() {
    }

    private static final String KEY_CONFIG = "configs";

    private static SharedPreferences get(Context context) {
        return context.getApplicationContext().getSharedPreferences(KEY_CONFIG, Context.MODE_PRIVATE);
    }

    public static String getData(Context context, String key) {
        return getData(context, key, "");
    }

    public static String getData(Context context, String key, String defaultValue) {
        return get(context).getString(key, defaultValue);
    }

    public static void saveData(Context context, String key, String value) {
        get(context).edit()
                .putString(key, value)
                .apply();
    }

    public static void saveObject(Context context, Object value) {
        if (value == null) return;
        String name = value.getClass().getSimpleName();
        saveObject(context, name, value);
    }

    public static void saveObject(Context context, String key, Object value) {
        String json = GsonUtil.toJsonString(value);
        saveData(context, key, json);
    }

    public static void cleanObject(Context context, Class clazz) {
        if (clazz == null || context == null) return;
        String name = clazz.getSimpleName();
        saveData(context, name, null);
    }

    public static <T> T getObject(Context context, Class<T> clazz) {
        if (clazz == null) return null;
        return getObject(context, clazz.getSimpleName(), clazz);
    }

    public static <T> T getObject(Context context, String key, Class<T> clazz) {
        String json = getData(context, key);
        return GsonUtil.fromJson(json, clazz);
    }

    /**
     * 有记住密码的话保存在sp里
     *
     * @param context
     * @return
     */
    public static String getLoginPassword(Context context) {
        return getData(context, "login_password");
    }

    public static void saveLoginPassword(Context context, String password) {
        saveData(context, "login_password", password);
    }

    /**
     * 下次登录还显示的是上次登录的账号
     *
     * @param context
     * @return
     */
    public static String getLoginAccount(Context context) {
        return getData(context, "login_account");
    }

    public static void saveLoginAccount(Context context, String account) {
        saveData(context, "login_account", account);
    }

    public static String getToken(Context context) {
        return getData(context, "token");
    }

    public static void saveToken(Context context, String token) {
        saveData(context, "token", token);
    }


    /**
     * 用户协议是否打钩
     */
    public static void saveCheckType(Context context, String checkType) {
        saveData(context, "check_type", checkType);
    }

    /**
     * 如果登录时需要保存用户的信息，可以存一个对象进sp
     */
   /* public static String getName(Context context) {
        String result = "";
        LoginBean loginUser = getObject(context, LoginBean.class);
        if (loginUser != null) {
            result = loginUser.getRealname();
        }
        return result;
    }*/

    /**
     * 保存用户id
     *
     * @param context
     * @param user_id
     */
    public static void saveUserId(Context context, int user_id) {
        saveData(context, "user_id", user_id + "");
    }

    /**
     * 获取用户id
     *
     * @param context
     * @return
     */
    public static String getUserId(Context context) {
        return getData(context, "user_id");
    }

    /**
     * 保存用户手机号
     *
     * @param context
     * @param phone
     */
    public static void setPhone(Context context, String phone) {
        saveData(context, "phone", phone);
    }

    /**
     * 获取用户手机号
     *
     * @param context
     * @return
     */
    public static String getPhone(Context context) {
        return getData(context, "phone");
    }

    /**
     * 保存用户昵称
     *
     * @param context
     * @param nickName
     */
    public static void setNickName(Context context, String nickName) {
        saveData(context, "nickName", nickName);
    }

    /**
     * 获取用户昵称
     *
     * @param context
     * @return
     */
    public static String getNickName(Context context) {
        return getData(context, "nickName");
    }

    /**
     * 保存用户角色
     *
     * @param context
     * @param role
     */
    public static void setRole(Context context, String role) {
        saveData(context, "role", role);
    }

    /**
     * 获取用户角色
     *
     * @param context
     * @return
     */
    public static String getRole(Context context) {
        return getData(context, "role");
    }


}
