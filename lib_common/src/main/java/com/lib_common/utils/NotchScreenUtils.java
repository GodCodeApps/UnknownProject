package com.lib_common.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.DisplayCutout;
import android.view.View;
import android.view.WindowInsets;

import com.lib_common.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 留海屏工具类
 * author hechao
 * date 2018/10/11 0011
 */
public class NotchScreenUtils {

    public final static int DEVICE_BRAND_OPPO = 0x0001;
    public final static int DEVICE_BRAND_HUAWEI = 0x0002;
    public final static int DEVICE_BRAND_VIVO = 0x0003;
    public final static int DEVICE_BRAND_XIAOMI = 0x0004;


    /**
     * 判断是否是刘海屏
     *
     * @return
     */
    public static boolean hasNotchScreen(Activity activity) {
        if (getInt("ro.miui.notch", activity) == 1 || hasNotchAtHuawei(activity) || hasNotchAtOPPO(activity)
                || hasNotchAtVivo(activity) || isAndroidP(activity) != null) { //TODO 各种品牌
            return true;
        }
        return false;
    }

    /**
     * 获取留海高度、宽度
     *
     * @param activity
     * @return
     */
    public static int getNotchSize(Activity activity) {
        switch (getDeviceBrand()) {
            case DEVICE_BRAND_OPPO:
                return getNotchSizeAtOppo();
            case DEVICE_BRAND_HUAWEI:
                return getNotchSizeAtHuawei(activity);
            case DEVICE_BRAND_VIVO:
                return getNotchSizeAtVivo(activity);
            case DEVICE_BRAND_XIAOMI:
                return getNotchSizeAtXiaomi(activity);
            default:
                DisplayCutout displayCutout = isAndroidP(activity);
                if (displayCutout != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        return displayCutout.getSafeInsetTop() - 20;
                    }
                }
                break;
        }
        return 0;
    }

    /**
     * Android P 刘海屏判断
     *
     * @param activity
     * @return
     */
    public static DisplayCutout isAndroidP(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        if (decorView != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            WindowInsets windowInsets = decorView.getRootWindowInsets();
            if (windowInsets != null)
                return windowInsets.getDisplayCutout();
        }
        return null;
    }

    /**
     * 小米刘海屏判断.
     *
     * @return 0 if it is not notch ; return 1 means notch
     * @throws IllegalArgumentException if the key exceeds 32 characters
     */
    public static int getInt(String key, Activity activity) {
        int result = 0;
        if (isXiaomi()) {
            try {
                ClassLoader classLoader = activity.getClassLoader();
                @SuppressWarnings("rawtypes")
                Class SystemProperties = classLoader.loadClass("android.os.SystemProperties");
                //参数类型
                @SuppressWarnings("rawtypes")
                Class[] paramTypes = new Class[2];
                paramTypes[0] = String.class;
                paramTypes[1] = int.class;
                Method getInt = SystemProperties.getMethod("getInt", paramTypes);
                //参数
                Object[] params = new Object[2];
                params[0] = new String(key);
                params[1] = new Integer(0);
                result = (Integer) getInt.invoke(SystemProperties, params);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 华为刘海屏判断
     *
     * @return
     */
    public static boolean hasNotchAtHuawei(Activity activity) {
        boolean ret = false;
        try {
            ClassLoader classLoader = activity.getClassLoader();
            Class HwNotchSizeUtil = classLoader.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("hasNotchInScreen");
            ret = (boolean) get.invoke(HwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
            KLog.e("hasNotchAtHuawei ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            KLog.e("hasNotchAtHuawei NoSuchMethodException");
        } catch (Exception e) {
            KLog.e("hasNotchAtHuawei Exception");
        } finally {
            return ret;
        }
    }

    public static final int VIVO_NOTCH = 0x00000020;//是否有刘海
    public static final int VIVO_FILLET = 0x00000008;//是否有圆角

    /**
     * VIVO刘海屏判断
     *
     * @return
     */
    public static boolean hasNotchAtVivo(Activity activity) {
        boolean ret = false;
        try {
            ClassLoader classLoader = activity.getClassLoader();
            Class FtFeature = classLoader.loadClass("android.util.FtFeature");
            Method method = FtFeature.getMethod("isFeatureSupport", int.class);
            ret = (boolean) method.invoke(FtFeature, VIVO_NOTCH);
        } catch (ClassNotFoundException e) {
            KLog.e("hasNotchAtVivo ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            KLog.e("hasNotchAtVivo NoSuchMethodException");
        } catch (Exception e) {
            KLog.e("hasNotchAtVivo Exception");
        } finally {
            return ret;
        }
    }

    /**
     * OPPO刘海屏判断
     *
     * @return
     */
    public static boolean hasNotchAtOPPO(Activity activity) {
        return activity.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    /**
     * 是否是小米手机
     */
    public static boolean isXiaomi() {
        return "Xiaomi".equals(Build.MANUFACTURER);
    }

    /**
     * 获取华为刘海的高
     *
     * @param context
     * @return
     */
    public static int getNotchSizeAtHuawei(Context context) {
        int[] ret = new int[]{0, 0};
        try {
            ClassLoader cl = context.getClassLoader();
            Class<?> HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("getNotchSize");
            ret = (int[]) get.invoke(HwNotchSizeUtil);

        } catch (ClassNotFoundException e) {
            KLog.e("NotchScreenUtil", "getNotchSize ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            KLog.e("NotchScreenUtil", "getNotchSize NoSuchMethodException");
        } catch (Exception e) {
            KLog.e("NotchScreenUtil", "getNotchSize Exception");
        }
        return ret[1];
    }

    public static int getNotchSizeAtXiaomi(Context context) {
//        int resourceId = context.getResources().getIdentifier("notch_height", "dimen", "android");
//        if (resourceId > 0) {
//            return context.getResources().getDimensionPixelSize(resourceId);
//        }
//        return 0;
        return context.getResources().getDimensionPixelSize(R.dimen.sm_px_35);
    }

    public static int getNotchSizeAtVivo(Context context) {
        return DisplayUtil.dp2px(32);
    }

    public static int getNotchSizeAtOppo() {
        return 80;
    }

    /**
     * 获取手机品牌
     *
     * @return
     */
    public static int getDeviceBrand() {
        String brand = Build.BRAND.trim().toUpperCase();
        if (brand.contains("HUAWEI")) {
            KLog.d("device brand", "HUAWEI");
            return DEVICE_BRAND_HUAWEI;
        } else if (brand.contains("XIAOMI")) {
            KLog.d("device brand", "XIAOMI");
            return DEVICE_BRAND_XIAOMI;
        } else if (brand.contains("OPPO")) {
            KLog.d("device brand", "OPPO");
            return DEVICE_BRAND_OPPO;
        } else if (brand.contains("VIVO")) {
            KLog.d("device brand", "VIVO");
            return DEVICE_BRAND_VIVO;
        }
        return 0;
    }
}
