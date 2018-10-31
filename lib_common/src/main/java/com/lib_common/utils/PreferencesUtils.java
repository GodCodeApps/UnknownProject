package com.lib_common.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Base64;
import android.util.Log;

import com.lib_common.base.BaseApplication;
import com.lib_common.utils.constant.SharePreferencesConstant;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;

/**
 * SharePreferences工具类
 *
 * @author LiJiaJian
 * @date 2016/5/21 14:26
 */
public class PreferencesUtils {

    private static final String TAG = PreferencesUtils.class.getSimpleName();
    /**
     * SPF文件名
     */
    private static final String PREFERENCE_NAME = "common_spf";

    private PreferencesUtils() {
        throw new AssertionError();
    }

    /**
     * 储存string类型数据
     *
     * @param context 上下文
     * @param key     preference key
     * @param value   需要保存的值
     * @return 返回true表示保存成功
     */
    public static boolean putString(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    /**
     * 获取string类型数据
     *
     * @param context 上下文
     * @param key     preference key
     * @return 取值失败的默认值为null
     * @see #getString(Context, String, String)
     */
    public static String getString(Context context, String key) {
        return getString(context, key, null);
    }

    /**
     * 获取string类型数据
     *
     * @param context      上下文
     * @param key          preference key
     * @param defaultValue 取值失败时的默认值
     * @return 如果取值失败则返回默认值
     */
    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, defaultValue);
    }

    /**
     * 储存int类型数据
     *
     * @param context 上下文
     * @param key     preference key
     * @param value   需要保存的值
     * @return 返回true表示保存成功
     */
    public static boolean putInt(Context context, String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);

        return editor.commit();
    }

    /**
     * 获取int类型数据
     *
     * @param context 上下文
     * @param key     preference key
     * @return 取值失败的默认值为-1
     * @see #getInt(Context, String, int)
     */
    public static int getInt(Context context, String key) {
        return getInt(context, key, -1);
    }

    /**
     * 获取int类型数据
     *
     * @param context      上下文
     * @param key          preference key
     * @param defaultValue 取值失败时的默认值
     * @return 如果取值失败则返回默认值
     */
    public static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getInt(key, defaultValue);
    }

    /**
     * 储存long类型数据
     *
     * @param context 上下文
     * @param key     preference key
     * @param value   需要保存的值
     * @return 返回true表示保存成功
     */
    public static boolean putLong(Context context, String key, long value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    /**
     * 获取long类型数据
     *
     * @param context 上下文
     * @param key     preference key
     * @return 取值失败的默认值为-1
     * @see #getLong(Context, String, long)
     */
    public static long getLong(Context context, String key) {
        return getLong(context, key, -1);
    }

    /**
     * 获取long类型数据
     *
     * @param context      上下文
     * @param key          preference key
     * @param defaultValue 取值失败时的默认值
     * @return 如果取值失败则返回默认值
     */
    public static long getLong(Context context, String key, long defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getLong(key, defaultValue);
    }

    /**
     * 储存float类型数据
     *
     * @param context 上下文
     * @param key     preference key
     * @param value   需要保存的值
     * @return 返回true表示保存成功
     */
    public static boolean putFloat(Context context, String key, float value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    /**
     * 获取float类型数据
     *
     * @param context 上下文
     * @param key     preference key
     * @return 取值失败的默认值为-1
     * @see #getFloat(Context, String, float)
     */
    public static float getFloat(Context context, String key) {
        return getFloat(context, key, -1);
    }

    /**
     * 获取float类型数据
     *
     * @param context      上下文
     * @param key          preference key
     * @param defaultValue 取值失败时的默认值
     * @return 如果取值失败则返回默认值
     */
    public static float getFloat(Context context, String key, float defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getFloat(key, defaultValue);
    }

    /**
     * 储存boolean类型数据
     *
     * @param context 上下文
     * @param key     preference key
     * @param value   需要保存的值
     * @return 返回true表示保存成功
     */
    public static boolean putBoolean(Context context, String key, boolean value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * 获取boolean类型数据
     *
     * @param context 上下文
     * @param key     preference key
     * @return 取值失败的默认值为false
     * @see #getBoolean(Context, String, boolean)
     */
    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }

    /**
     * 获取boolean类型数据
     *
     * @param context      上下文
     * @param key          preference key
     * @param defaultValue 取值失败时的默认值
     * @return 如果取值失败则返回默认值
     */
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences settings = BaseApplication.getInstance().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getBoolean(key, defaultValue);
    }

    /**
     * 存储Set<String> 类型数据
     *
     * @param context 上下文
     * @param key     preference key
     * @param value   存储的值
     * @return
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static boolean putStringSet(Context context, String key, Set<String> value) {
        SharedPreferences settings = BaseApplication.getInstance().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putStringSet(key, value);
        return editor.commit();
    }


    /**
     * 获取Set<String> 类型数据
     *
     * @param context 上下文
     * @param key     键值
     * @return
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static Set<String> getStringSet(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getStringSet(key, null);
    }

    /**
     * 获取Set<String> 类型数据
     *
     * @param context      上下文
     * @param key          键值
     * @param defauleValue 获取失败是的默认值
     * @return
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static Set<String> getStringSet(Context context, String key, Set<String> defauleValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getStringSet(key, defauleValue);
    }


    /**
     * 储存object类型数据
     *
     * @param context 上下文
     * @param key     preference key
     * @param obj     需要保存的对象
     * @return 返回true表示保存成功
     */
    public static boolean putObject(Context context, String key, Object obj) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        // 创建字节输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            // 创建对象输出流，并封装字节流
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            // 将对象写入字节流
            oos.writeObject(obj);
            // 将字节流编码成base64的字符窜
            String base64Object = new String(Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT));
            editor.putString(key, base64Object);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
        return editor.commit();
    }

    /**
     * 获取object类型数据
     *
     * @param context 上下文
     * @param key     preference key
     * @return 取值失败的默认值为null
     * @see #getObject(Context, String, Object)
     */
    public static Object getObject(Context context, String key) {
        return getObject(context, key, null);
    }

    /**
     * 获取object类型数据
     *
     * @param context       上下文
     * @param key           preference key
     * @param defaultObject 取值失败时的默认值
     * @return 如果取值失败则返回默认值
     */
    public static Object getObject(Context context, String key, Object defaultObject) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        try {
            String base64Object = settings.getString(key, "");
            //读取字节
            byte[] base64 = Base64.decode(base64Object, Base64.DEFAULT);
            //封装到字节流
            ByteArrayInputStream bais = new ByteArrayInputStream(base64);
            //再次封装
            ObjectInputStream bis = new ObjectInputStream(bais);
            //读取对象
            return bis.readObject();
        } catch (Exception e) {
            Log.e(TAG, e != null && e.getMessage() != null ? e.getMessage() : "spf getObject error");
        }
        return defaultObject;
    }

    /**
     * 清除登录状态
     *
     * @param context
     */
    public static void clearForKey(Context context) {
        putObject(context, SharePreferencesConstant.LOGIN_OBJECT, null);
        putString(context, SharePreferencesConstant.LOGIN_TOKEN, null);
        putString(context, SharePreferencesConstant.LOGIN_UID, null);
        putString(context, SharePreferencesConstant.IM_TOKEN, null);
        putBoolean(context, SharePreferencesConstant.HAVE_NEW_ORDER, false);
        putInt(context, SharePreferencesConstant.CANCEL_ORDER_NUMBER, 0);
        putLong(context, SharePreferencesConstant.CANCEL_ORDER_TIME, 0);

    }

}
