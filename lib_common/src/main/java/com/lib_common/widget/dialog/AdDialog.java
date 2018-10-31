package com.lib_common.widget.dialog;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lib_common.R;
import com.lib_common.databinding.DialogAdBinding;
import com.lib_common.imageloader.app.ImageLoaderApi;

import java.util.Objects;

/**
 * 广告弹框
 * author hechao
 * date 2018/9/25 0025
 */
public class AdDialog extends DialogFragment {

    public static final int STYLE_CLOSE_BOTTOM = 0;
    public static final int STYLE_CLOSE_RIGHT_TOP = 1;

    private DialogAdBinding mDialogAdBinding;
    private int mStyle;
    private String mUrl;
    private View.OnClickListener mAdClickListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Objects.requireNonNull(getDialog().getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialogAdBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_ad, container, true);
        return mDialogAdBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initial();
    }

    private void initial() {
        mDialogAdBinding.btnClose.setOnClickListener(v -> dismissAllowingStateLoss());
        ImageLoaderApi.loadImage(mUrl, mDialogAdBinding.imageView, getContext());
        mDialogAdBinding.cardView.setOnClickListener(v -> {
            dismiss();
            if (null != mAdClickListener) {
                mAdClickListener.onClick(v);
            }
        });
        if (mStyle == STYLE_CLOSE_BOTTOM) {
            mDialogAdBinding.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else if (mStyle == STYLE_CLOSE_RIGHT_TOP) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-2, -2);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.topMargin = getResources().getDimensionPixelSize(R.dimen.sm_px_30);
            mDialogAdBinding.btnClose.setLayoutParams(params);

            mDialogAdBinding.cardView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            mDialogAdBinding.cardView.setCardBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null && dialog.getWindow() != null) {
            dialog.getWindow().setLayout(-1, -1);
        }
    }

    public static class Builder {

        private AppCompatActivity mActivity;
        private int mStyle;
        private String mUrl;
        private View.OnClickListener mAdClickListener;

        public Builder(AppCompatActivity appCompatActivity) {
            this.mActivity = appCompatActivity;
        }

        public Builder setStyle(int style) {
            mStyle = style;
            return this;
        }

        public Builder setUrl(String url) {
            mUrl = url;
            return this;
        }

        public Builder setAdClickListener(View.OnClickListener onClickListener) {
            mAdClickListener = onClickListener;
            return this;
        }

        public AdDialog build() {
            AdDialog adDialog = new AdDialog();
            adDialog.mStyle = mStyle;
            adDialog.mUrl = mUrl;
            adDialog.mAdClickListener = mAdClickListener;
            return adDialog;
        }

        public void show() {
            AdDialog dialog = build();
            if (null != mActivity.getSupportFragmentManager()) {
                dialog.show(mActivity.getSupportFragmentManager(), "");
            }
        }
    }
}
