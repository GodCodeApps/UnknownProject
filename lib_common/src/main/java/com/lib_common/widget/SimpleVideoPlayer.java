package com.lib_common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.lib_common.R;
import com.lib_common.utils.constant.VideoConstants;
import com.shuyu.gsyvideoplayer.video.NormalGSYVideoPlayer;

/**
 * author hechao
 * date 2018/9/11 0011
 */
public class SimpleVideoPlayer extends NormalGSYVideoPlayer {

    public SimpleVideoPlayer(Context context) {
        this(context, null);
    }

    public SimpleVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        mThumbImageView = new ImageView(context);

    }

    public void setVideoUrl(String url) {
        setThumbImageView(getThumbView(url + VideoConstants.OSS_THUMB_SUFFIX));
        setUp(url, true, null);
    }

    public View getThumbView(String url) {
        ImageView imageView = new ImageView(getContext());
        Glide.with(this)
                .load(url)
                .apply(new RequestOptions().placeholder(R.color.gray))
                .transition(new DrawableTransitionOptions()
                        .crossFade())
                .into(imageView);
        return imageView;
    }
}
