package com.diandian7.imageloader.imgconfig;

import android.net.Uri;
import android.widget.ImageView;

import java.io.File;

/**
 * 图片加载对象
 * 使用Builder模式，一步一步的创建一个复杂对象的创建者模式;
 * 它允许用户在不知道内部构建细节的情况下，可以更精细的控制对象的构建流程
 * 使用：
 * ImageLoader<String> imageLoader = ImageLoader.createBuilder(url)
 * .setLoadErrorResId(-1)
 * .setPlaceHolderResId(-1)
 * .setImageView(null)
 * .build();
 */
public class ImageLoader<SourceType> {

    private SourceType source; //资源来源
    private int placeHolderResId; //占位图(默认图)资源id
    private int loadErrorResId; //图片加载失败后显示图片的资源id
    private ImageView imageView; //图片控件
    private boolean isGif; //是否GIF图片

    private ImageLoader(Builder<SourceType> builder) {
        this.source = builder.source;
        this.placeHolderResId = builder.placeHolderResId;
        this.loadErrorResId = builder.loadErrorResId;
        this.imageView = builder.imageView;
        this.isGif = builder.isGif;
    }

    /**
     * 创建网络资源Buidler对象
     *
     * @param url
     * @return
     */
    public static Builder<String> createBuilder(String url) {
        return new Builder<String>(url);
    }

    /**
     * 创建磁盘文件资源Buidler对象
     *
     * @param file
     * @return
     */
    public static Builder<File> createBuilder(File file) {
        return new Builder<File>(file);
    }

    /**
     * 创建Uri资源Buidler对象
     *
     * @param uri
     * @return
     */
    public static Builder<Uri> createBuilder(Uri uri) {
        return new Builder<Uri>(uri);
    }

    /**
     * 创建本地资源Buidler对象
     *
     * @param resId
     * @return
     */
    public static Builder<Integer> createBuilder(int resId) {
        return new Builder<Integer>(resId);
    }

    /**
     * 图片url
     *
     * @return
     */
    public SourceType getSource() {
        return source;
    }

    /**
     * 占位图(默认图)资源id
     *
     * @return
     */
    public int getPlaceHolderResId() {
        return placeHolderResId;
    }


    /**
     * 占位图(加载出错图)资源id
     *
     * @return
     */
    public int getLoadErrorResId() {
        return loadErrorResId;
    }

    /**
     * 图片控件
     *
     * @return
     */
    public ImageView getImageView() {
        return imageView;
    }

    /**
     * 是否GIF
     *
     * @return
     */
    public boolean isGif() {
        return isGif;
    }

    /**
     * ImageLoader builder工具
     *
     * @author LiJiaJian
     * @date 2016/5/26 10:09
     */
    public static class Builder<SourceType> {

        private SourceType source;
        private int placeHolderResId; //占位图(默认图)资源id
        private int loadErrorResId; //图片加载失败后显示图片的资源id
        private ImageView imageView; //图片控件
        private boolean isGif; //是否GIF图片

        private Builder(SourceType source) {
            this.source = source;
            this.isGif = false;
        }

        /**
         * 设置占位图(默认图)资源id
         *
         * @param placeHolderResId
         * @return
         */
        public Builder<SourceType> setPlaceHolderResId(int placeHolderResId) {
            this.placeHolderResId = placeHolderResId;
            return this;
        }

        /**
         * 设置图片加载失败后显示图片的资源id
         *
         * @param loadErrorResId
         * @return
         */
        public Builder<SourceType> setLoadErrorResId(int loadErrorResId) {
            this.loadErrorResId = loadErrorResId;
            return this;
        }

        /**
         * 设置图片控件
         *
         * @param imageView
         * @return
         */
        public Builder<SourceType> setImageView(ImageView imageView) {
            this.imageView = imageView;
            return this;
        }

        /**
         * 标记是否gif
         *
         * @return
         */
        public Builder<SourceType> asGif() {
            this.isGif = true;
            return this;
        }

        /**
         * 构造ImageLoader对象
         *
         * @return
         */
        public ImageLoader<SourceType> build() {
            return new ImageLoader(this);
        }
    }


}
