package com.lib_common.net;


import com.lib_common.entity.BaseResponse;
import com.lib_common.entity.NewHomeInfo;
import com.lib_common.entity.PictureInfo;
import com.lib_common.entity.VideoInfo;
import com.lib_common.entity.VideoLiveList;
import com.lib_common.entity.VideoLiveTable;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * ClientAPI
 *
 * @author Peng YanMing 2016/12/9
 */
public interface ClientAPI {
    /**
     * 获取直播类别
     *
     * @return VideoLiveTable
     */
    @GET("videolive/home/channels?iid=39921234076&device_id=41657683759&ac=wifi&channel=vivo&aid=32&app_name=video_article&version_code=664&version_name=6.6.4&device_platform=android&ab_version=344692%2C418416%2C321290%2C425685%2C424183%2C426435%2C381398%2C439901%2C432388%2C436132%2C374100%2C419921%2C398044%2C434356%2C439844%2C377320%2C423674%2C409606%2C378355%2C434430%2C437663%2C430495%2C435541%2C437658&ssmix=a&device_type=vivo+X20A&device_brand=vivo&language=zh&os_api=25&os_version=7.1.1&uuid=867649036961114&openudid=d38a3a1ca6fafba1&manifest_version_code=264&resolution=1080*2160&dpi=480&update_version_code=66404&_rticket=1533518545731&fp=jrTqF2GtFSc7FlcSJlU1FYmIJYK7&rom_version=Funtouch+OS_3.2_PD1709_A_1.14.3")
    Observable<VideoLiveTable> getVideoliveTable();

    /**
     * 获取直播列表
     * feeds_type=0下拉feeds_type=1上拉
     */
    @GET("videolive/home/page/v2?page_type=0&cursor=0&app_life_id=1533518545772&iid=39921234076&device_id=41657683759&ac=wifi&channel=vivo&aid=32&app_name=video_article&version_code=664&version_name=6.6.4&device_platform=android&ab_version=344692%2C418416%2C321290%2C425685%2C424183%2C426435%2C381398%2C439901%2C432388%2C436132%2C374100%2C419921%2C398044%2C434356%2C439844%2C377320%2C423674%2C409606%2C378355%2C434430%2C437663%2C430495%2C435541%2C437658&ssmix=a&device_type=vivo+X20A&device_brand=vivo&language=zh&os_api=25&os_version=7.1.1&uuid=867649036961114&openudid=d38a3a1ca6fafba1&manifest_version_code=264&resolution=1080*2160&dpi=480&update_version_code=66404&_rticket=1533518545846&fp=jrTqF2GtFSc7FlcSJlU1FYmIJYK7&rom_version=Funtouch+OS_3.2_PD1709_A_1.14.3")
    Observable<VideoLiveList> getVideoliveList(@Query("id") int id, @Query("feeds_type") int feeds_type, @Query("page") int page);
    // device_id=41657683759
    // &ac=wifi
    // &channel=vivo
    // &aid=32
    // &app_name=video_article
    // &version_code=664
    // &version_name=6.6.4
    // &device_platform=android
    // &ssmix=a
    // &device_type=vivo+X20A
    // &device_brand=vivo
    // &language=zh
    // &os_api=25
    // &os_version=7.1.1
    // &uuid=867649036961114
    // &openudid=d38a3a1ca6fafba1
    // &manifest_version_code=264
    // &resolution=1080*2160
    // &dpi=480
    // &update_version_code=66404
    // &_rticket=1533708507711
    // &rom_version=Funtouch+OS_3.2_PD1709_A_1.14.3

    /**
     * 获取新闻列表
     */
    @FormUrlEncoded
    @POST("video/app/stream/v51/?")
    Observable<NewHomeInfo> getNewListInfo(@Field("device_id") String device_id,
                                           @Field("ac") String ac,
                                           @Field("channel") String channel,
                                           @Field("aid") String aid,
                                           @Field("app_name") String app_name,
                                           @Field("version_code") String version_code,
                                           @Field("version_name") String version_name,
                                           @Field("device_platform") String device_platform,
                                           @Field("ssmix") String ssmix,
                                           @Field("device_type") String device_type,
                                           @Field("device_brand") String device_brand,
                                           @Field("language") String language,
                                           @Field("os_api") String os_api,
                                           @Field("os_version") String os_version,
                                           @Field("uuid") String uuid,
                                           @Field("openudid") String openudid,
                                           @Field("manifest_version_code") String manifest_version_code,
                                           @Field("resolution") int resolution,
                                           @Field("dpi") String dpi,
                                           @Field("update_version_code") String update_version_code,
                                           @Field("_rticket") String _rticket,
                                           @Field("rom_version") String rom_version,
                                           @Field("page") int page);

    @GET("http://baobab.kaiyanapp.com/api/v4/tabs/selected?")
    Observable<VideoInfo> getVideoList(@Query("date") long date, @Query("num") int num, @Query("page") int page);

    @GET("http://gank.io/api/" + "data/{category}/" + "/{pagingLimit}" + "/{page}")
    Observable<BaseResponse<PictureInfo>> getPicture(@Path("category") String categoryId, @Path("page") int page, @Path("pagingLimit") int pagingLimit);
}
