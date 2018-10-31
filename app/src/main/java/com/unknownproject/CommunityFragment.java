package com.unknownproject;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.lib_common.base.BaseFragment;
import com.lib_common.base.BaseViewModel;

/**
 * Peng YanMing on 2018\10\30 0030
 */
public class CommunityFragment extends BaseFragment<ViewDataBinding,BaseViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_community;
    }

    @Override
    public int initVariableId() {
        return BR.vm;
    }

    @Override
    public BaseViewModel initViewModel() {
        return new BaseViewModel(getActivity());
    }
}
