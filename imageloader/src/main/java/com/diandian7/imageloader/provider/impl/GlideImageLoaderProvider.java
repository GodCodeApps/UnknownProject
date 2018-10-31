package com.diandian7.imageloader.provider.impl;

import android.app.Fragment;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.ViewTarget;
import com.diandian7.imageloader.R;
import com.diandian7.imageloader.glide.GlideApp;
import com.diandian7.imageloader.imgconfig.ImageLoader;
import com.diandian7.imageloader.listener.ImageLoaderListener;
import com.diandian7.imageloader.provider.BaseImageLoaderProvider;


/**
 * Google开源图片加载框架Glide图片加载提供实现
 */
public class GlideImageLoaderProvider extends BaseImageLoaderProvider {

    /**
     * 初始化
     *
     * @param context
     */
    public GlideImageLoaderProvider(Context context) {
        super(context);
    }

    @Override
    protected void initConfigs(Context context) {
    }

    @Override
    public void loadImageToTarget(Context context, ImageLoader loader, SimpleTarget target) {
        doLoadImage(GlideApp.with(context), loader, target);
    }

    @Override
    public void loadImageToTarget(Context context, ImageLoader loader, ViewTarget target) {
        doLoadImage(GlideApp.with(context), loader, target);
    }

    @Override
    public void releaseMemoryCache(Context context) {
        //将部分图片从内存中释放，避免图片内存占有过大致使应用内存使用率居高不下
        GlideApp.get(context).onTrimMemory(ComponentCallbacks2.TRIM_MEMORY_BACKGROUND);
    }

    @Override
    public void loadImage(Context context, ImageLoader loader, ImageLoaderListener listener) {
        doLoadImage(GlideApp.with(context), loader, listener);
    }

    @Override
    public void loadImage(Fragment fragment, ImageLoader loader, ImageLoaderListener listener) {
        doLoadImage(GlideApp.with(fragment), loader, listener);
    }

    @Override
    public void loadAvatar(Context context, ImageLoader loader) {
        loadAvatar(context, loader, null);
    }

    @Override
    public void loadAvatar(Context context, ImageLoader loader, ImageLoaderListener listener) {
        doLoadAvatar(GlideApp.with(context), loader, listener);
    }

    private void doLoadImage(RequestManager requestManager, ImageLoader loader, SimpleTarget<Bitmap> target) {
        RequestBuilder drawableRequestBuilder;
        if (loader.isGif()) {
            drawableRequestBuilder = requestManager
                    .applyDefaultRequestOptions(new RequestOptions().error(loader.getLoadErrorResId() == 0 ? R.color.color_D8D8D8 : loader.getLoadErrorResId()).placeholder(loader.getPlaceHolderResId()).diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                    .asGif()
                    .transition(new DrawableTransitionOptions()
                            .crossFade())
                    .load(loader.getSource());
        } else {
            drawableRequestBuilder = requestManager
                    .applyDefaultRequestOptions(new RequestOptions().error(loader.getLoadErrorResId() == 0 ? R.color.color_D8D8D8 : loader.getLoadErrorResId()).placeholder(loader.getPlaceHolderResId() == 0 ? R.color.color_D8D8D8 : loader.getPlaceHolderResId()).diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                    .asBitmap().transition(new BitmapTransitionOptions().crossFade()).load(loader.getSource());
        }
        drawableRequestBuilder.into(target);
    }

    private void doLoadImage(RequestManager requestManager, ImageLoader loader, ViewTarget target) {
        RequestBuilder drawableRequestBuilder;
        if (loader.isGif()) {
            drawableRequestBuilder = requestManager
                    .applyDefaultRequestOptions(new RequestOptions().error(loader.getLoadErrorResId() == 0 ? R.color.color_D8D8D8 : loader.getLoadErrorResId()).placeholder(loader.getPlaceHolderResId() == 0 ? R.color.color_D8D8D8 : loader.getPlaceHolderResId()).diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                    .asGif()
                    .transition(new DrawableTransitionOptions()
                            .crossFade())
                    .load(loader.getSource());
        } else {
            drawableRequestBuilder = requestManager
                    .applyDefaultRequestOptions(new RequestOptions().error(loader.getLoadErrorResId() == 0 ? R.color.color_D8D8D8 : loader.getLoadErrorResId()).placeholder(loader.getPlaceHolderResId() == 0 ? R.color.color_D8D8D8 : loader.getPlaceHolderResId()).diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                    .asBitmap()
                    .transition(new BitmapTransitionOptions().crossFade())
                    .load(loader.getSource());
        }
        drawableRequestBuilder.into(target);
    }

    /**
     * 执行图片加载
     *
     * @param requestManager
     * @param loader
     * @param listener
     */
    private void doLoadImage(RequestManager requestManager, ImageLoader loader, ImageLoaderListener listener) {
        RequestBuilder drawableRequestBuilder;
        if (loader.isGif()) {
            drawableRequestBuilder = requestManager
                    .applyDefaultRequestOptions(new RequestOptions().error(loader.getLoadErrorResId() == 0 || loader.getLoadErrorResId() == -1 ? R.color.color_D8D8D8 : loader.getLoadErrorResId()).placeholder(loader.getPlaceHolderResId() == 0 || loader.getPlaceHolderResId() == -1 ? R.color.color_D8D8D8 : loader.getPlaceHolderResId()).diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                    .asGif()
                    .transition(new DrawableTransitionOptions()
                            .crossFade())
                    .load(loader.getSource());
        } else {
            drawableRequestBuilder = requestManager
                    .applyDefaultRequestOptions(new RequestOptions().error(loader.getLoadErrorResId() == 0 || loader.getLoadErrorResId() == -1 ? R.color.color_D8D8D8 : loader.getLoadErrorResId()).placeholder(loader.getPlaceHolderResId() == 0 || loader.getPlaceHolderResId() == -1 ? R.color.color_D8D8D8 : loader.getPlaceHolderResId())
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                    .asBitmap()
                    .transition(new BitmapTransitionOptions().crossFade())
                    .load(loader.getSource());
        }
        drawableRequestBuilder.into(loader.getImageView());


//        requestManager.load(loader.getScorce())        //load方法可以加载URL、File、Uri（包括视频，可以加载第一帧），本地资源Resource
//                .asGif()                                  //如果传入的String url是Gif则设置这个参数，如果设置了这个方法但是此url不是gif则当做失败处理
//                .asBitmap()                               //如果仅仅想要显示 Gif 的第一帧，你可以调用 asBitmap() 去保证其作为一个常规的图片显示，即使这个 URL 是一个 Gif
//                .placeholder(loader.getPlaceHolderResId())  //占位图
//                .diskCacheStrategy(DiskCacheStrategy.ALL)   //PS: DiskCacheStrategy.NONE 什么都不缓存，跳过磁盘缓存
        //    DiskCacheStrategy.SOURCE 仅仅只缓存原来的全分辨率的图像。
        //    DiskCacheStrategy.RESULT 仅仅缓存最终的图像，即，降低分辨率后的（或者是转换后的）(3.6.1以及最新的3.7.0默认行为)
        //    DiskCacheStrategy.ALL 缓存所有版本的图像（3.6.0及以下默认行为）
//                .skipMemoryCache(true)                    //跳过内存缓存，不会把这张图片放到内存缓存中去，默认是false
        //PS: 注意一个事实，对于相同的 URL ，如果你的初始请求没调用 .skipMemoryCache(true) 方法，
        //    你后来又调用了 .skipMemoryCache(true)这个方法，这个资源将会在内存中获取缓存。当你想要去调整缓存行为时，确保对同一个资源调用的一致性。
//                .error(R.drawable.ic_person)              //加载失败显示的图，只能是drawable或者资源id
//                .animate()                                //设置自定义加载完成的动画
//                .centerCrop()                             //是一个裁剪技术，即缩放图像让它填充到 ImageView 界限内并且侧键额外的部分。ImageView 可能会完全填充，但图像可能不会完整显示
//                .fitCenter()                              //是裁剪技术，即缩放图像让图像都测量出来等于或小于 ImageView 的边界范围。该图像将会完全显示，但可能不会填满整个 ImageView。
//                .crossFade()                              //强制淡入淡出动画，还有一个重载方法.crossFade(int duration)可控制淡入淡出时间默认300ms
//                .dontAnimate()                            //去掉淡入淡出效果，直接显示
//                .override(600, 200)                       //改变图片加载大小，默认Gilde已经自动适应ImageView大小了，如果想重新改变大小可以设置此函数

//                .priority(Priority.HIGH)                  //图片加载的优先级(低到高) Priority.LOW -> Priority.NORMAL -> Priority.HIGH -> Priority.IMMEDIATE
//                .thumbnail(0.1f)                          //加载缩略图，0.1f表示是全尺寸原图的10%大小，也可以用如下方法加载另外一张图当做缩略图，当需要的图加载完之后就覆盖缩略图
        //DrawableRequestBuilder<String> thumbnailRequest = Glide.with(context).load(thumbnailUrl);
        //缩略图设置别的图做缩略图   .thumbnail(thumbnailRequest)
//                .listener(requestListener)                //设置回调，错误回调
//                .into(target1)                            //target1在下面：
        //首先是 SimpleTarget 对象的字段声明。从技术上来说，Java/Android 会允许你在 .into() 方法中去声明 target 的匿名内部类。然而，
        //这大大增加了这样一个可能性：即在 Glide 做完图片请求之前， Android 垃圾回收移除了这个匿名内部类对象。最终这可能会导致一个情况，
        //当图像加载完成了，但是回调再也不会被调用。所请确保你所声明的回调对象是作为一个字段对象的，
        //这样你就可以保护它避免被邪恶的 Android 垃圾回收机制回收。
//                .into(target2)                            //target2在下面： 指定图片加载大小，有效控制节省内存
//                .into(viewTarget)                         //viewTarget在下面： 把图片加载到非ImageView的控件上
//                .into(loader.getImageView());         //把图片加载到View中

    }

    private void doLoadAvatar(RequestManager requestManager, ImageLoader loader, ImageLoaderListener listener) {
        RequestBuilder drawableRequestBuilder;
        if (loader.isGif()) {
            drawableRequestBuilder = requestManager
                    .applyDefaultRequestOptions(new RequestOptions().error(loader.getLoadErrorResId()).placeholder(loader.getPlaceHolderResId()).diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                    .asGif()
                    .transition(new DrawableTransitionOptions()
                            .crossFade())
                    .load(loader.getSource());
        } else {
            drawableRequestBuilder = requestManager
                    .applyDefaultRequestOptions(new RequestOptions().error(loader.getLoadErrorResId()).placeholder(loader.getPlaceHolderResId())
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                    .asBitmap()
                    .load(loader.getSource());
        }
        drawableRequestBuilder.into(loader.getImageView());
    }
}
