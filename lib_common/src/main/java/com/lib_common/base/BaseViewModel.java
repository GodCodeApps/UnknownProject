package com.lib_common.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.afollestad.materialdialogs.MaterialDialog;
import com.lib_common.inject.component.ApplicationComponent;
import com.lib_common.service.base.DataLayer;
import com.lib_common.utils.MaterialDialogUtils;
import com.lib_common.utils.ToastUtils;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * Created by goldze on 2017/6/15.
 */
public class BaseViewModel extends BaseObservable implements IBaseViewModel ,Disposable {
    public Context context;
    protected Fragment fragment;
    public final ObservableBoolean loading = new ObservableBoolean();
    public final ObservableBoolean empty = new ObservableBoolean();
    public final ObservableBoolean error = new ObservableBoolean();
    public final ObservableField<String> msg = new ObservableField<>();
    @Inject
    DataLayer mDataLayer;
    @Inject
    protected CompositeDisposable mCompositeDisposable;
    public BaseViewModel() {
        ApplicationComponent.Instance.get().inject(this);
    }

    public BaseViewModel(Context context) {
        this.context = context;
        ApplicationComponent.Instance.get().inject(this);
        initDialog();
    }

    public BaseViewModel(Fragment fragment) {
        this(fragment.getContext());
        this.fragment = fragment;
    }

    private void initDialog() {
        MaterialDialog.Builder builder = MaterialDialogUtils.showIndeterminateProgressDialog(context, "请稍后...", true);
        dialog = builder.build();
    }

    public void afterCreate() {
        afterCreate(true);
    }

    public void afterCreate(boolean autoRequest) {
    }
    protected DataLayer getDataLayer() {
        return mDataLayer;
    }

    @Override
    public boolean isDisposed() {
        return mCompositeDisposable.isDisposed();
    }

    @Override
    public void dispose() {
        mCompositeDisposable.dispose();
    }
    private MaterialDialog dialog;

    public void showDialog() {
        showDialog("请稍后...");
    }

    public void showToast(String msg) {
        ToastUtils.showDefaultToast(msg);
    }

    public void showDialog(String title) {
        if (dialog != null) {
            dialog.getTitleView().setText(title);
            dialog.show();
        }
    }

    public void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    public void startActivity(Class<?> clz) {
        context.startActivity(new Intent(context, clz));
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(context, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    public void startActivityForResult(Class<?> clz, int requestCode) {
        Intent intent = new Intent(context, clz);
        ((Activity) context).startActivityForResult(intent, requestCode);
    }

    public void startActivityForResult(Intent intent, int requestCode) {
        ((Activity) context).startActivityForResult(intent, requestCode);
    }


    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void registerRxBus() {
    }

    @Override
    public void removeRxBus() {
    }

    protected void notifyMsg(@Nullable String s) {
        if (s != null && s.equals(msg.get())) {
            msg.notifyChange();
        } else {
            msg.set(s);
        }
    }
}
