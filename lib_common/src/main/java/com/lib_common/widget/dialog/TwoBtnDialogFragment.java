package com.lib_common.widget.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lib_common.R;


/**
 * @author ZhongWeiZhi
 * @date 2018/8/23  17:26
 * @description 两个按钮，可带可不带图标
 */
public class TwoBtnDialogFragment extends DialogFragment {

    public static final String EXTRA_CONTENT = "extra_content";    //提示框内容
    public static final String EXTRA_SUB_CONTENT = "extra_sub_content";    //提示框内容
    public static final String EXTRA_CONTENT_COLOR = "extra_content_color";    //提示框内容颜色；默认R.color.222222
    public static final String EXTRA_OK_TEXT = "extra_ok_text";//确定按钮文本
    public static final String EXTRA_CANCEL_TEXT = "extra_cancel_text";//取消按钮文本
    public static final String EXTRA_SINGLE_BUTTON = "extra_single_button";//是否为单按钮
    public static final String EXTRA_INPUT_STYLE = "extra_input_style";//是否为输入框
    public static final String EXTRA_RES = "extra_res";    //提示框内容
    private ImageView ivIcon;
    private TextView tvContent;
    private TextView tvSubContent;
    private EditText etContent;
    private Button tvSure;
    private Button tvCancle;
    private View.OnClickListener onSureClick;
    private View.OnClickListener onCancelClick;
    private OnInputClickListener mOnInputClickListener;

    public TwoBtnDialogFragment() {
    }

    /**
     * 带输入框dialog
     *
     * @param content
     * @param onClickListener
     * @return
     */
    public static TwoBtnDialogFragment getInputInstance(String content, OnInputClickListener onClickListener) {
        return getInputInstance(content, "确定", R.mipmap.default_head, onClickListener);
    }

    public static TwoBtnDialogFragment getInputInstance(String content, int resId, OnInputClickListener onClickListener) {
        return getInputInstance(content, "确定", resId, onClickListener);
    }

    public static TwoBtnDialogFragment getInputInstance(String content, String buttonText, int resId, OnInputClickListener onClickListener) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_CONTENT, content);
        bundle.putString(EXTRA_OK_TEXT, buttonText);
        bundle.putBoolean(EXTRA_SINGLE_BUTTON, true);
        bundle.putBoolean(EXTRA_INPUT_STYLE, true);
        bundle.putInt(EXTRA_RES, resId);
        TwoBtnDialogFragment fragment = new TwoBtnDialogFragment();
        fragment.setArguments(bundle);
        fragment.setOnInputClickListener(onClickListener);
        return fragment;
    }

    public static TwoBtnDialogFragment getSingleButtonInstance(String content, String buttonText, View.OnClickListener onClickListener) {
        return getSingleButtonInstance(content, buttonText, R.mipmap.default_head, onClickListener);
    }

    /**
     * 单个按钮dialog
     *
     * @param content
     * @param buttonText
     * @param resId
     * @param onClickListener
     * @return
     */
    public static TwoBtnDialogFragment getSingleButtonInstance(String content, String buttonText, int resId, View.OnClickListener onClickListener) {
        return getSingleButtonInstance(content, "", buttonText, resId, onClickListener);
    }

    public static TwoBtnDialogFragment getSingleButtonInstance(String content, String subContent, String buttonText, int resId, View.OnClickListener onClickListener) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_CONTENT, content);
        bundle.putString(EXTRA_SUB_CONTENT, subContent);
        bundle.putString(EXTRA_OK_TEXT, buttonText);
        bundle.putBoolean(EXTRA_SINGLE_BUTTON, true);
        bundle.putInt(EXTRA_RES, resId);
        TwoBtnDialogFragment fragment = new TwoBtnDialogFragment();
        fragment.setArguments(bundle);
        fragment.setOnSureClickListener(onClickListener);
        return fragment;
    }

    /**
     * 普通确定取消按钮dialog
     *
     * @param content
     * @param onSureClick
     * @return
     */
    public static TwoBtnDialogFragment getIntance(String content, View.OnClickListener onSureClick) {
        return getIntance(content, "", onSureClick);
    }

    public static TwoBtnDialogFragment getIntance(String content, String subContent, View.OnClickListener onSureClick) {
        return getIntance(content, subContent, "确定", "取消", onSureClick);
    }

    public static TwoBtnDialogFragment getIntance(String content, String okText, String cancelText, View.OnClickListener onSureClick) {
        return getIntance(content, "", okText, cancelText, onSureClick);
    }

    public static TwoBtnDialogFragment getIntance(String content, String subContent, String okText, String cancelText, View.OnClickListener onSureClick) {
        Bundle bundle = new Bundle();
        bundle.putString(TwoBtnDialogFragment.EXTRA_CONTENT, content);
        bundle.putInt(TwoBtnDialogFragment.EXTRA_RES, R.mipmap.default_head);
        bundle.putString(TwoBtnDialogFragment.EXTRA_SUB_CONTENT, subContent);
        bundle.putString(EXTRA_OK_TEXT, okText);
        bundle.putString(EXTRA_CANCEL_TEXT, cancelText);
        TwoBtnDialogFragment fragment = new TwoBtnDialogFragment();
        fragment.setArguments(bundle);
        fragment.setOnSureClickListener(onSureClick);
        return fragment;
    }

    public static TwoBtnDialogFragment getIntance(String content, String okText, String cancelText, int res, View.OnClickListener onSureClick, View.OnClickListener onCancelClick) {
        Bundle bundle = new Bundle();
        bundle.putString(TwoBtnDialogFragment.EXTRA_CONTENT, content);
        bundle.putInt(TwoBtnDialogFragment.EXTRA_RES, res);
        bundle.putString(TwoBtnDialogFragment.EXTRA_SUB_CONTENT, "");
        bundle.putString(EXTRA_OK_TEXT, okText);
        bundle.putString(EXTRA_CANCEL_TEXT, cancelText);
        TwoBtnDialogFragment fragment = new TwoBtnDialogFragment();
        fragment.setArguments(bundle);
        fragment.setOnSureClickListener(onSureClick);
        fragment.setOnCancelClickListener(onCancelClick);
        return fragment;
    }

    public static TwoBtnDialogFragment getIntance(String content, int res, View.OnClickListener onSureClick) {
        return getIntance(content, "", res, onSureClick);
    }

    public static TwoBtnDialogFragment getIntance(String content, String subContent, int res, View.OnClickListener onSureClick) {
        return getIntance(content, subContent, "确定", "取消", res, onSureClick);
    }

    public static TwoBtnDialogFragment getIntance(String content, String subContent, String okText, String cancelText, int res, View.OnClickListener onSureClick) {
        Bundle bundle = new Bundle();
        bundle.putString(TwoBtnDialogFragment.EXTRA_CONTENT, content);
        bundle.putString(TwoBtnDialogFragment.EXTRA_SUB_CONTENT, subContent);
        bundle.putInt(TwoBtnDialogFragment.EXTRA_RES, res);
        bundle.putString(EXTRA_OK_TEXT, okText);
        bundle.putString(EXTRA_CANCEL_TEXT, cancelText);
        TwoBtnDialogFragment fragment = new TwoBtnDialogFragment();
        fragment.setArguments(bundle);
        fragment.setOnSureClickListener(onSureClick);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.fragment_two_btn_dialog, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        Bundle bundle = getArguments();
        tvContent = view.findViewById(R.id.tv_content);
        tvSubContent = view.findViewById(R.id.tv_sub_content);
        etContent = view.findViewById(R.id.edit_content);
        tvSure = view.findViewById(R.id.tv_sure);
        tvCancle = view.findViewById(R.id.tv_cancel);
        boolean isInputStyle = false;
        if (bundle != null) {
            tvContent.setText(bundle.getString(EXTRA_CONTENT));

            String subContent = bundle.getString(EXTRA_SUB_CONTENT);
            if (!TextUtils.isEmpty(subContent)) {
                tvSubContent.setVisibility(View.VISIBLE);
                tvSubContent.setText(subContent);
            }

            String okText = bundle.getString(EXTRA_OK_TEXT);
            if (!TextUtils.isEmpty(okText)) {
                tvSure.setText(okText);
            }

            String cancelText = bundle.getString(EXTRA_CANCEL_TEXT);
            if (!TextUtils.isEmpty(cancelText)) {
                tvCancle.setText(cancelText);
            }

            boolean isSingleButton = bundle.getBoolean(EXTRA_SINGLE_BUTTON, false);
            if (isSingleButton) {
                tvCancle.setVisibility(View.GONE);
                tvSure.setBackgroundResource(R.mipmap.default_head);
                tvSure.setLayoutParams(new LinearLayout.LayoutParams(-1, getResources().getDimensionPixelSize(R.dimen.sm_px_40)));
            }

            isInputStyle = bundle.getBoolean(EXTRA_INPUT_STYLE, false);
            if (isInputStyle) {
                etContent.setVisibility(View.VISIBLE);
                tvSure.setOnClickListener(v -> {
                    dismiss();
                    if (mOnInputClickListener != null) {
                        mOnInputClickListener.onClick(etContent.getText().toString());
                    }
                });
            }
        }


        if (bundle != null && bundle.getInt(EXTRA_RES) != 0) {
            ivIcon = view.findViewById(R.id.iv_icon);
            ivIcon.setVisibility(View.VISIBLE);
            ivIcon.setImageDrawable(ContextCompat.getDrawable(getContext(), bundle.getInt(EXTRA_RES)));
        } else {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tvContent.getLayoutParams();
            params.setMargins(getResources().getDimensionPixelSize(R.dimen.sm_px_15), 0, getResources().getDimensionPixelSize(R.dimen.sm_px_15), getResources().getDimensionPixelSize(R.dimen.sm_px_22));
            tvContent.setLayoutParams(params);
        }

        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (null != onCancelClick) {
                    onCancelClick.onClick(v);
                }
            }
        });

        if (!isInputStyle) {
            tvSure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    if (null != onSureClick) {
                        onSureClick.onClick(v);
                    }
                }
            });
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout(getContext().getResources().getDimensionPixelSize(R.dimen.sm_px_275), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    public void setOnInputClickListener(OnInputClickListener onInputClickListener) {
        mOnInputClickListener = onInputClickListener;
    }

    /**
     * 设置输入回调
     *
     * @param sureClick
     */
    public void setOnSureClickListener(View.OnClickListener sureClick) {
        this.onSureClick = sureClick;
    }

    public void setOnCancelClickListener(View.OnClickListener cancelClickListener) {
        this.onCancelClick = cancelClickListener;
    }

    public interface OnInputClickListener {
        void onClick(String content);
    }
}
