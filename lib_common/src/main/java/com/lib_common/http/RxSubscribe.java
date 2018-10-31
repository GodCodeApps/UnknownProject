package com.lib_common.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;

import com.lib_common.entity.BaseBean;
import com.lib_common.http.exception.ServerException;
import com.lib_common.utils.CommonUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

/**
 * 回调封装
 */
public abstract class RxSubscribe<T> extends DisposableObserver<T> {
    private Context mContext;
    private ProgressDialog mDialog;
    //是否显示对话框
    private boolean mShowDialog;

    public RxSubscribe(Context context) {
        this.mContext = context;
    }

    @Override
    public void onComplete() {
        // todo some common as  dismiss loadding
    }

    @Override
    public void onStart() {
        // todo some common as show loadding  and check netWork is NetworkAvailable
        if (!CommonUtil.isNetWorkConnected(mContext)) {
           /* if (!isUnsubscribed()) {
                unsubscribe();
            }*/
            _onError("网络不给力");
            dismissLoading();
            showNoNetWorkConnected();
        }
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (!CommonUtil.isNetWorkConnected(mContext)) {
            //_onError("网络不可用");
           /* if (AppUtils.getTopActivity() instanceof BaseActivity){//关闭loading
                BaseActivity base = (BaseActivity) AppUtils.getTopActivity();
                base.showTopErrorTips("网络不可用");
            }*/
            //SocketTimeoutException:服务器响应超时
            //ConnectException:服务器请求超时
        } else if (e instanceof SocketTimeoutException || e instanceof ConnectException) {
//            if (AppUtils.getTopActivity() instanceof BaseActivity){//关闭loading
//                BaseActivity base = (BaseActivity) AppUtils.getTopActivity();
//                base.showTopErrorTips("请求超时,请稍后再试...");
//            }
        } else if (e instanceof HttpException) {
         /*   if (AppUtils.getTopActivity() instanceof BaseActivity){//关闭loading
                BaseActivity base = (BaseActivity) AppUtils.getTopActivity();
                base.showTopErrorTips("服务器异常，请稍后再试...");
            }*/
            //_onError("服务器异常，请稍后再试...");
        } else if (e instanceof ServerException) {
            _onError(((ServerException) e).getBaseBean());

        } else {
            if (!TextUtils.isEmpty(e.getMessage())) {
                _onError(e.getMessage());
            }
        }
        dismissLoading();
    }

    private void dismissLoading() {
      /*  EventBus.getDefault().post(new EventBusBean<>(Constant.EventTag.NETERORE, null));
        if (AppUtils.getTopActivity() instanceof BaseActivity){//关闭loading
            BaseActivity base = (BaseActivity) AppUtils.getTopActivity();
            base.dismissLoadingDialog();
        }*/
    }

    private void showNoNetWorkConnected() {
        //    ToastUtils.showTipsToast(MainApplication.getContext(),"网络不给力");
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);

    protected void _onError(BaseBean baseBean) {
    }
}
