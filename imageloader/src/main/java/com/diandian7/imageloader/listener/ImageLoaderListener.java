package com.diandian7.imageloader.listener;

/**
 * 图片加载监听
 *
 * @author LiJiaJian
 * @date 2016/5/23 15:52
 */
public interface ImageLoaderListener {
    /**
     * 加载成功
     */
    int LOAD_SUCCESSFULLY = 100;
    /**
     * 加载失败
     */
    int load_fail = 101;

    /**
     * 图片加载状态回调
     *
     * @param status
     */
    void onImageLoader(int status);
}
