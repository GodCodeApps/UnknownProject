package com.lib_common.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.text.TextUtils;

import com.diandian7.imageloader.manager.ImageLoaderManager;
import com.lib_common.inject.component.ApplicationComponent;
import com.lib_common.inject.component.DaggerApplicationComponent;
import com.lib_common.inject.module.ApplicationModule;
import com.lib_common.utils.KLog;
import com.lib_common.utils.Utils;
import com.lib_common.utils.constant.MemoryConstants;
import com.liulishuo.filedownloader.FileDownloader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by goldze on 2017/6/15.
 */

public abstract class BaseApplication extends Application implements Application.ActivityLifecycleCallbacks {
    private static BaseApplication sInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationComponent.Instance.init(
                DaggerApplicationComponent
                        .builder()
                        .applicationModule(new ApplicationModule(this))
                        .build());
        sInstance = this;
        //开启打印日志
        KLog.init(true);
        //初始化工具类
        Utils.init(this);
        //初始化下载组件
        FileDownloader.setup(this);
        //初始化图片加载配置
        ImageLoaderManager.getInstance().init(this, MemoryConstants.IMAGE_CACHE_DIR);
        //注册监听每个activity的生命周期,便于堆栈式管理
        registerActivityLifecycleCallbacks(this);
        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
//        // 设置是否为上报进程
//        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
//        strategy.setUploadProcess(processName == null || processName.equals(packageName));
//        CrashReport.initCrashReport(getApplicationContext());
//
//        UMConfigure.setLogEnabled(true);
//        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE,
//                null);
//
//        PlatformConfig.setWeixin(OtherLoginConstants.WECHAT_APP_ID, OtherLoginConstants.WECHAT_APP_SECRET);
//        PlatformConfig.setQQZone(OtherLoginConstants.QQ_APP_ID, OtherLoginConstants.QQ_APP_SECRET);
//        PlatformConfig.setSinaWeibo(OtherLoginConstants.WEIBO_APP_KEY, OtherLoginConstants.WEIBO_APP_SECRET, "http://sns.whalecloud.com");

    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Application 初始化
     */
    public abstract void initModuleApp(Application application);

    /**
     * 所有 Application 初始化后的自定义操作
     */
    public abstract void initModuleData(Application application);

    /**
     * 获得当前app运行的AppContext
     */
    public static BaseApplication getInstance() {
        return sInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        AppManager.getAppManager().addActivity(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {


    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        AppManager.getAppManager().removeActivity(activity);
    }
}
