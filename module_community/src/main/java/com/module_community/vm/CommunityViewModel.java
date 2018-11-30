package com.module_community.vm;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.lib_common.base.recycler.PagingViewModel;
import com.lib_common.commonadapter.CommonAdapter;
import com.lib_common.entity.PictureInfo;
import com.module_community.R;
import com.zhihu.matisse.internal.ui.PreviewActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Peng YanMing on 2018\10\31 0031
 */
public class CommunityViewModel extends PagingViewModel<PictureInfo, CommonAdapter> {
    public CommunityViewModel(Context context) {
        super(context);
    }

    @Override
    protected void initAdapter() {
        adapter = new CommonAdapter(context, mList, R.layout.item_community);
        adapter.setOnItemClickListener((view, position, o) -> {
            List<PictureInfo> list = adapter.getList();
            Object[] objects = list.toArray();
            Observable.fromArray(objects).map(o1 -> ((PictureInfo) o1).getUrl()).toList().subscribe(strings -> {
                Intent intent = new Intent((Activity) context, PreviewActivity.class);
                intent.putExtra(PreviewActivity.PREVIEW_POSITION, position);
                intent.putStringArrayListExtra(PreviewActivity.PREVIEW_IMAGE_ARRAY, (ArrayList<String>) strings);
                context.startActivity(intent);
            });

        });
    }

    @SuppressLint("CheckResult")
    @Override
    protected void getData(boolean isMore) {
        getDataLayer().getNewService().getPictureList(pagingOffset)
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
                    pagingHaveMore = (image.getResults().size() >= 20);
                })
                .subscribe(newHomeInfo -> {
                    List<PictureInfo> results = newHomeInfo.getResults();
                    accept(isMore, results);
                }, Throwable::printStackTrace);
    }
}
