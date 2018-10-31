package com.module_community.vm;

import android.content.Context;

import com.lib_common.base.BaseViewModel;
import com.lib_common.base.recycler.PagingViewModel;
import com.lib_common.commonadapter.CommonAdapter;
import com.module_community.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Peng YanMing on 2018\10\31 0031
 */
public class CommunityViewModel extends PagingViewModel<String, CommonAdapter> {
    public CommunityViewModel(Context context) {
        super(context);
    }

    @Override
    protected void initAdapter() {
        adapter = new CommonAdapter(context, mList, R.layout.item_community);
    }

    @Override
    protected void getData(boolean isMore) {
        doOnSubscribe(isMore);
        List<String> list=new ArrayList<>();
        list.add("http://seopic.699pic.com/photo/50035/0520.jpg_wh1200.jpg");
        list.add("https://img.pc841.com/2018/0815/20180815101229911.jpg");
        accept(isMore, list);
        doOnComplete(isMore);

    }
}
