package com.lib_common.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 列表滑动时FloatingActionButton 显示、隐藏动画处理
 * author hechao
 * date 2018/8/18 0018
 */
public class FloatButtonBehavior extends FloatingActionButton.Behavior {

    private FastOutLinearInInterpolator mInterpolator = new FastOutLinearInInterpolator();
    boolean isAnimatingOut = false;

    public FloatButtonBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type);
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
        if (dyConsumed > 0 && !isAnimatingOut && child.getVisibility() == View.VISIBLE) {
//            animateOut(child);
            child.hide(new FloatingActionButton.OnVisibilityChangedListener() {
                @Override
                public void onHidden(FloatingActionButton fab) {
                    super.onHidden(fab);
                    fab.setVisibility(View.INVISIBLE);
                }
            });
        } else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE) {
//            animateIn(child);
            child.show();
        }
    }

    private void animateIn(View child) {
        child.setVisibility(View.VISIBLE);
        ViewCompat.animate(child).translationY(0).setInterpolator(mInterpolator).setListener(null).start();
    }

    private void animateOut(View child) {
//        child.setVisibility(View.INVISIBLE);
        ViewCompat.animate(child).translationY(child.getHeight()).setInterpolator(mInterpolator).setListener(new ViewPropertyAnimatorListener() {
            @Override
            public void onAnimationStart(View view) {
                isAnimatingOut = true;
            }

            @Override
            public void onAnimationEnd(View view) {
                isAnimatingOut = false;
                view.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onAnimationCancel(View view) {
                isAnimatingOut = false;
            }
        }).start();
    }


    private int getMarginBottom(View v) {
        int marginBottom = 0;
        final ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            marginBottom = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        }
        return marginBottom;
    }
}
