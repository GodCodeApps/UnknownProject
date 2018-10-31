package com.module_message.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lib_common.base.BaseFragment;
import com.lib_common.base.BaseViewModel;
import com.lib_common.utils.constant.MemoryConstants;
import com.lib_common.utils.constant.RouteConstants;
import com.lib_common.utils.qmuihelper.QMUIDisplayHelper;
import com.module_message.BR;
import com.module_message.R;
import com.module_message.databinding.FragmentMsgMainLayoutBinding;

/**
 * Peng YanMing on 2018\10\30 0030
 */
@Route(path = RouteConstants.Message.MESSAGE_MAIN)
public class MsgMainFragment extends BaseFragment<FragmentMsgMainLayoutBinding,BaseViewModel>{
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_msg_main_layout;
    }

    @Override
    public int initVariableId() {
        return BR.vm;
    }

    @Override
    public BaseViewModel initViewModel() {
        return new BaseViewModel(getActivity());
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        initTitle();
    }

    private void initTitle() {
        int statusBarHeight1 = -1;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);
        }
        int actionBarHeight = 44;
        TypedValue tv = new TypedValue();
        if (getActivity().getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getActivity().getResources().getDisplayMetrics());
        }
        int tabTopTextSize = getResources().getDimensionPixelSize(R.dimen.sm_px_18);
        int tabSelectedTextSize = getResources().getDimensionPixelSize(R.dimen.sm_px_24);
        float screenWidthHalf = (float) (QMUIDisplayHelper.getScreenWidth(getContext()) / 2);
        float offset = QMUIDisplayHelper.DENSITY < 3 ? QMUIDisplayHelper.DENSITY * 10 : 0;
        binding.appBar.setLayoutParams(new CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.WRAP_CONTENT, (int) (2 * actionBarHeight + offset + MemoryConstants.NOTCH_HEIGHT)));
        final float toolbarHeight = actionBarHeight - statusBarHeight1 + offset + MemoryConstants.NOTCH_HEIGHT;
        binding.appBar.addOnOffsetChangedListener((AppBarLayout.OnOffsetChangedListener) (appBarLayout, verticalOffset) -> {
            float distanceSubscribeX = screenWidthHalf - (binding.title.getWidth() / 2);
            float scaleX = distanceSubscribeX / toolbarHeight;
            binding.title.setTranslationX(-scaleX * verticalOffset);
            float newTextSize = tabSelectedTextSize - ((float) (tabTopTextSize - tabSelectedTextSize) / (float) binding.appBar.getTotalScrollRange()) * verticalOffset;
            binding.title.setTextSize(TypedValue.COMPLEX_UNIT_PX, newTextSize);
        });
    }
}


