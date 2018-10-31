package com.lib_common.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.view.ViewConfiguration;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 屏幕工具类
 */
public class DisplayUtil {

    public static int dp2px(float dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(float pxValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int sp2px(float spValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scale + 0.5f);
    }

    public static int px2sp(float pxValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度 - 不包含底部导航栏
     */
    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static int getStatusHeight() {
        int resId = Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android");
        return resId > 0 ? Resources.getSystem().getDimensionPixelSize(resId) : 0;
    }

    /**
     * 获取软键盘高度 - 等于 0 时为软件盘隐藏状态
     */
    public static int getSoftKeyboardHeight(Activity activity) {
        Rect rect = new Rect(); //获取当前界面可视部分
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return getScreenHeight() - rect.bottom;
    }

    /**
     * 获取底部导航栏 - 虚拟键高度
     */
    public static int getNavigationBarHeight(Context context) {
        int result = 0;
        if (hasNavigationBar(context)) {
            Resources res = context.getResources();
            int resId = Resources.getSystem().getIdentifier("navigation_bar_height", "dimen", "android");
            if (resId > 0) {
                result = res.getDimensionPixelSize(resId);
            }
        }
        return result;
    }

    private static boolean hasNavigationBar(Context context) {
        int resId = Resources.getSystem().getIdentifier("config_showNavigationBar", "bool", "android");
        if (resId != 0) {
            boolean hasNav = Resources.getSystem().getBoolean(resId);
            // check override flag
            String sNavBarOverride = getNavigationBarOverride();
            if ("1".equals(sNavBarOverride)) {
                hasNav = false;
            } else if ("0".equals(sNavBarOverride)) {
                hasNav = true;
            }
            return hasNav;
        } else { // fallback
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }
    }

    /**
     * 判断虚拟按键栏是否重写
     */
    private static String getNavigationBarOverride() {
        String sNavBarOverride = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Class c = Class.forName("android.os.SystemProperties");
                Method m = c.getDeclaredMethod("get", String.class);
                m.setAccessible(true);
                sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return sNavBarOverride;
    }

    /**
     * 判断是否魅族手机
     */
    public static boolean isMeizu() {
        return Build.BRAND.equals("Meizu");
    }

    /**
     * 获取魅族手机底部虚拟键盘高度
     */
    public static int getSmartBarHeight() {
        int resId = Resources.getSystem().getIdentifier("mz_action_button_min_height", "dimen", "android");
        return resId > 0 ? Resources.getSystem().getDimensionPixelSize(resId) : 0;
    }

    /**
     * 判断当前界面是否是桌面
     */
    @SuppressWarnings("deprecation")
    public static boolean isHome(Context context) {
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> rti = mActivityManager.getRunningTasks(1);
        return getHomes(context).contains(rti.get(0).topActivity.getPackageName());
    }

    /**
     * 获得属于桌面的应用的应用包名称
     *
     * @return 返回包含所有包名的字符串列表
     */
    private static List<String> getHomes(Context context) {
        List<String> names = new ArrayList<>();
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo ri : resolveInfo) {
            names.add(ri.activityInfo.packageName);
        }
        return names;
    }
}

