package com.diandian7.imageloader.provider;

import android.app.Fragment;
import android.content.Context;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.ViewTarget;
import com.diandian7.imageloader.imgconfig.ImageLoader;
import com.diandian7.imageloader.listener.ImageLoaderListener;

/**
 * 提供图片加载抽象方法
 *
 * @author LiJiaJian
 * @date 2016/5/23 15:44
 */
public abstract class BaseImageLoaderProvider {

    /**
     * 初始化
     */
    public BaseImageLoaderProvider(Context context) {
        initConfigs(context);
    }

    /**
     * 初始化配置
     */
    protected abstract void initConfigs(Context context);

    /**
     * 加载图片，默认不需要监听回调
     *
     * @param context 上下文
     * @param loader  图片加载对象
     */
    public void loadImage(Context context, ImageLoader loader) {
        loadImage(context, loader, null);
    }

    public void loadAvatar(Context context, ImageLoader loader) {
        loadAvatar(context, loader, null);
    }

    /**
     * 加载图片
     *
     * @param context 上下文
     * @param loader  图片加载对象
     * @param target  图片返回目标
     */
    public abstract void loadImageToTarget(Context context, ImageLoader loader, SimpleTarget target);

    /**
     * 加载图片
     *
     * @param context 上下文
     * @param loader  图片加载对象
     * @param target  图片返回目标
     */
    public abstract void loadImageToTarget(Context context, ImageLoader loader, ViewTarget target);

    /**
     * 释放图片占有的内存
     *
     * @param context
     */
    public abstract void releaseMemoryCache(Context context);

    /**
     * 加载图片
     *
     * @param context  上下文
     * @param loader   图片加载对象
     * @param listener 图片加载监听
     */
    public abstract void loadImage(Context context, ImageLoader loader, ImageLoaderListener listener);

    public abstract void loadAvatar(Context context, ImageLoader loader, ImageLoaderListener listener);

    /**
     * 加载图片，默认不需要监听回调
     *
     * @param fragment 所依附的fragment
     * @param loader   图片加载对象
     */
    public void loadImage(Fragment fragment, ImageLoader loader) {
        loadImage(fragment, loader, null);
    }

    /**
     * 加载图片
     *
     * @param fragment 所依附的fragment
     * @param loader   图片加载对象
     * @param listener 图片加载监听
     */
    public abstract void loadImage(Fragment fragment, ImageLoader loader, ImageLoaderListener listener);
}
