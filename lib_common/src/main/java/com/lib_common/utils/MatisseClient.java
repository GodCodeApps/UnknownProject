package com.lib_common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;

import com.lib_common.R;
import com.lib_common.base.BaseApplication;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.SelectionCreator;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.internal.entity.IncapableCause;
import com.zhihu.matisse.internal.entity.Item;

import java.util.EnumSet;
import java.util.Set;


/**
 * 照片选择封装
 * author hechao
 * date 2018/8/23 0023
 */
public class MatisseClient {

    public static SelectionCreator get(Activity activity) {
        return get(activity, MimeType.ofAll());
    }

    public static SelectionCreator get(Activity activity, Set<MimeType> set) {
        return launcher(getInstance(activity), set);
    }

    public static SelectionCreator get(Fragment fragment) {
        return get(fragment, MimeType.ofAll());
    }

    public static SelectionCreator get(Fragment fragment, Set<MimeType> set) {
        return launcher(getInstance(fragment), set);
    }

    private static Matisse getInstance(Activity activity) {
        return Matisse.from(activity);
    }

    private static Matisse getInstance(Fragment activity) {
        return Matisse.from(activity);
    }

    private static SelectionCreator launcher(Matisse matisse, Set<MimeType> set) {
        return matisse.choose(set)
                .maxOriginalSize(30)
                .captureStrategy(new CaptureStrategy(true, "com.diandian7.ddqcompetition.FileProvider"))
                .gridExpectedSize(BaseApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.sm_px_120))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT)
                .thumbnailScale(0.85f)
                .addFilter(new Filter() {
                    @Override
                    protected Set<MimeType> constraintTypes() {
                        return EnumSet.of(MimeType.AVI,  MimeType.MKV, MimeType.QUICKTIME, MimeType.TS, MimeType.MPEG, MimeType.BMP, MimeType.WEBM, MimeType.WEBP);
                    }

                    @Override
                    public IncapableCause filter(Context context, Item item) {
                        if (needFiltering(context, item)) {
                            return new IncapableCause(context.getString(R.string.error_file_type));
                        } else return null;
                    }
                }).imageEngine(new GlideEngine());
    }
}
