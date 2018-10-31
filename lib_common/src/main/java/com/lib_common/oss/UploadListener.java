package com.lib_common.oss;

import java.util.List;
import java.util.Map;

/**
 * @author ZhongWeiZhi
 * @date 2018/8/16  10:31
 * @description  上传结果监听
 */
public interface UploadListener {
    /**
     * 上传完成
     *
     * @param success
     * @param failure
     */
    void onUploadComplete(Map<String, Object> success, List<String> failure);
}
