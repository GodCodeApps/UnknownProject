package com.lib_common.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.lib_common.R;
import com.lib_common.imageloader.app.ImageLoaderApi;
import com.lib_common.widget.ninegrid.NineGridView;

/**
 * author hechao
 * date 2018/8/31 0031
 */
public class NineImageLoader implements NineGridView.ImageLoader {
    @Override
    public void onDisplayImage(Context context, ImageView imageView, String url) {
        ImageLoaderApi.loadImage(url, R.color.color_D8D8D8, R.mipmap.default_head, imageView, context);
    }

    @Override
    public Bitmap getCacheImage(String url) {
        return null;
    }
}
