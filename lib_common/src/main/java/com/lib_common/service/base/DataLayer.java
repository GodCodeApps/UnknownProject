package com.lib_common.service.base;

import android.app.Activity;
import android.support.v4.app.Fragment;


import com.lib_common.entity.BaseResponse;
import com.lib_common.entity.NewHomeInfo;
import com.lib_common.entity.PictureInfo;
import com.lib_common.entity.VideoLiveList;
import com.lib_common.entity.VideoLiveTable;

import io.reactivex.Observable;


/**
 * DataLayer
 *
 * @author Peng YanMing 2017/4/28
 */
public class DataLayer {

    private VideoPlayService mVideoPlayService;
    private NewService mNewService;


    public DataLayer(VideoPlayService VideoPlayService,NewService NewService) {
        mVideoPlayService = VideoPlayService;
        mNewService = NewService;
    }


    public VideoPlayService getVideoPlayService() {
        return mVideoPlayService;
    }

    public NewService getNewService() {
        return mNewService;
    }


    public interface VideoPlayService {
        Observable<VideoLiveTable> getVideoLiveTable(Fragment fragment);
        Observable<VideoLiveList> getVideoLiveList(Fragment fragment, int id, int feeds_type, int page);
    }
    public interface NewService {
        Observable<NewHomeInfo> getNewHomeList(int page);
        Observable<BaseResponse<PictureInfo>> getPictureList(int page);
    }
}
