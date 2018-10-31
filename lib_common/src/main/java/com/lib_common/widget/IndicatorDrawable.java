package com.lib_common.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.lib_common.R;

import java.lang.reflect.Field;

/**
 * @author ZhongWeiZhi
 * @date 2018/8/22  14:06
 * @description
 */
public class IndicatorDrawable extends Drawable {
    private int INDICATOR_MARGIN =0;
    private int INDICATOR_HEIGHT = 2;
    private int INDICATOR_RADIUS =0;

    private View view;
    private Paint paint;

    public IndicatorDrawable(View view) {
        this.view = view;
        this.paint = new Paint();
        INDICATOR_MARGIN =view.getResources().getDimensionPixelSize(R.dimen.sm_px_15);
        INDICATOR_HEIGHT =view.getResources().getDimensionPixelSize(R.dimen.sm_px_2);
        paint.setColor(view.getContext().getResources().getColor(R.color.color_ECB212));
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        int mIndicatorLeft = getIntValue("mIndicatorLeft");
        int mIndicatorRight = getIntValue("mIndicatorRight");
        int radius = INDICATOR_RADIUS;
        if (mIndicatorLeft >= 0 && mIndicatorRight > mIndicatorLeft) {
            canvas.drawRoundRect(new RectF(mIndicatorLeft +(mIndicatorRight-mIndicatorLeft-INDICATOR_MARGIN)/2, view.getHeight() - 3*INDICATOR_HEIGHT, mIndicatorRight - (mIndicatorRight-mIndicatorLeft-INDICATOR_MARGIN)/2, view.getHeight()-2*INDICATOR_HEIGHT), radius, radius, paint);
        }
    }

    private int getIntValue(String name) {
        try {
            Field f = view.getClass().getDeclaredField(name);
            f.setAccessible(true);
            Object obj = f.get(view);
            return (Integer) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }
}
