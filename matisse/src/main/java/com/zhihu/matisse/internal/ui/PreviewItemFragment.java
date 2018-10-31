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
package com.zhihu.matisse.internal.ui;

import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.zhihu.matisse.R;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.entity.SelectionSpec;
import com.zhihu.matisse.internal.utils.PathUtils;
import com.zhihu.matisse.internal.utils.PhotoMetadataUtils;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;

public class PreviewItemFragment extends Fragment {

    private static final String ARGS_ITEM = "args_item";
    private static final String ARGS_TRANSITION_NAME = "args_transition_name";

    public static PreviewItemFragment newInstance(Item item) {
        return newInstance(item, null);
    }

    public static PreviewItemFragment newInstance(Item item, String transitionName) {
        PreviewItemFragment fragment = new PreviewItemFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGS_ITEM, item);
        bundle.putString(ARGS_TRANSITION_NAME, transitionName);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_preview_item, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Item item = getArguments().getParcelable(ARGS_ITEM);
        String transitionName = null;

        if (getArguments().containsKey(ARGS_TRANSITION_NAME)) {
            transitionName = getArguments().getString(ARGS_TRANSITION_NAME);
        }

        if (item == null) {
            return;
        }

        final View videoPlayButton = view.findViewById(R.id.video_play_button);
        final ContentLoadingProgressBar progressBar = view.findViewById(R.id.progressBar);

        if (item.isVideo()) {
            progressBar.hide();
            videoPlayButton.setVisibility(View.VISIBLE);
            videoPlayButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String videoPath = null;
                    if (!TextUtils.isEmpty(item.getUrl()) && item.uri == null) {
                        videoPath = item.getUrl();
                    } else if (item.uri != null) {
                        videoPath = PathUtils.getPath(view.getContext(), item.uri);
                    }
                    if (TextUtils.isEmpty(videoPath))
                        return;
                    Intent intent = new Intent(v.getContext(), PlayerActivity.class);
                    intent.putExtra(PlayerActivity.VIDEO_URL, videoPath);
                    startActivity(intent);
                }
            });
        } else {
            videoPlayButton.setVisibility(View.GONE);
        }

        ImageViewTouch image = (ImageViewTouch) view.findViewById(R.id.image_view);

        if (!TextUtils.isEmpty(transitionName) && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            image.setTransitionName(transitionName);
        }
        image.setDisplayType(ImageViewTouchBase.DisplayType.FIT_TO_SCREEN);

        //本地图片
        if (item.getContentUri() != null) {
            progressBar.hide();
            Point size = PhotoMetadataUtils.getBitmapSize(item.getContentUri(), getActivity());
            if (item.isGif()) {
                SelectionSpec.getInstance().imageEngine.loadGifImage(getContext(), size.x, size.y, image,
                        item.getContentUri());
            } else {
                if (item.getContentUri() != null) {
                    SelectionSpec.getInstance().imageEngine.loadImage(getContext(), size.x, size.y, image,
                            item.getContentUri());
                }
            }
        }

        //网络图片
        else if (!TextUtils.isEmpty(item.getUrl())) {
            if (SelectionSpec.getInstance().imageEngine == null) {
                SelectionSpec.getInstance().imageEngine = new GlideEngine();
            }
            SelectionSpec.getInstance().imageEngine.loadImage(getContext(), image, item.getUrl(), new RequestListener() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
                    progressBar.hide();
                    return false;
                }
            });
        }

        image.setSingleTapListener(new ImageViewTouch.OnImageViewTouchSingleTapListener() {
            @Override
            public void onSingleTapConfirmed() {
                if (getActivity() != null) {
                    getActivity().finish();
                    getActivity().overridePendingTransition(0, android.R.anim.fade_out);
                }
            }
        });
    }

    public void resetView() {
        if (GSYVideoManager.instance().isPlaying()) {
            GSYVideoManager.releaseAllVideos();
        }
        if (getView() != null) {
            ((ImageViewTouch) getView().findViewById(R.id.image_view)).resetMatrix();
        }
    }
}
