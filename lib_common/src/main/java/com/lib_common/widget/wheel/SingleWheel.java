package com.lib_common.widget.wheel;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnDismissListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.lib_common.R;
import com.lib_common.entity.CardBean;
import com.lib_common.utils.ConvertUtils;


import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhongWeiZhi
 * @date 2018/8/31  20:03
 * @description  单列选择器
 */
public class SingleWheel {

    private static SingleWheel instance;
    private Context mContext;
    private OptionsPickerView mOptionsPickerView;
    private OnFinishListener mOnFinishListener;
    private List<CardBean> cards = new ArrayList<>();
    public SingleWheel(Context mContext) {
        this.mContext = mContext;
    }

    public static SingleWheel getInstance(Context context) {
        synchronized (SingleWheel.class) {
            if (instance == null) {
                instance = new SingleWheel(context);
            }
        }
        return instance;
    }

    public void showWheel(List<CardBean> list,String title,OnFinishListener listener ){
        cards = list;
        setListener(listener);
        mOptionsPickerView=null;
        /**
         * @description
         *
         * 注意事项：
         * 自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针。
         * 具体可参考demo 里面的两个自定义layout布局。
         */
        mOptionsPickerView = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                if(null!=mOnFinishListener){
                    mOnFinishListener.selectListener(cards.get(options1));
                }
            }
        }).setLayoutRes(R.layout.popupwindow_select, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        final TextView tvAdd = (TextView) v.findViewById(R.id.tv_title);
                        TextView ivCancel = (TextView) v.findViewById(R.id.tv_cancel);
                        tvAdd.setText(title);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mOptionsPickerView.returnData();
                                mOptionsPickerView.dismiss();
                            }
                        });

                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mOptionsPickerView.dismiss();
                            }
                        });


                    }
                }).setContentTextSize(ConvertUtils.px2sp(mContext.getResources().getDimensionPixelSize(R.dimen.sm_px_23)))
                .isDialog(false)
                .build();
        mOptionsPickerView.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(Object o) {
                instance = null;
            }
        });
        mOptionsPickerView.setPicker(cards);//添加数据
        mOptionsPickerView.show();

    }
    public void setListener(OnFinishListener listener ){
        mOnFinishListener = listener;
    }
    /**
     * 完成键监听
     */
    public interface OnFinishListener{
        public void selectListener(CardBean bean);
    }
}
