package com.lib_common.widget.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.lib_common.R;


/**
 * 选择照片bottomPhoto
 * author hechao
 * date 2018/9/5 0005
 */
public class SelectPhotoDialog extends BottomSheetDialog {

    private View.OnClickListener mOnTakePhotoListener;
    private View.OnClickListener mOnSelectAlbumListener;
    private View.OnClickListener mOnDismissListener;

    public SelectPhotoDialog(@NonNull Context context) {
        this(context, 0);
    }

    public SelectPhotoDialog(@NonNull Context context, int theme) {
        super(context, theme);
        init(context);
    }

    private void init(final Context context) {
        setContentView(R.layout.dialog_select_photo);
        //给布局设置透明背景色
        getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet)
                .setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));

        findViewById(R.id.text_take_photo).setOnClickListener(v -> {
            dismiss();
            if (mOnTakePhotoListener != null) {
                mOnTakePhotoListener.onClick(v);
            }
        });

        findViewById(R.id.text_album).setOnClickListener(v -> {
            dismiss();
            if (mOnSelectAlbumListener != null) {
                mOnSelectAlbumListener.onClick(v);
            }
        });

        findViewById(R.id.text_cancel).setOnClickListener(v -> {
            dismiss();
            if (mOnDismissListener != null) {
                mOnDismissListener.onClick(v);
            }
        });
    }

    public SelectPhotoDialog setTakePhotoListener(View.OnClickListener onClickListener) {
        this.mOnTakePhotoListener = onClickListener;
        return this;
    }

    public SelectPhotoDialog setSelectAlbumListener(View.OnClickListener onClickListener) {
        this.mOnSelectAlbumListener = onClickListener;
        return this;
    }

    public SelectPhotoDialog setDismissListener(View.OnClickListener onClickListener) {
        this.mOnDismissListener = onClickListener;
        return this;
    }
}
