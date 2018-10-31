package com.lib_common.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author ZhongWeiZhi
 * @date 2018/8/22  16:14
 * @description recyclerview行间距
 */
public class SpaceGridItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpace;

    public SpaceGridItemDecoration(int space) {
        this.mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top = mSpace;
        outRect.left = mSpace;
        outRect.right = mSpace;
        outRect.bottom = mSpace;
    }
}
