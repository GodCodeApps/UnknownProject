package com.lib_common.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2017/10/19.
 */

public class SpaceHorizontalItemDecoration extends RecyclerView.ItemDecoration{

    private int space;
    private int headSpace;
    public SpaceHorizontalItemDecoration(int space, int headSpace) {
        this.space = space;
        this.headSpace = headSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if(parent.getChildPosition(view) != 0) {
            outRect.left = space;
        }else{
            outRect.left =headSpace;
        }
    }
}
