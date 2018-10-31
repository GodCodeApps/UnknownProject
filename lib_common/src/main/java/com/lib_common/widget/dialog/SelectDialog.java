package com.lib_common.widget.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.lib_common.R;
import com.lib_common.commonadapter.SelectActionAdapter;
import com.lib_common.entity.CardBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhongWeiZhi
 * @date 2018/9/12 21:01
 * @description 选择操作bottom
 */

public class SelectDialog extends BottomSheetDialog {

    private View.OnClickListener mOnDismissListener;
    private RecyclerView recyclerView;
    private List<CardBean> list = new ArrayList<>();
    private Context context;
    private SelectActionAdapter mAdapter;
    OnSelectListener listener;//选择监听

    public SelectDialog(@NonNull Context context, List<CardBean> list) {
        this(context, 0);
        this.context = context;
        this.list.addAll(list);
    }

    public SelectDialog(@NonNull Context context, int theme) {
        super(context, theme);
        this.context = context;
        init(context);
    }

    private void init(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_select, null);
        setContentView(view);
        recyclerView = view.findViewById(R.id.recyclerview);
        initAdapter(list);
        //给布局设置透明背景色
        getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet)
                .setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
//        divider.setDrawable(ContextCompat.getDrawable(context,R.drawable.custom_divider));
        recyclerView.addItemDecoration(divider);
        findViewById(R.id.text_cancel).setOnClickListener(v -> {
            dismiss();
            if (mOnDismissListener != null) {
                mOnDismissListener.onClick(v);
            }
        });
    }

    private void initAdapter(List<CardBean> list) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new SelectActionAdapter(context, list, new SelectActionAdapter.OnSelectClickListener() {
            @Override
            public void onSelectClick(CardBean bean) {
                dismiss();
                if (null != listener)
                    listener.onSelectClick(bean, SelectDialog.this);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    public SelectDialog setSelectListener(OnSelectListener listener) {
        this.listener = listener;
        return this;
    }

    public SelectDialog setDismissListener(View.OnClickListener onClickListener) {
        this.mOnDismissListener = onClickListener;
        return this;
    }

    public interface OnSelectListener {
        void onSelectClick(CardBean bean, SelectDialog dialog);
    }
}
