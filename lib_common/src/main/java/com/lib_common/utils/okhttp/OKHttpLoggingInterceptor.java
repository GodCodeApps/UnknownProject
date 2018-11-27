package com.lib_common.utils.okhttp;


import com.lib_common.utils.L;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSource;

/**
 * 自定义 OkHttp 日志拦截器
 *
 * @author Peng YanMing 2017/5/2
 */
public final class OKHttpLoggingInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        try {
            Buffer buffer = new Buffer();
            request.body().writeTo(buffer);
            L.d("--> " + request.method() + ' ' + request.url() + "\n" + buffer.readString(UTF8));
        } catch (NullPointerException e) {
            L.d("--> " + request.method() + ' ' + request.url());
        } catch (Exception e) {
            L.d("--> " + request.method() + ' ' + request.url() + ' ' + e.getLocalizedMessage());
        }

        Response response;
        try {
            response = chain.proceed(request);
        } catch (IOException e) {
            throw e;
        }
        try {
            BufferedSource source = response.body().source();
            source.request(Long.MAX_VALUE);
            String xml = source.buffer().clone().readString(UTF8);
            L.v("<-- " + request.url() + "\n" + source.buffer().clone().readString(UTF8));
        } catch (Exception e) {
            L.v("<-- " + request.url() + ' ' + e);
        }

        return response;
    }
}
