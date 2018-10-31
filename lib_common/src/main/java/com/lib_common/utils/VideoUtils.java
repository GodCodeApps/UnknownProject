package com.lib_common.utils;

import android.app.Activity;
import android.content.Context;
import android.media.MediaMetadataRetriever;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;


/**
 * author hechao
 * date 2018/9/6 0006
 */
public class VideoUtils {

    /**
     * 获取视频宽高
     *
     * @param videoPath
     * @return
     */
    public static int[] getVideoInfo(String videoPath) {
        try {
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
//            FFmpegMediaMetadataRetriever retriever = new FFmpegMediaMetadataRetriever();
            retriever.setDataSource(videoPath);
            String width = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH); // 视频宽度
            String height = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT); // 视频高度
            String rotation = null; // 视频旋转方向
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
                rotation = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_ROTATION);
            }
            retriever.release();
            return new int[]{Integer.parseInt(width), Integer.parseInt(height), rotation != null ? Integer.parseInt(rotation) : 0};
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new int[3];
    }

    /**
     * GSY视频播放配置
     *
     * @return
     */
    public static GSYVideoOptionBuilder getOptionBuilder(Context context) {
        return new GSYVideoOptionBuilder()
                .setIsTouchWiget(true)
//                .setSetUpLazy(true)//lazy可以防止滑动卡顿
                .setCacheWithPlay(false)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setShowFullAnimation(false)
                .setNeedLockFull(false)
                .setAutoFullWithSize(true)
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

                    @Override
                    public void onAutoComplete(String url, Object... objects) {
                        super.onAutoComplete(url, objects);
                        if (GSYVideoManager.isFullState((Activity) context)) {
                            GSYVideoManager.backFromWindowFull(context);
                        }
                    }
                });
    }
}
