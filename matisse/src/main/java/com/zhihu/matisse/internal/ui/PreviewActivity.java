package com.zhihu.matisse.internal.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.zhihu.matisse.R;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.ui.widget.PreviewViewPager;
import com.zhihu.matisse.internal.utils.qmuihelper.QMUIDisplayHelper;

import java.util.ArrayList;
import java.util.List;

public class PreviewActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    public static final String PREVIEW_IMAGE_ARRAY = "preview_image_array";
    public static final String PREVIEW_POSITION = "preview_position";
    private List<String> mList = new ArrayList<>();
    private TextView mTextPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        PreviewViewPager viewPager = findViewById(R.id.viewPager);
        mTextPosition = findViewById(R.id.text_position);

        Intent intent = getIntent();
        mList = intent.getStringArrayListExtra(PREVIEW_IMAGE_ARRAY);
        int position = intent.getIntExtra(PREVIEW_POSITION, 0);

        viewPager.addOnPageChangeListener(this);
        viewPager.setAdapter(new PreviewAdapter(getSupportFragmentManager()));
        if (position > 0) {
            viewPager.setCurrentItem(position);
        }
        mTextPosition.setText((position + 1) + "/" + mList.size());

        if (mList.size() == 1 && mList.get(0).toLowerCase().contains("mp4")) {
            if (QMUIDisplayHelper.checkDeviceHasNavigationBar(this) && QMUIDisplayHelper.getNavMenuHeight(this) > 0) {
                viewPager.setFitsSystemWindows(true);
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        mTextPosition.setText((position + 1) + "/" + mList.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class PreviewAdapter extends FragmentPagerAdapter {

        public PreviewAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PreviewItemFragment.newInstance(new Item(mList.get(position)), String.valueOf(position));
        }

        @Override
        public int getCount() {
            return mList.size();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, android.R.anim.fade_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (GSYVideoManager.instance().isPlaying()) {
            GSYVideoManager.releaseAllVideos();
        }
    }
}
