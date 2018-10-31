package com.lib_common.utils;

import android.graphics.Bitmap;

/**
 * @author ZhongWeiZhi
 * @date 2018/10/13  10:49
 * @description
 */
public class BitmapUtils {
    /**
     * 以CenterCrop方式resize图片
     * @param src  原始图片
     * @param destWidth  目标图片宽度
     * @param destHeight 目标图片高度
     * @return
     */
    public static Bitmap resizeBitmapByCenterCrop(Bitmap src, int destWidth, int destHeight) {
        if (src == null || destWidth == 0 || destHeight == 0) {
            return null;
        }
        // 图片宽度
        int w = src.getWidth();
        // 图片高度
        int h = src.getHeight();
        // Imageview宽度
        int x = destWidth;
        // Imageview高度
        int y = destHeight;
        // 高宽比之差
        int temp = (y / x) - (h / w);
        /**
         * 判断高宽比例，如果目标高宽比例大于原图，则原图高度不变，宽度为(w1 = (h * x) / y)拉伸
         * 画布宽高(w1,h),在原图的((w - w1) / 2, 0)位置进行切割
         */

        if (temp > 0) {
            // 计算画布宽度
            int w1 = (h * x) / y;
            // 创建一个指定高宽的图片
            Bitmap newb = Bitmap.createBitmap(src, (w - w1) / 2, 0, w1, h);
            //原图回收
            src.recycle();
            return newb;
        } else {
            /**
             * 如果目标高宽比小于原图，则原图宽度不变，高度为(h1 = (y * w) / x),
             * 画布宽高(w, h1), 原图切割点(0, (h - h1) / 2)
             */

            // 计算画布高度
            int h1 = (y * w) / x;
            // 创建一个指定高宽的图片
            Bitmap newb = Bitmap.createBitmap(src, 0, (h - h1) / 2, w, h1);
            //原图回收
            src.recycle();
            return newb;
        }
    }
}
