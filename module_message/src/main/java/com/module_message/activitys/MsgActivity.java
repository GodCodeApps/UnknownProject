package com.module_message.activitys;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lib_common.base.BaseActivity;
import com.lib_common.base.BaseViewModel;
import com.lib_common.utils.constant.RouteConstants;
import com.module_message.BR;
import com.module_message.R;
import com.module_message.databinding.MainMsgLayoutBinding;

/**
 * Peng YanMing on 2018\10\30 0030
 */
public class MsgActivity extends BaseActivity<MainMsgLayoutBinding,BaseViewModel> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_msg_layout);
    }

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.main_msg_layout;
    }

    @Override
    public int initVariableId() {
        return BR.vm;
    }

    @Override
    public BaseViewModel initViewModel() {
        return new BaseViewModel(this);
    }
}
