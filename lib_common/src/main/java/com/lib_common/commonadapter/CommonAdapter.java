package com.lib_common.commonadapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.annotation.CallSuper;
import android.view.View;


import com.lib_common.base.recycler.RecyclerHeaderFooterAdapter;
import com.lib_common.base.recycler.RecyclerHolder;
import com.lib_common.BR;
import java.util.List;

/**
 * 普通展示列表adapter
 * author hechao
 * date 2018/9/1 0001
 */
public class CommonAdapter<T, B extends ViewDataBinding> extends RecyclerHeaderFooterAdapter<T, B> {

    private OnItemClickListener mOnItemClickListener;

    public CommonAdapter(Context context, List<T> list, int layoutId) {
        super(context, list, layoutId);
    }

    public List<T> getList() {
        return mList;
    }

    @CallSuper
    @Override
    protected void onBind(RecyclerHolder<B> holder, int position, T t) {
        holder.binding.setVariable(BR.item, t);

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> mOnItemClickListener.onItemClick(v, position, t));
        }
        holder.binding.executePendingBindings();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(View view, int position, T t);
    }
}
