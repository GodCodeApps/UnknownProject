package com.lib_common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lib_common.inject.component.ApplicationComponent;
import com.lib_common.service.base.DataLayer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Peng YanMing on 2018\11\26 0026
 */
public class RxBaseActivity extends RxAppCompatActivity {
    @Inject
    DataLayer mDataLayer;
    @Inject
    protected CompositeDisposable mCompositeDisposable;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApplicationComponent.Instance.get().inject(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.dispose();
    }
    public DataLayer getDataLayer() {
        return mDataLayer;
    }
}
