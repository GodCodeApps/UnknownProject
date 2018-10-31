package com.diandian7.imageloader.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.AppGlideModule;
import com.diandian7.imageloader.manager.ImageLoaderManager;

@GlideModule
public class GlideModuleConfig extends AppGlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDiskCache(new DiskLruCacheFactory(ImageLoaderManager.getInstance().getDiskCachePath(), 1024 * 1024 * 100));
    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        super.registerComponents(context, glide, registry);
        // 写入咱们的okhttp
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        // 写入咱们的okhttp的拦截器,在拦截器中监听进度
//        builder.addInterceptor(new ProgressInterceptor());
//        OkHttpClient okHttpClient = builder.build();
//        // glide吧urlConnection替换为okhttp
//        registry.replace(GlideUrl.class, InputStream.class, new OkHttpGlideUrlLoader.Factory(okHttpClient));
    }
}
