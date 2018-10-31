package com.lib_common.utils;

import android.app.Activity;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;

import java.io.Serializable;

/**
 * @author ZhongWeiZhi
 * @date 2018/9/13  10:15
 * @description
 */
public class PageJumpUtils {
    /**
     * 简单跳转
     * @param pagePath
     */
    public static void jumpPage(String pagePath){
        ARouter.getInstance().build(pagePath).navigation();
    }

    /**
     * 跳转带参数bundle
     * @param pagePath
     * @param bundle
     * 取值时  getIntent()getBundleExtra("bundle")
     */
    public static void jumpPage(String pagePath, Bundle bundle){
        ARouter.getInstance().build(pagePath).withBundle("bundle",bundle).navigation();
    }
    /**
     * 跳转带参数bundle
     * @param pagePath
     * @param requestCode
     */
    public static void jumpPageForResult(Activity context,String pagePath, int requestCode){
        ARouter.getInstance().build(pagePath).navigation(context,requestCode);
    }
    /**
     * 跳转带参数bundle
     * @param pagePath
     * @param requestCode
     *  取值时  getIntent()getBundleExtra("bundle")
     */
    public static void jumpPageForResult(Activity context,String pagePath, Bundle bundle, int requestCode){
        ARouter.getInstance().build(pagePath).withBundle("bundle",bundle).navigation(context,requestCode);
    }
    /**
     * 跳转带参数bundle
     * @param pagePath
     * @param content
     * @param key
     *  取值时  getIntent().getExtras().getString("key")
     */
    public static void jumpPageWithString(String pagePath, String content,String key){
        ARouter.getInstance().build(pagePath).withString(key,content).navigation();
    }
    /**
     * 跳转带参数bundle
     * @param pagePath
     * @param content
     * @param key
     *  取值时  getIntent().getExtras().getString("key")
     */
    public static void jumpPageWithInt(String pagePath, int content,String key){
        ARouter.getInstance().build(pagePath).withInt(key,content).navigation();
    }
    /**
     * 跳转带参数bundle
     * @param pagePath
     * @param content  对象
     * @param key
     *  取值时  getIntent().getExtras().getString("key")
     */
    public static void jumpPageWithObject(String pagePath, Object content,String key){
        ARouter.getInstance().build(pagePath).withObject(key,content).navigation();
    }
    /**
     * 跳转带参数bundle
     * @param pagePath
     * @param content
     * @param key
     *  取值时  getIntent().getExtras().getString("key")
     */
    public static void jumpPageWithSerializable(String pagePath, Serializable content, String key){
        ARouter.getInstance().build(pagePath).withSerializable(key,content).navigation();
    }
    /**
     * 跳转带参数bundle
     * @param pagePath
     * @param content
     * @param key
     */
    public static void jumpPageWithStringForResult(Activity context, String pagePath, String content, String key, int requestCode){
        ARouter.getInstance().build(pagePath).withString(key,content).navigation(context,requestCode);
    }

    /**
     * 简单跳转(不拦截)
     * @param pagePath
     */
    public static void jumpPageNotInterceptor(String pagePath){
        ARouter.getInstance().build(pagePath).greenChannel().navigation();
    }

    /**
     * 跳转带参数bundle(不拦截)
     * @param pagePath
     * @param bundle
     * 取值时  getIntent()getBundleExtra("bundle")
     */
    public static void jumpPageNotInterceptor(String pagePath, Bundle bundle){
        ARouter.getInstance().build(pagePath).withBundle("bundle",bundle).greenChannel().navigation();
    }
    /**
     * 跳转带参数bundle(不拦截)
     * @param pagePath
     * @param requestCode
     */
    public static void jumpPageForResultNotInterceptor(Activity context, String pagePath, int requestCode){
        ARouter.getInstance().build(pagePath).greenChannel().navigation(context,requestCode);
    }
    /**
     * 跳转带参数bundle(不拦截)
     * @param pagePath
     * @param requestCode
     *  取值时  getIntent()getBundleExtra("bundle")
     */
    public static void jumpPageForResultNotInterceptor(Activity context, String pagePath, Bundle bundle, int requestCode){
        ARouter.getInstance().build(pagePath).withBundle("bundle",bundle).greenChannel().navigation(context,requestCode);
    }
    /**
     * 跳转带参数bundle(不拦截)
     * @param pagePath
     * @param content
     * @param key
     *  取值时  getIntent().getExtras().getString("key")
     */
    public static void jumpPageWithStringNotInterceptor(String pagePath, String content,String key){
        ARouter.getInstance().build(pagePath).withString(key,content).greenChannel().navigation();
    }

    /**
     * 跳转带参数bundle(不拦截)
     * @param pagePath
     * @param content
     * @param key
     *  @param requestCode
     */
    public static void jumpPageWithStringForResultNotInterceptor(Activity context, String pagePath,String content,String key,int requestCode){
        ARouter.getInstance().build(pagePath).withString(key,content).greenChannel().navigation(context,requestCode);
    }

    /**
     * 跳转带参数bundle
     * @param pagePath
     * @param content  对象
     * @param key
     *  取值时  getIntent().getExtras().getString("key")
     */
    public static void jumpPageWithObjectNotInterceptor(String pagePath, Object content,String key){
        ARouter.getInstance().build(pagePath).withObject(key,content).greenChannel().navigation();
    }
    /**
     * 跳转带参数bundle
     * @param pagePath
     * @param content
     * @param key
     *  取值时  getIntent().getExtras().getString("key")
     */
    public static void jumpPageWithSerializableNotInterceptor(String pagePath, Serializable content, String key){
        ARouter.getInstance().build(pagePath).withSerializable(key,content).greenChannel().navigation();
    }
}
