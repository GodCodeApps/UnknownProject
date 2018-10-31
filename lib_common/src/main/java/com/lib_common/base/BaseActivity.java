package com.lib_common.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.lib_common.R;
import com.lib_common.binding.command.BindingCommand;
import com.lib_common.bus.BusEvent;
import com.lib_common.bus.Messenger;
import com.lib_common.bus.RxBus;
import com.lib_common.bus.RxBusSubscriber;
import com.lib_common.utils.constant.MemoryConstants;
import com.lib_common.utils.qmuihelper.QMUIDisplayHelper;
import com.lib_common.utils.qmuihelper.QMUIStatusBarHelper;
import com.lib_common.widget.IndicatorDrawable;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;


/**
 * Created by goldze on 2017/6/15.
 * 一个拥有DataBinding框架的基Activity
 * 这里根据项目业务可以换成你自己熟悉的BaseActivity, 但是需要继承RxAppCompatActivity,方便LifecycleProvider管理生命周期
 */

public abstract class BaseActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends RxAppCompatActivity implements IBaseActivity, WeakHandlerCallback {
    protected Context mContext;
    protected V binding;
    protected VM viewModel;
    private Menu mMenu;
    private int mTabSelectedIndex = 0;//tabLayout 默认选中项

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        initParam();
        initViewDataBinding(savedInstanceState);
        super.onCreate(savedInstanceState);
        initViewObservable();
        initData();
        viewModel.onCreate();
        viewModel.registerRxBus();

        if (registerRxBus()) {
            RxBus.getDefault().toObservable(BusEvent.class).subscribe(new RxBusSubscriber<BusEvent>() {
                @Override
                protected void onEvent(BusEvent busEvent) {
                    eventComing(busEvent);
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (registerRxBus()) {
            RxBus.getDefault().removeStickyEvent(BusEvent.class);
        }
        Messenger.getDefault().unregister(this);
        viewModel.removeRxBus();
        viewModel.onDestroy();
        viewModel = null;
        binding.unbind();
    }

    /**
     * 注入绑定
     */
    private void initViewDataBinding(Bundle savedInstanceState) {
        QMUIStatusBarHelper.translucent(this);

        //DataBindingUtil类需要在project的build中配置 dataBinding {enabled true }, 同步后会自动关联android.databinding包
        binding = DataBindingUtil.setContentView(this, initContentView(savedInstanceState));
        binding.setVariable(initVariableId(), viewModel = initViewModel());

        AppBarLayout appBarLayout = binding.getRoot().findViewById(R.id.appbar);
        CollapsingToolbarLayout toolbarLayout = binding.getRoot().findViewById(R.id.toolbarLayout);
        Toolbar toolbar = binding.getRoot().findViewById(R.id.toolbar);
        TabLayout tabLayout = binding.getRoot().findViewById(R.id.tabLayout);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            toolbar.setNavigationOnClickListener(v -> {
                if (onNavigationClick() == null) {
                    onBackPressed();
                } else onNavigationClick().execute();
            });
            toolbar.setTitle(getTitleText());
        }

        int tabNormalTextSize = getResources().getDimensionPixelSize(R.dimen.sm_px_16);
        int tabTopTextSize = getResources().getDimensionPixelSize(R.dimen.sm_px_18);
        int tabSelectedTextSize = getResources().getDimensionPixelSize(R.dimen.sm_px_24);

        //处理带tabLayout标题栏滑动
        if (tabLayout != null && appBarLayout != null) {
            String[] titles = getTabTitles();
            if (titles != null && titles.length > 0) {
                for (int i = 0; i < titles.length; i++) {
                    TextView textView = new TextView(this);
                    textView.setText(titles[i]);
                    if (i == mTabSelectedIndex) {
                        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabSelectedTextSize);
                        textView.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                    } else {
                        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabNormalTextSize);
                        textView.setTextColor(ContextCompat.getColor(this, R.color.color_747474));
                    }
                    tabLayout.addTab(tabLayout.newTab().setCustomView(textView));
                }
            }
            View tabStripView = tabLayout.getChildAt(0);
            tabStripView.setBackground(new IndicatorDrawable(tabStripView));//设置背景 添加自定义下划线

            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    changeTabStatus(true, tab);
                    getViewPager().setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                    changeTabStatus(false, tab);
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });

            getViewPager().addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    tabLayout.getTabAt(position).select();

                    if (GSYVideoManager.instance().isPlaying()) {
                        GSYVideoManager.releaseAllVideos();
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });

            int statusBarHeight = QMUIDisplayHelper.getStatusBarHeight(this);
            int actionBarSize = 44;
            TypedValue tv = new TypedValue();
            if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
                actionBarSize = TypedValue.complexToDimensionPixelSize(tv.data, this.getResources().getDisplayMetrics());
            }

            float screenWidthHalf = QMUIDisplayHelper.getScreenWidth(this) / 2;
            appBarLayout.setLayoutParams(new CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.WRAP_CONTENT, 2 * actionBarSize + MemoryConstants.NOTCH_HEIGHT));
            float toolbarHeight = actionBarSize - statusBarHeight + MemoryConstants.NOTCH_HEIGHT;
            int textSizeDiff = tabTopTextSize - tabNormalTextSize;

            appBarLayout.addOnOffsetChangedListener((appBar, verticalOffset) -> {
                float distanceSubscribeX = screenWidthHalf - (tabLayout.getWidth() / 2.0f);
                float scaleX = distanceSubscribeX / toolbarHeight;
                tabLayout.setTranslationX(-scaleX * verticalOffset);

                //处理滑动时字体大小变化
                for (int i = 0; i < tabLayout.getTabCount(); i++) {
                    TabLayout.Tab tab = tabLayout.getTabAt(i);
                    if (tab != null) {
                        TextView text = (TextView) tab.getCustomView();
                        float newTextSize;
                        if (!tab.isSelected()) {
                            newTextSize = tabNormalTextSize - ((float) textSizeDiff / (float) appBarLayout.getTotalScrollRange()) * verticalOffset;
                        } else {
                            newTextSize = tabSelectedTextSize - ((float) (tabTopTextSize - tabSelectedTextSize) / (float) appBarLayout.getTotalScrollRange()) * verticalOffset;
                        }
                        text.setTextSize(TypedValue.COMPLEX_UNIT_PX, newTextSize);
                    }
                }
            });
        } else {
            if (MemoryConstants.NOTCH_HEIGHT > 0 && appBarLayout != null) {
                appBarLayout.setLayoutParams(new CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.WRAP_CONTENT, getResources().getDimensionPixelSize(R.dimen.sm_px_88) + MemoryConstants.NOTCH_HEIGHT));
            }
        }

        if (isLightMode()) {
            QMUIStatusBarHelper.setStatusBarLightMode(this);

            if (appBarLayout != null) {
                appBarLayout.setBackgroundResource(R.color.white);
            }

            if (toolbarLayout != null) {
                toolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, getTitleTextColor() == 0 ? R.color.black : getTitleTextColor()));
                toolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, getTitleTextColor() == 0 ? R.color.black : getTitleTextColor()));
            }

            if (toolbar != null) {
                toolbar.setNavigationIcon(getNavigationIcon() == 0 ? R.mipmap.back_icon : getNavigationIcon());
                toolbar.setTitleTextColor(ContextCompat.getColor(this, getTitleTextColor() == 0 ? R.color.black : getTitleTextColor()));
            }
        } else {
            QMUIStatusBarHelper.setStatusBarDarkMode(this);

            if (appBarLayout != null) {
                appBarLayout.setBackgroundResource(android.R.color.transparent);
            }

            if (toolbarLayout != null) {
                toolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, getTitleTextColor() == 0 ? R.color.white : getTitleTextColor()));
                toolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, getTitleTextColor() == 0 ? R.color.white : getTitleTextColor()));
            }

            if (toolbar != null) {
                toolbar.setNavigationIcon(getNavigationIcon() == 0 ? R.mipmap.back_white_icon : getNavigationIcon());
                toolbar.setTitleTextColor(ContextCompat.getColor(this, getTitleTextColor() == 0 ? R.color.white : getTitleTextColor()));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getMenuLayoutId() != 0) {
            mMenu = menu;
            getMenuInflater().inflate(getMenuLayoutId(), menu);
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (GSYVideoManager.instance().isPlaying()) {
            GSYVideoManager.releaseAllVideos();
        }
    }

    protected Menu getMenu() {
        return this.mMenu;
    }


    /**
     * 状态栏Light模式(图标、文字黑色)
     * Dark模式(图标、文字白色)
     *
     * @return
     */
    protected boolean isLightMode() {
        return true;
    }

    /**
     * 标题文字
     *
     * @return
     */
    protected CharSequence getTitleText() {
        return "";
    }

    protected String[] getTabTitles() {
        return new String[0];
    }

    protected void setTabSelectedIndex(int index) {
        this.mTabSelectedIndex = index;
        if (binding != null) {
            TabLayout tabLayout = binding.getRoot().findViewById(R.id.tabLayout);
            tabLayout.getTabAt(index).select();
        }
    }

    protected ViewPager getViewPager() {
        return null;
    }

    protected void setTitleText(CharSequence charSequence) {
        if (binding != null) {
            CollapsingToolbarLayout toolbarLayout = binding.getRoot().findViewById(R.id.toolbarLayout);
            if (toolbarLayout != null) {
                toolbarLayout.setTitle(charSequence);
            } else {
                Toolbar toolbar = binding.getRoot().findViewById(R.id.toolbar);
                toolbar.setTitle(charSequence);
            }
        }
    }

    /**
     * 设置appbar展开状态
     *
     * @param expanded
     */
    protected void setToolbarExpanded(boolean expanded) {
        if (binding != null) {
            AppBarLayout appBarLayout = binding.getRoot().findViewById(R.id.appbar);
            if (appBarLayout != null) {
                appBarLayout.setExpanded(expanded);
            }
        }
    }

    /**
     * 标题文字颜色
     *
     * @return
     */
    protected int getTitleTextColor() {
        return 0;
    }

    /**
     * 自定义左边返回按钮图标
     *
     * @return
     */
    protected int getNavigationIcon() {
        return 0;
    }

    /**
     * 自定义左边按钮点击事件
     *
     * @return
     */
    protected BindingCommand onNavigationClick() {
        return null;
    }

    /**
     * 右边菜单
     *
     * @return
     */
    protected int getMenuLayoutId() {
        return 0;
    }

    //刷新布局
    public void refreshLayout() {
        if (viewModel != null) {
            binding.setVariable(initVariableId(), viewModel);
        }
    }

    @Override
    public void initParam() {

    }

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    public abstract int initContentView(Bundle savedInstanceState);

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

    @Override
    public void initData() {

    }

    @Override
    public void initViewObservable() {
    }

    protected boolean registerRxBus() {
        return false;
    }

    protected void eventComing(BusEvent t) {
    }

    /**
     * 消息队列回调监听
     */
    private Handler.Callback mCallback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return BaseActivity.this.handleMessage(msg);
        }
    };

    /**
     * 弱引用handler
     */
    private Handler mHandler = new Handler(mCallback);

    /**
     * 弱引用handler
     * 防止内存泄露
     *
     * @return
     * @author LiJiaJian
     */
    public Handler getHandler() {
        return mHandler;
    }

    /**
     * Handler回调
     *
     * @param msg
     * @return
     * @author LiJiaJian
     */
    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

    private void changeTabStatus(boolean checked, TabLayout.Tab tab) {
        View view = tab.getCustomView();
        if (view != null && view instanceof TextView) {
            TextView text = (TextView) view;
            if (checked) {
                text.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.sm_px_24));
                text.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
            } else {
                text.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.sm_px_16));
                text.setTextColor(ContextCompat.getColor(this, R.color.color_747474));
            }
        }
    }
}
