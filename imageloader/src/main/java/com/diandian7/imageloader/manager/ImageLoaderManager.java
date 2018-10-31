package com.diandian7.imageloader.manager;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.ViewTarget;
import com.diandian7.imageloader.imgconfig.ImageLoader;
import com.diandian7.imageloader.listener.ImageLoaderListener;
import com.diandian7.imageloader.provider.BaseImageLoaderProvider;
import com.diandian7.imageloader.provider.impl.GlideImageLoaderProvider;

/**
 * 图片加载管理器
 */
public class ImageLoaderManager {
    /**
     * Log TAG
     */
    private static final String TAG = ImageLoaderManager.class.getSimpleName();
    /**
     * 单例对象
     */
    private static ImageLoaderManager mInstance;
    /**
     * 图片加载提供者对象
     */
    private BaseImageLoaderProvider mProvider;
    /**
     * 本地缓存路径
     */
    private String mDiskCachePath = null;

    /**
     * 初始化管理器
     *
     * @param applicationContext
     */
    public void init(Context applicationContext, String diskCachePath) {
        checkedContextNotNull(applicationContext);
        // 目前使用Google开源图片加载框架Glide
        // 后续如果切换别的图片框架重写一个BaseImageLoaderProvider替换下面GlideImageLoaderProvider即可
        mProvider = new GlideImageLoaderProvider(applicationContext);
        this.mDiskCachePath = diskCachePath;
    }

    /**
     * 单例私有构造，初始化必要参数
     */
    private ImageLoaderManager() {
    }

    /**
     * 单例
     *
     * @return
     */
    public static ImageLoaderManager getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoaderManager.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoaderManager();
                    return mInstance;
                }
            }
        }
        return mInstance;
    }

    /**
     * 检查上下文是否为NULL
     *
     * @param context
     */
    private void checkedContextNotNull(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("applicationContext can not be null~    please run init~");
        }
    }

    /**
     * 加载图片
     *
     * @param context 上下文
     * @param loader  图片加载对象
     */
    public void loadImage(Context context, ImageLoader loader) {
        mProvider.loadImage(context, loader);
    }

    public void loadAvatar(Context context, ImageLoader loader) {
        mProvider.loadAvatar(context, loader);
    }

    /**
     * 加载图片
     *
     * @param context 上下文
     * @param loader  图片加载对象
     */
    public void loadImage(Context context, ImageLoader loader, ImageLoaderListener listener) {
        mProvider.loadImage(context, loader, listener);
    }

    public void loadImage(Context context, ImageLoader loader, SimpleTarget<Bitmap> target) {
        mProvider.loadImageToTarget(context, loader, target);
    }

    public void loadImage(Context context, ImageLoader loader, ViewTarget target) {
        mProvider.loadImageToTarget(context, loader, target);
    }

    public void releaseMemoryCache(Context context) {
        mProvider.releaseMemoryCache(context);
    }

    public String getDiskCachePath() {
        return mDiskCachePath;
    }
}
