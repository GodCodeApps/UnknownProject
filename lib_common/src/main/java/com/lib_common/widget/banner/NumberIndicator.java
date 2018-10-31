package com.lib_common.widget.banner;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.lib_common.R;


public class NumberIndicator extends TextView {
    public NumberIndicator(Context context) {
        super(context);
        setTextColor(Color.WHITE);
        setTextSize(14);
        setBackgroundResource(R.drawable.shape_point_unselect);
        int padding = DensityUtils.dp2px(context, 5);
        setPadding(padding,padding,padding,padding);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
