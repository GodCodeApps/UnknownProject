package com.module_community.vm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.MainThread;

import com.lib_common.base.BaseViewModel;
import com.lib_common.base.recycler.PagingViewModel;
import com.lib_common.commonadapter.CommonAdapter;
import com.lib_common.entity.ContentGson;
import com.lib_common.entity.NewHomeInfo;
import com.lib_common.utils.JsonUtil;
import com.module_community.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Peng YanMing on 2018\10\31 0031
 */
public class CommunityViewModel extends PagingViewModel<ContentGson, CommonAdapter> {
    public CommunityViewModel(Context context) {
        super(context);
    }

    @Override
    protected void initAdapter() {
        adapter = new CommonAdapter(context, mList, R.layout.item_community);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void getData(boolean isMore) {
        getDataLayer().getNewService().getNewHomeList(pagingOffset)
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
                    pagingHaveMore = image.isHas_more();
                })
                .subscribe(newHomeInfo -> {
                    List<NewHomeInfo.DataBean> data = newHomeInfo.getData();
                    List<ContentGson> results = new ArrayList<>();
                    if (data != null && data.size() > 0) {
                        for (int i = 0; i < data.size(); i++) {
                            NewHomeInfo.DataBean dataBean = data.get(i);
                            String content = dataBean.getContent();
                            ContentGson contentGson = JsonUtil.toObj(content, ContentGson.class);
                            results.add(contentGson);
                        }
                    }
                    accept(isMore, results);
                },Throwable::printStackTrace);
    }
}
