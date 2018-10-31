package com.lib_common.widget.wheel;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.contrarywind.view.WheelView;

/**
 * Peng YanMing on 2018\9\4 0004
 */
public class CustomWheelView extends WheelView {
    public CustomWheelView(Context context) {
        super(context);
    }

    public CustomWheelView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(event);
    }

}
