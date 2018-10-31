package com.lib_common.service;

import com.lib_common.entity.AliAuthRes;
import com.lib_common.entity.BaseBean;
import com.lib_common.http.NetUrl;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author ZhongWeiZhi
 * @date 2018/8/18  15:30
 * @description 公共接口类
 */
public interface CommonService {
    @POST(NetUrl.GET_OSSSTS)
    Observable<BaseBean<AliAuthRes>> getOssSts(@Body RequestBody account);

//    @POST(NetUrl.GET_USER_INFO)
//    Observable<BaseBean<UserInfo>> getUserInfo(@Body RequestBody account);
//
//    /**
//     * 修改头像
//     *
//     * @param body
//     * @return
//     */
//    @POST(NetUrl.SET_IMAGEURL)
//    Observable<BaseBean<SetImageResultBean>> setimageurl(@Body RequestBody body);

}
