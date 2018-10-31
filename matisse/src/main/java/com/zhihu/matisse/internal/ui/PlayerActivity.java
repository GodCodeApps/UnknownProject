package com.zhihu.matisse.internal.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.zhihu.matisse.R;

/**
 * author hechao
 * date 2018/9/17 0017
 */
public class PlayerActivity extends AppCompatActivity {

    public static final String VIDEO_URL = "video_url";
    private StandardGSYVideoPlayer mVideoPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_player);

        mVideoPlayer = findViewById(R.id.video_player);
        mVideoPlayer.getBackButton().setVisibility(View.VISIBLE);

        hideNavigationBar();

        mVideoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String url = getIntent().getStringExtra(VIDEO_URL);

        ImageView imageView = new ImageView(this);
        Glide.with(this)
                .setDefaultRequestOptions(
                        new RequestOptions()
                                .frame(1000000)
                                .centerCrop())
                .load(url)
                .into(imageView);

        getOptionBuilder().setThumbImageView(imageView)
                .setUrl(url)
                .build(mVideoPlayer);

        mVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoPlayer.startWindowFullscreen(v.getContext(), true, false);
            }
        });

        mVideoPlayer.startPlayLogic();
    }

    @Override
    protected void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    /**
     * 初始化Video配置参数
     *
     * @return
     */
    private GSYVideoOptionBuilder getOptionBuilder() {
        return new GSYVideoOptionBuilder()
                .setIsTouchWiget(false)
//                .setSetUpLazy(true)//lazy可以防止滑动卡顿
                .setCacheWithPlay(true)
                .setRotateViewAuto(true)
                .setLockLand(true)
                .setShowFullAnimation(false)
                .setNeedLockFull(true)
                .setAutoFullWithSize(false)
                .setVideoAllCallBack(new GSYSampleCallBack() {
                    @Override
                    public void onPrepared(String url, Object... objects) {
                        super.onPrepared(url, objects);
//                        if (!simpleVideoPlayer.isIfCurrentIsFullscreen()) {
//                            //静音
//                            GSYVideoManager.instance().setNeedMute(false);
//                        }
                    }

                    @Override
                    public void onQuitFullscreen(String url, Object... objects) {
                        super.onQuitFullscreen(url, objects);
                        //全屏不静音
                        GSYVideoManager.instance().setNeedMute(true);
                    }

                    @Override
                    public void onEnterFullscreen(String url, Object... objects) {
                        super.onEnterFullscreen(url, objects);
                        GSYVideoManager.instance().setNeedMute(false);
                    }
                });
    }

    private void hideNavigationBar() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }
}
