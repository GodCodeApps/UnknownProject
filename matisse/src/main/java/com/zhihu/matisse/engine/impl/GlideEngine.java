/*
 * Copyright 2017 Zhihu Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zhihu.matisse.engine.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.diandian7.imageloader.glide.GlideApp;
import com.zhihu.matisse.engine.ImageEngine;

/**
 * {@link ImageEngine} implementation using Glide.
 */

public class GlideEngine implements ImageEngine {

    @Override
    public void loadThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {
        GlideApp.with(context)
                .load(uri)
                .apply(new RequestOptions().placeholder(placeholder)
                        .centerCrop().override(resize, resize)
                )
                .transition(new DrawableTransitionOptions().crossFade(300))
                .into(imageView);
    }

    @Override
    public void loadGifThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView,
                                 Uri uri) {
        GlideApp.with(context)
                .load(uri)
                .apply(new RequestOptions().placeholder(placeholder)
                        .centerCrop().override(resize, resize)
                )
                .transition(new DrawableTransitionOptions().crossFade(300))
                .into(imageView);
    }

    @Override
    public void loadImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        GlideApp.with(context)
                .load(uri)
                .apply(new RequestOptions()
                        .fitCenter().override(resizeX, resizeY)
                        .priority(Priority.HIGH)
                )
                .transition(new DrawableTransitionOptions().crossFade(300))
                .into(imageView);
    }

    @Override
    public void loadImage(Context context, ImageView imageView, String url) {
        GlideApp.with(context)
                .load(url)
                .transition(new DrawableTransitionOptions().crossFade(300))
                .into(imageView);
    }

    @Override
    public void loadImage(Context context, ImageView imageView, String url, RequestListener requestListener) {
        GlideApp.with(context)
                .load(url)
                .transition(new DrawableTransitionOptions().crossFade(300))
                .listener(requestListener)
                .into(imageView);
    }

    @Override
    public void loadGifImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        GlideApp.with(context)
                .load(uri)
                .apply(new RequestOptions()
                        .fitCenter().override(resizeX, resizeY)
                        .priority(Priority.HIGH))
                .transition(new DrawableTransitionOptions().crossFade(300))
                .into(imageView);
    }

    @Override
    public boolean supportAnimatedGif() {
        return true;
    }

}
