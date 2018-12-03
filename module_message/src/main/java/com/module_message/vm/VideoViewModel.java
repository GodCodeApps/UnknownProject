package com.module_message.vm;

import android.content.Context;

import com.lib_common.base.recycler.PagingViewModel;
import com.lib_common.commonadapter.VideoListAdapter;
import com.lib_common.entity.VideoInfo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author :  Peng YanMing
 * data   : 2018年12月03日
 * desc   :
 */
public class VideoViewModel extends PagingViewModel<VideoInfo.ItemListBean, VideoListAdapter> {
    public VideoViewModel(Context context) {
        super(context);
    }

    @Override
    protected void initAdapter() {
        adapter = new VideoListAdapter(context, mList);
    }

    @Override
    protected void getData(boolean isMore) {
        getDataLayer().getVideoPlayService().getVideoList(System.currentTimeMillis(), pagingLimit, pagingOffset)
                .delay(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                    mCompositeDisposable.add(disposable);
                    doOnSubscribe(isMore);
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> {
                    doOnComplete(isMore);
                })
                .doOnError(throwable -> {
                    doOnError(isMore, throwable);
                })
                .doOnNext(image -> {
                    pagingHaveMore = (image.getItemList().size() >= pagingLimit);
                })
                .subscribe(newHomeInfo -> {
                    List<VideoInfo.ItemListBean> list = newHomeInfo.getItemList();
                    accept(isMore, list);
                }, Throwable::printStackTrace);
    }
}
