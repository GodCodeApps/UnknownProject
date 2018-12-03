package com.lib_common.commonadapter;

import android.content.Context;

import com.lib_common.R;
import com.lib_common.base.recycler.RecyclerHolder;
import com.lib_common.databinding.ItemVideoListBinding;
import com.lib_common.entity.VideoInfo;

import java.util.List;

/**
 * author :  Peng YanMing
 * data   : 2018年12月03日
 * desc   :
 */
public class VideoListAdapter extends CommonAdapter<VideoInfo.ItemListBean, ItemVideoListBinding> {
    public VideoListAdapter(Context context, List list) {
        super(context, list, R.layout.item_video_list);
    }

    @Override
    protected void onBind(RecyclerHolder<ItemVideoListBinding> holder, int position, VideoInfo.ItemListBean itemListBean) {
        super.onBind(holder, position, itemListBean);
        ItemVideoListBinding binding = holder.binding;
        binding.simpleVideoPlayer.setVideoUrl(itemListBean.getData().getPlayUrl());
        if (itemListBean.getData().getCover() != null) {
            binding.simpleVideoPlayer.setThumbImageView(binding.simpleVideoPlayer.getThumbView(itemListBean.getData().getCover().getFeed()));
        } else {
            binding.simpleVideoPlayer.setThumbImageView(binding.simpleVideoPlayer.getThumbView(itemListBean.getData().getPlayUrl()));
        }
        binding.simpleVideoPlayer.getFullscreenButton().setOnClickListener(v -> binding.simpleVideoPlayer.startWindowFullscreen(mContext, false, false));
        binding.simpleVideoPlayer.setPlayPosition(position);
        binding.simpleVideoPlayer.setPlayTag(VideoListAdapter.class.getCanonicalName());

    }
}
