package com.lib_common.binding;


import android.databinding.BindingAdapter;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.lib_common.R;
import com.lib_common.imageloader.app.ImageLoaderApi;
import com.lib_common.widget.CircleImageView;


/**
 * Created by goldze on 2017/6/18.
 */
public final class ViewAdapter {
    @BindingAdapter(value = {"url", "placeholderRes"}, requireAll = false)
    public static void setImageUri(ImageView imageView, String url, int placeholderRes) {
        if (!TextUtils.isEmpty(url)) {
            int colorRes = ImageLoaderApi.getPlaceholderColor();
            ImageLoaderApi.loadImage(url, colorRes, colorRes, imageView, imageView.getContext());
        }
    }

    @BindingAdapter(value = {"url", "placeholderRes"}, requireAll = false)
    public static void setImageUri(CircleImageView imageView, String url, String placeholderRes) {
        if (!TextUtils.isEmpty(url)) {
            ImageLoaderApi.loadAvatar(url, R.mipmap.default_head, R.mipmap.default_head, imageView, imageView.getContext());
        }
    }

    @BindingAdapter(value = {"path"})
    public static void setImageUri(ImageView imageView, String path) {
        Glide.with(imageView.getContext())
                .load(TextUtils.isEmpty(path) ? R.mipmap.default_head : path)
                .transition(new DrawableTransitionOptions()
                        .crossFade())
                .into(imageView);
    }

    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, int resId) {
        view.setImageDrawable(ContextCompat.getDrawable(view.getContext(), resId));
    }

}

