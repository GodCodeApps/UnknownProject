package com.lib_common.base.recycler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.ObservableBoolean;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.lib_common.base.BaseViewModel;
import com.lib_common.commonadapter.VideoListAdapter;
import com.shuyu.gsyvideoplayer.GSYVideoManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import com.lib_common.databinding.FooterLoadingBinding;
/**
 * 分页 ViewModel 基类
 *
 * @author Peng YanMing 2018/8/29
 */
public abstract class PagingViewModel<T, A extends RecyclerHeaderFooterAdapter>
        extends BaseViewModel implements SwipeRefreshLayout.OnRefreshListener {

    public final ObservableBoolean refreshable = new ObservableBoolean(true);

    protected FooterViewModel mFooterViewModel;

    protected boolean pagingHaveMore = false;   // 有无更多
    protected boolean pagingLoading = false;    // 分页数据加载中
    protected int pagingLimit = 20;             // 分页数据大小
    protected int pagingOffset = 1;             // 分页页码
    protected int pagingPreCount = 0;           // 分页数据预加载 item 数量
    private int mFirstVisibleItem, mLastVisibleItem;

    protected List<T> mList = new ArrayList<>(); // 有无更多
    public A adapter; // 有无更多

    /*
     * 数据分条更新时刷新清空标记
     */
    private boolean clearFlag;

    public PagingViewModel(Context context) {
        super(context);
    }

    public PagingViewModel(Fragment fragment) {
        super(fragment);
    }

    @Override
    public void afterCreate() {
        afterCreate(true);
    }

    @Override
    public void afterCreate(boolean autoRequest) {
        initAdapter();
        initFooterBinding();
        notifyChange();
        if (autoRequest) {
            getData(false);
        }
    }

    public PagingViewModel getModel() {
        return this;
    }

    protected void initFooterBinding() {
        FooterLoadingBinding loadingBinding = FooterLoadingBinding.inflate(LayoutInflater.from(context));
        mFooterViewModel = new FooterViewModel(context);
        mFooterViewModel.afterCreate();
        loadingBinding.setVm(mFooterViewModel);
        adapter.setFooterBinding(loadingBinding);
    }

    /**
     * 通过这个方法初始化列表适配器
     */
    protected abstract void initAdapter();

    /**
     * 通过这个方法加载分页数据
     */
    protected abstract void getData(boolean isMore);

    /**
     * SwipeRefreshLayout.OnRefreshListener 触发事件
     */
    @Override
    public void onRefresh() {
        getData(false);
    }

    /**
     * 分页数据加载前
     */
    protected void doOnSubscribe(boolean isMore) {
        pagingLoading = true;
        if (isMore) {
            mFooterViewModel.notifyStateChanged(FooterViewModel.STATE_LOADING);
        } else {
            clearFlag = true;
            loading.set(true);
            empty.set(false);
            error.set(false);
            pagingOffset = 0;
        }
        pagingOffset++;
    }

    /**
     * 分页数据接收展示 - 单次单页
     */
    protected void accept(boolean isMore, List<T> newList) {
        if (!isMore) mList.clear();
        if (newList != null) {
            mList.addAll(newList);
            if (newList.size() == 0) {
                if (!isMore) {
                    empty.set(true);
                }
            }
        }

        adapter.notifyDataSetChanged();
    }

    /**
     * 分页数据接收展示 - 单次单条
     */
    protected void accept(boolean isMore, T t) {
        if (!isMore && clearFlag) {
            clearFlag = false;
            mList.clear();
            adapter.notifyDataSetChanged();
        }
        mList.add(t);
        adapter.notifyItemInserted(mList.size() - 1);
    }

    /**
     * 分页数据加载完成
     */
    protected void doOnComplete(boolean isMore) {
        refreshComplete();
        mFooterViewModel.notifyStateChanged(isMore ? FooterViewModel.STATE_IDLE : (empty.get() == true ? FooterViewModel.STATE_GONE : FooterViewModel.STATE_PERIOD));
        pagingLoading = false;
    }





    /**
     * 分页数据加载出错
     */
    protected void doOnError(boolean isMore, Throwable e) {
        refreshComplete();
        notifyMsg(e.getMessage());
        if (isMore) {
            mFooterViewModel.notifyStateChanged(FooterViewModel.STATE_ERROR);
        } else {
            error.set(true);
        }
        pagingOffset--;
        pagingLoading = false;
    }

    /**
     * 下拉数据为空
     */
    protected void doOnEmpty(boolean isMore) {
        refreshComplete();
        pagingLoading = false;
        if (!isMore) {
            empty.set(true);
        }
        pagingLoading = false;
    }


    /**
     * RecyclerView 自定加载更多触发装置
     */
    // 使用 DiffUtil 可解决 当数据量少时下拉刷新后不会再触发加载事件
    public RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                mFirstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
            }

            //得到当前显示的最后一个item的view
            View lastChildView = recyclerView.getLayoutManager().getChildAt(recyclerView.getLayoutManager().getChildCount() - 1);
            if (lastChildView != null) {
                //通过这个lastChildView得到这个view当前的position值
                mLastVisibleItem = recyclerView.getLayoutManager().getPosition(lastChildView);
                //判断lastPosition是不是最后一个position
                if (mLastVisibleItem > recyclerView.getLayoutManager().getItemCount() - 2 - pagingPreCount) {
                    performPagingLoad();
                }
            }

            //当前播放的位置
            int position = GSYVideoManager.instance().getPlayPosition() + (adapter.getHeaderBinding() == null ? 0 : 1);
            //大于0说明有播放
            if (position >= 0) {

                //对应的播放列表TAG
                if (GSYVideoManager.instance().getPlayTag().equals(VideoListAdapter.class.getCanonicalName())
                        && (position < mFirstVisibleItem || position > mLastVisibleItem)) {
                    GSYVideoManager.releaseAllVideos();
                    adapter.notifyDataSetChanged();
                }
            }
        }
    };

    @SuppressLint("CheckResult")
    private void refreshComplete() {
        Observable.timer(650, TimeUnit.MILLISECONDS).subscribe(aLong -> loading.set(false));
    }

    /**
     * 执行加载更多
     */
    public void performPagingLoad() {
        if (pagingHaveMore && !pagingLoading) {
            pagingLoading = true;
            getData(true);
        }
    }

    protected void notifyMsg(int state, @Nullable String s) {
        super.notifyMsg(s);
        this.mFooterViewModel.notifyStateChanged(state, s);
    }
}
