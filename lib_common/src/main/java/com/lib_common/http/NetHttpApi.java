package com.lib_common.http;

import android.os.Build;

import com.alibaba.fastjson.JSONObject;
import com.lib_base.ServiceFactory;
import com.lib_common.utils.FormatUtil;
import com.lib_common.utils.string.MD5;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求Api
 */
public class NetHttpApi {
    private Retrofit mRetrofit;
    //请求超时时间
    private static final int REQUEST_TIME = 30;
    //读取超时时间
    private static final int READ_TIME = 30;
    //写入超时时间
    private static final int WRITE_TIME = 30;
    private static NetHttpApi instance;

    private NetHttpApi() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(REQUEST_TIME, TimeUnit.SECONDS)
                .readTimeout(READ_TIME, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();
                        Request.Builder builder = originalRequest.newBuilder();
                        if (originalRequest.method().equals("POST")) {
                            RequestBody requestBody = originalRequest.body();
                            if (requestBody instanceof PostJsonBody) {
                                String content = ((PostJsonBody) requestBody).getContent();
                              JSONObject obj =JSONObject.parseObject(content);
                                builder.post(getRequestBody(obj,originalRequest.url().toString()));
                            }
                        }
                        return chain.proceed(builder.build());
                    }
                })
                .addInterceptor(new LogInterceptor())
                .addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                if (Build.VERSION.SDK != null && Build.VERSION.SDK_INT > 13) {
                    Request request = chain.request()
                            .newBuilder()
                            .addHeader("source-terminal", "Android")   //操作系统名称（注：ios、android）//设备型号
                            .addHeader("device-model", Build.MODEL)         //设备型号
                            .addHeader("os-version", Build.VERSION.RELEASE)//操作系统版本号
                            .addHeader("Connection", "close")
                            .build();
                    return chain.proceed(request);
                }else{
                    Request request = chain.request()
                            .newBuilder()
                            .addHeader("source-terminal", "Android")   //操作系统名称（注：ios、android）//设备型号
                            .addHeader("device-model", Build.MODEL)         //设备型号
                            .addHeader("os-version", Build.VERSION.RELEASE)//操作系统版本号
                            //.addHeader("app-name", name);//应用名称
                            .build();
                    return chain.proceed(request);
                }
            }
        });
        mRetrofit = new Retrofit.Builder()
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(NetUrl.BASE_CLIENT_RES_URL)
                .build();

    }

    public static NetHttpApi getInstance() {
        if (instance == null) {
            synchronized (NetHttpApi.class) {
                if (instance == null) {
                    instance = new NetHttpApi();
                }
            }
        }
        return instance;
    }

    public <T> T getService(Class<T> service) {
        return mRetrofit.create(service);
    }

    /**
     * 添加公共参数
     * @param obj
     * @return
     */
    public static RequestBody getRequestBody(JSONObject obj, String Url) {
        if(null==obj){
            obj = new JSONObject();
        }
        if(ServiceFactory.getInstance().getAccountService().isLogin()&&null!=ServiceFactory.getInstance().getAccountService().getToken()){
            obj.put("token", ServiceFactory.getInstance().getAccountService().getToken());
        }
        if(ServiceFactory.getInstance().getAccountService().isLogin()&&null!=ServiceFactory.getInstance().getAccountService().getAccountId()){
            obj.put("uid", ServiceFactory.getInstance().getAccountService().getAccountId());
        }

        obj.put("sign", MD5.getStringMD5(FormatUtil.getSortList(obj)+(FormatUtil.getSortList(obj).equals("")?"":"&")+testSign));
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), obj.toString());
    }
    private static final String testSign = "a28c309a1aa0b6fb4c3cf4f3d7494cd1";
}
