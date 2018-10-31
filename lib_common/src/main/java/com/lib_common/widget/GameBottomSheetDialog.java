package com.lib_common.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.contrarywind.view.WheelView;
import com.lib_common.R;
import com.lib_common.entity.CardBean;
import com.lib_common.utils.ConvertUtils;
import com.lib_common.widget.wheel.CustomWheelAdapter;

import java.util.List;

/**
 * Peng YanMing on 2018\9\4 0004
 */
public class GameBottomSheetDialog extends BottomSheetDialog {
    public Context context;
    private View view;
    private List<CardBean> list;
    public OnOkListener listener;
    private String title = "";
private int currentItem=0;
    public GameBottomSheetDialog(@NonNull Context context, String title,int currentItem, List<CardBean> beanList) {
        super(context);
        this.context = context;
        list = beanList;
        this.title = title;
        this.currentItem=currentItem;
        setLayoutContentView();
    }

    public GameBottomSheetDialog(@NonNull Context context, int theme) {
        super(context, theme);
    }

    protected GameBottomSheetDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private void setLayoutContentView() {
        view = LayoutInflater.from(context).inflate(R.layout.wheel_game_class, null);
        setContentView(view);
        ViewGroup parent = (ViewGroup) view.getParent();
        parent.setBackgroundResource(R.color.transparent);
        TextView tv_cancel = view.findViewById(R.id.tv_cancel);
        TextView tv_title = view.findViewById(R.id.tv_title);
        TextView tv_finish = view.findViewById(R.id.tv_finish);
        WheelView wheelView = view.findViewById(R.id.wheel);
        wheelView.setCyclic(false);
        wheelView.setCurrentItem(currentItem);
        wheelView.setTextSize(ConvertUtils.px2sp(context.getResources().getDimensionPixelSize(R.dimen.sm_px_23)));
        CustomWheelAdapter wheelAdapter = new CustomWheelAdapter(list);
        wheelView.setAdapter(wheelAdapter);
        tv_title.setText(title);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (listener != null) {
                    listener.click(wheelView.getCurrentItem());
                }
            }
        });
    }

    public interface OnOkListener {
        void click(int index);
    }


    public void setOnOkListener(OnOkListener onOkListener) {
        listener = onOkListener;
    }
}
