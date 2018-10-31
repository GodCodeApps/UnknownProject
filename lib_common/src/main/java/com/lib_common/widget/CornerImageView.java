package com.lib_common.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * 圆角ImageView
 * author hechao
 * date 2018/9/6 0006
 */
public class CornerImageView extends android.support.v7.widget.AppCompatImageView {

    private final int CORNER_RADIUS = 15;
    private float mImageWidth, mImageHeight;

    public CornerImageView(Context context) {
        this(context, null);
    }

    public CornerImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CornerImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mImageWidth = getWidth();
        mImageHeight = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mImageWidth > CORNER_RADIUS && mImageHeight > CORNER_RADIUS) {
            @SuppressLint("DrawAllocation") Path path = new Path();
            path.moveTo(CORNER_RADIUS, 0);
            path.lineTo(mImageWidth - CORNER_RADIUS, 0);
            path.quadTo(mImageWidth, 0, mImageWidth, CORNER_RADIUS);
            path.lineTo(mImageWidth, mImageHeight - CORNER_RADIUS);
            path.quadTo(mImageWidth, mImageHeight, mImageWidth - CORNER_RADIUS, mImageHeight);
            path.lineTo(CORNER_RADIUS, mImageHeight);
            path.quadTo(0, mImageHeight, 0, mImageHeight - CORNER_RADIUS);
            path.lineTo(0, CORNER_RADIUS);
            path.quadTo(0, 0, CORNER_RADIUS, 0);
            canvas.clipPath(path);
        }
        super.onDraw(canvas);
    }
}
