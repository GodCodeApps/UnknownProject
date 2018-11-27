package com.lib_common.utils.okhttp;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;


/**
 * OkHttp3 网络拦截器
 *
 * @author Peng YanMing 2017/4/28
 */
public final class OKHttpRefererNetworkInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(chain.request());
    }
}
