package com.lib_common.http;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lib_base.ServiceFactory;
import com.lib_common.R;
import com.lib_common.base.AppManager;
import com.lib_common.base.BaseApplication;
import com.lib_common.bus.Messenger;
import com.lib_common.entity.BaseBean;
import com.lib_common.http.exception.ServerException;
import com.lib_common.utils.PreferencesUtils;
import com.lib_common.utils.constant.MessengerConstants;
import com.lib_common.utils.constant.RouteConstants;
import com.lib_common.utils.constant.SharePreferencesConstant;
import com.lib_common.widget.dialog.TwoBtnDialogFragment;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 处理返回的数据
 */
public class RxHelper {
    /**
     * 用来统一处理Http的resultCode,并将返回的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    public static <T> ObservableTransformer<BaseBean<T>, T> handleResult() {
        return new ObservableTransformer<BaseBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseBean<T>> upstream) {
                return upstream.flatMap(new Function<BaseBean<T>, Observable<T>>() {
                    @Override
                    public Observable<T> apply(BaseBean<T> result) throws Exception {
                        if (result.getCode() == 0) {
                            return createData(result.getData());
                        } else {
                            if (result.getCode() == 1010) {//token失效
                                AppManager.getAppManager().finishAllBugMainActivity();//关闭除了主页意外的页面
                                ARouter.getInstance().build(RouteConstants.LOGINACTIVITY).navigation();
                            } else if (result.getCode() == 1203) {//交易密码错误
                                PreferencesUtils.putInt(BaseApplication.getInstance(), ServiceFactory.getInstance().getAccountService().getAccountId()+ SharePreferencesConstant.TRANSACTION_PASSWORD_ERROR_COUNT, PreferencesUtils.getInt(BaseApplication.getInstance(), ServiceFactory.getInstance().getAccountService().getAccountId()+SharePreferencesConstant.TRANSACTION_PASSWORD_ERROR_COUNT, 0) + 1);
                                int count = 3 - PreferencesUtils.getInt(BaseApplication.getInstance(), ServiceFactory.getInstance().getAccountService().getAccountId()+SharePreferencesConstant.TRANSACTION_PASSWORD_ERROR_COUNT);
                                Messenger.getDefault().send("交易密码有误（还有" + count + "次机会）", MessengerConstants.TRANSACTION_PASSWORD_ERROR);
                            }

                            //重复关注、取消关注
                            else if (result.getCode() == 1305 || result.getCode() == 1304) {
                                return Observable.error(new ServerException(result));
                            } else if (result.getCode() == 1002) {
                                return Observable.error(new ServerException(result));
                            }else if (result.getCode() == 1403||result.getCode() == 1404) {//玩家有未完成订单
                              //  TwoBtnDialogFragment.getSingleButtonInstance("对方有订单进行中 请稍后再下单", "知道了", R.mipmap.ic_dialog_order, null).show(((AppCompatActivity) AppManager.getAppManager().currentActivity()).getSupportFragmentManager(), "clear");
                                return Observable.error(new Exception(""));
                            }
                        }
                        return Observable.error(new Exception(result.getMsg()));
                    }
                }).subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };

    }

    /**
     * 将数据存入subscriber
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(data);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });
    }

    /**
     * 生命周期绑定
     *
     * @param lifecycle Activity
     */
    public static <T> LifecycleTransformer<T> bindToLifecycle(Context lifecycle) {
        if (lifecycle instanceof LifecycleProvider) {
            return ((LifecycleProvider) lifecycle).bindToLifecycle();
        } else {
            throw new IllegalArgumentException("context not the LifecycleProvider type");
        }
    }

    /**
     * 生命周期绑定
     *
     * @param lifecycle Fragment
     */
    public static <T> LifecycleTransformer<T> bindToLifecycle(Fragment lifecycle) {
        if (lifecycle instanceof LifecycleProvider) {
            return ((LifecycleProvider) lifecycle).bindToLifecycle();
        } else {
            throw new IllegalArgumentException("fragment not the LifecycleProvider type");
        }
    }

    /**
     * 线程调度器
     */
    public static ObservableTransformer schedulersTransformer() {
        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> ObservableTransformer<BaseBean<T>, T> exceptionTransformer() {

        return new ObservableTransformer() {
            @Override
            public ObservableSource apply(Observable observable) {
                return (observable)
//                        .map(new HandleFuc<T>())  //这里可以取出BaseResponse中的Result
                        .onErrorResumeNext(new HttpResponseFunc<T>());
            }
        };
    }

    private static class HttpResponseFunc<T> implements Function<Throwable, Observable<T>> {
        @Override
        public Observable<T> apply(Throwable t) {
            return Observable.error(ExceptionHandle.handleException(t));
        }
    }

    private static class HandleFuc<T> implements Function<BaseBean<T>, T> {
        @Override
        public T apply(BaseBean<T> response) {
            if (!response.isOk())
                throw new RuntimeException(response.getCode() + "" + response.getMsg() != null ? response.getMsg() : "");
            return response.getData();
        }
    }
}
