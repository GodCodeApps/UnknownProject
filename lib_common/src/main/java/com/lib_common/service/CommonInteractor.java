package com.lib_common.service;

import com.lib_common.http.NetHttpApi;

/**
 * @author ZhongWeiZhi
 * @date 2018/8/28  16:57
 * @description
 */
public class CommonInteractor {
    private CommonService mService;

    public CommonInteractor() {
        mService = NetHttpApi.getInstance().getService(CommonService.class);
    }
//
//    /**
//     * 获取当前用户信息
//     *
//     * @param subscribe
//     */
//    public void getUserInfo(RxSubscribe<UserInfo> subscribe) {
//        mService.getUserInfo(PostJsonBody.create("")).compose(RxHelper.handleResult()).subscribe(subscribe);
//    }
//
//    /**
//     * 设置头像
//     *
//     * @param imageurl
//     * @param subscribe
//     */
//    public void setImageUrl(String imageurl, RxSubscribe<SetImageResultBean> subscribe) {
//        HashMap<String, Object> paramsMap = new HashMap<>();
//        paramsMap.put("imageurl", imageurl);
//        mService.setimageurl(PostJsonBody.create(new Gson().toJson(paramsMap))).compose(RxHelper.handleResult()).subscribe(subscribe);
//    }

}
