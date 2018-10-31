package com.module_community.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lib_common.base.BaseFragment;
import com.lib_common.utils.constant.RouteConstants;
import com.module_community.BR;
import com.module_community.R;
import com.module_community.databinding.FragmentCommunityMainLayoutBinding;
import com.module_community.vm.CommunityViewModel;

/**
 * Peng YanMing on 2018\10\30 0030
 */
@Route(path = RouteConstants.Community.COMMUNITY_MAIN)
public class CommunityMainFragment extends BaseFragment<FragmentCommunityMainLayoutBinding, CommunityViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_community_main_layout;
    }

    @Override
    public int initVariableId() {
        return BR.vm;
    }

    @Override
    public CommunityViewModel initViewModel() {
        viewModel = new CommunityViewModel(getActivity());
        return viewModel;
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();
        viewModel.afterCreate();
    }
}
