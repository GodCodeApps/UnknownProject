package com.lib_common.imageloader.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.ViewTarget;
import com.diandian7.imageloader.imgconfig.ImageLoader;
import com.diandian7.imageloader.manager.ImageLoaderManager;
import com.lib_common.R;

import java.io.File;

/**
 * 对外提供快速定制加载图片API
 *
 * @author LiJiaJian
 * @date 2016/5/26 11:19
 */
public class ImageLoaderApi {

    public static final int RESOURCE_ID_NONE = -1;

    /**
     * 图片加载
     *
     * @param imageLoader
     * @param context
     */
    public static void loadImage(ImageLoader imageLoader, Context context) {
        ImageLoaderManager.getInstance().loadImage(context, imageLoader);
    }


    /**
     * 最简单的图片加载
     *
     * @param url
     * @param imageView
     * @param context
     */
    public static void loadImage(String url, ImageView imageView, Context context) {
        ImageLoader<String> imageLoader = ImageLoader.createBuilder(url)
                .setPlaceHolderResId(RESOURCE_ID_NONE)
                .setLoadErrorResId(RESOURCE_ID_NONE)
                .setImageView(imageView)
                .build();
        ImageLoaderManager.getInstance().loadImage(context, imageLoader);
    }

    /**
     * 图片加载
     *
     * @param url
     * @param placeHolderResId
     * @param loadErrorResId
     * @param imageView
     * @param context
     */
    public static void loadImage(String url, int placeHolderResId, int loadErrorResId, ImageView imageView, Context context) {
        ImageLoader<String> imageLoader = ImageLoader.createBuilder(url)
                .setPlaceHolderResId(placeHolderResId)
                .setLoadErrorResId(loadErrorResId)
                .setImageView(imageView)
                .build();
        ImageLoaderManager.getInstance().loadImage(context, imageLoader);
    }

    public static void loadAvatar(String url, int placeHolderResId, int loadErrorResId, ImageView imageView, Context context) {
        ImageLoader<String> imageLoader = ImageLoader.createBuilder(url)
                .setPlaceHolderResId(placeHolderResId)
                .setLoadErrorResId(loadErrorResId)
                .setImageView(imageView)
                .build();
        ImageLoaderManager.getInstance().loadAvatar(context, imageLoader);
    }

    /**
     * 图片加载
     *
     * @param url
     * @param placeHolderResId
     * @param loadErrorResId
     * @param imageView
     * @param context
     */
    public static void loadGif(String url, int placeHolderResId, int loadErrorResId, ImageView imageView, Context context) {
        ImageLoader<String> imageLoader = ImageLoader.createBuilder(url)
                .setPlaceHolderResId(placeHolderResId)
                .asGif()
                .setLoadErrorResId(loadErrorResId)
                .setImageView(imageView)
                .build();
        ImageLoaderManager.getInstance().loadImage(context, imageLoader);
    }

    /**
     * 最简单的图片加载
     *
     * @param file
     * @param imageView
     * @param context
     */
    public static void loadImage(File file, ImageView imageView, Context context) {
        ImageLoader<File> imageLoader = ImageLoader.createBuilder(file)
                .setPlaceHolderResId(RESOURCE_ID_NONE)
                .setLoadErrorResId(RESOURCE_ID_NONE)
                .setImageView(imageView)
                .build();
        ImageLoaderManager.getInstance().loadImage(context, imageLoader);
    }

    /**
     * 图片加载
     *
     * @param file
     * @param placeHolderResId
     * @param loadErrorResId
     * @param imageView
     * @param context
     */
    public static void loadImage(File file, int placeHolderResId, int loadErrorResId, ImageView imageView, Context context) {
        ImageLoader<File> imageLoader = ImageLoader.createBuilder(file)
                .setPlaceHolderResId(placeHolderResId)
                .setLoadErrorResId(loadErrorResId)
                .setImageView(imageView)
                .build();
        ImageLoaderManager.getInstance().loadImage(context, imageLoader);
    }

    /**
     * 最简单的图片加载
     *
     * @param uri
     * @param imageView
     * @param context
     */
    public static void loadImage(Uri uri, ImageView imageView, Context context) {
        ImageLoader<Uri> imageLoader = ImageLoader.createBuilder(uri)
                .setPlaceHolderResId(RESOURCE_ID_NONE)
                .setLoadErrorResId(RESOURCE_ID_NONE)
                .setImageView(imageView)
                .build();
        ImageLoaderManager.getInstance().loadImage(context, imageLoader);
    }

    /**
     * 图片加载
     *
     * @param uri
     * @param placeHolderResId
     * @param loadErrorResId
     * @param imageView
     * @param context
     */
    public static void loadImage(Uri uri, int placeHolderResId, int loadErrorResId, ImageView imageView, Context context) {
        ImageLoader<Uri> imageLoader = ImageLoader.createBuilder(uri)
                .setPlaceHolderResId(placeHolderResId)
                .setLoadErrorResId(loadErrorResId)
                .setImageView(imageView)
                .build();
        ImageLoaderManager.getInstance().loadImage(context, imageLoader);
    }

    /**
     * 最简单的图片加载
     *
     * @param resId
     * @param imageView
     * @param context
     */
    public static void loadImage(int resId, ImageView imageView, Context context) {
        ImageLoader<Integer> imageLoader = ImageLoader.createBuilder(resId)
                .setPlaceHolderResId(RESOURCE_ID_NONE)
                .setLoadErrorResId(RESOURCE_ID_NONE)
                .setImageView(imageView)
                .build();
        ImageLoaderManager.getInstance().loadImage(context, imageLoader);
    }

    /**
     * 最简单的图片加载
     *
     * @param resId
     * @param imageView
     * @param context
     */
    public static void loadGit(int resId, ImageView imageView, Context context) {
        ImageLoader<Integer> imageLoader = ImageLoader.createBuilder(resId)
                .asGif()
                .setImageView(imageView)
                .build();
        ImageLoaderManager.getInstance().loadImage(context, imageLoader);
    }

    /**
     * 图片加载
     *
     * @param context
     */
    public static void loadImage(String url, Context context, SimpleTarget<Bitmap> target) {
        ImageLoader<String> imageLoader = ImageLoader.createBuilder(url)
                .setPlaceHolderResId(RESOURCE_ID_NONE)
                .setLoadErrorResId(RESOURCE_ID_NONE)
                .build();
        ImageLoaderManager.getInstance().loadImage(context, imageLoader, target);
    }

    /**
     * 图片加载
     *
     * @param context
     */
    public static void loadImage(String url, Context context, ViewTarget target) {
        ImageLoader<String> imageLoader = ImageLoader.createBuilder(url)
                .setPlaceHolderResId(RESOURCE_ID_NONE)
                .setLoadErrorResId(RESOURCE_ID_NONE)
                .build();
        ImageLoaderManager.getInstance().loadImage(context, imageLoader, target);
    }

    public static void releaseMemoryCache(Context context) {
        ImageLoaderManager.getInstance().releaseMemoryCache(context);
    }
    /**
     * 获取随机占位色
     */
    public static int getPlaceholderColor() {
        return COLORS[(int) (Math.random() * COLORS.length)];
    }
    /**
     * 图片占位色
     */
    private static final int[] COLORS = new int[]{
            R.color.colorHolder00,
            R.color.colorHolder01,
            R.color.colorHolder02,
            R.color.colorHolder03,
            R.color.colorHolder04,
            R.color.colorHolder05,
            R.color.colorHolder06,
    };
}
