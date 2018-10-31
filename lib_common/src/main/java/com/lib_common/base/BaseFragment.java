package com.lib_common.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lib_common.bus.BusEvent;
import com.lib_common.bus.Messenger;
import com.lib_common.bus.RxBus;
import com.lib_common.bus.RxBusSubscriber;
import com.trello.rxlifecycle2.components.support.RxFragment;


/**
 * Created by goldze on 2017/6/15.
 */
public abstract class BaseFragment<V extends ViewDataBinding, VM extends BaseViewModel> extends RxFragment implements IBaseActivity {
    protected V binding;
    protected VM viewModel;

    private boolean isPrepareView = false;  //用来确定View是否创建完成
    private boolean isInitData = false;     //数据是否加载完成
    private boolean isVisibleToUser = false;//Fragment是否可见

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initParam();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, initContentView(inflater, container, savedInstanceState), container, false);
        binding.setVariable(initVariableId(), viewModel = initViewModel());

        if (registerRxBus()) {
            RxBus.getDefault().toObservable(BusEvent.class).subscribe(new RxBusSubscriber<BusEvent>() {
                @Override
                protected void onEvent(BusEvent busEvent) {
                    eventComing(busEvent);
                }
            });
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViewObservable();

        viewModel.onCreate();

        viewModel.registerRxBus();

        isPrepareView = true;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lazyInitData();
    }

    @Override
    public void onDestroyView() {
        isInitData = false;
        isPrepareView = false;
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Messenger.getDefault().unregister(this.getContext());
        if(viewModel!=null){
        viewModel.removeRxBus();
        viewModel.onDestroy();
        }
        viewModel = null;
        isInitData = false;
        isPrepareView = false;
        binding.unbind();
    }

    @Override
    public void initParam() {

    }


    /**
     * Fragment可见状态变化时该方法被调用
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        lazyInitData();
    }

    protected boolean registerRxBus() {
        return false;
    }

    protected void eventComing(BusEvent t) {
    }

    /**
     * 刷新布局
     */
    public void refreshLayout() {
        if (viewModel != null) {
            binding.setVariable(initVariableId(), viewModel);
        }
    }

    /**
     * 懒加载
     */
    private void lazyInitData() {
        //全部符合条件才进行加载
        if (!isInitData && isPrepareView && isVisibleToUser) {
            isInitData = true;
            initData();
        }
    }

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    public abstract int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    public abstract int initVariableId();

    /**
     * 初始化ViewModel
     *
     * @return 继承BaseViewModel的ViewModel
     */
    public abstract VM initViewModel();

    /**
     * 懒加载数据
     */
    @Override
    public void initData() {
    }

    @Override
    public void initViewObservable() {

    }

    public boolean onBackPressed() {
        return false;
    }


    public interface FragmentCallback {
        public void callback(Object object);
    }
}
