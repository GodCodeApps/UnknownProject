package com.lib_common.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lib_common.inject.component.ApplicationComponent;
import com.lib_common.service.base.DataLayer;
import com.trello.rxlifecycle2.components.support.RxFragment;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Peng YanMing on 2018\11\27 0027
 */
public class RxBaseFragment extends RxFragment {
    @Inject
    DataLayer mDataLayer;
    @Inject
    protected CompositeDisposable mCompositeDisposable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ApplicationComponent.Instance.get().inject(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mCompositeDisposable.dispose();
    }
    public DataLayer getDataLayer() {
        return mDataLayer;
    }
}
