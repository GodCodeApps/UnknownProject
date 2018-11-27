package com.lib_common.service;

/**
 * Peng YanMing on 2018\8\17 0017
 */

import android.support.v4.app.Fragment;


import com.lib_common.entity.VideoLiveList;
import com.lib_common.entity.VideoLiveTable;
import com.lib_common.service.base.BaseManager;
import com.lib_common.service.base.DataLayer;

import io.reactivex.Observable;

/**
 *
 * @author Peng YanMing 2017/4/28
 */
public class VideoServiceManager extends BaseManager implements DataLayer.VideoPlayService {


    @Override
    public Observable<VideoLiveTable> getVideoLiveTable(Fragment fragment) {
        return getApi().getVideoliveTable();
    }

    @Override
    public Observable<VideoLiveList> getVideoLiveList(Fragment fragment, int id, int feeds_type, int page) {
        return getApi().getVideoliveList(id, feeds_type, page);
    }
}

