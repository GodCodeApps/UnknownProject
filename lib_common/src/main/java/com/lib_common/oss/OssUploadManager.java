package com.lib_common.oss;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationToken;
import com.alibaba.sdk.android.oss.common.utils.BinaryUtil;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.ObjectMetadata;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.lib_base.ServiceFactory;
import com.lib_common.base.BaseApplication;
import com.lib_common.http.NetHttpApi;
import com.lib_common.http.NetUrl;
import com.lib_common.utils.constant.OssConstants;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author ZhongWeiZhi
 * @date 2018/8/16  10:23
 * @description oss上传管理
 */
public class OssUploadManager {
    private static OssUploadManager instance;
    /**
     * 上传client
     */
    private OSS oss;
    /**
     * 上传次数
     */
    private int number;
    /**
     * 成功上传(本地文件名作为key,阿里云地址为value)
     */
    private Map<String, Object> success = new HashMap<>();
    /**
     * 失败上传(返回失败文件的本地地址)
     */
    private List<String> failure = new ArrayList<>();
    /**
     * 上传回调
     */
    private UploadListener uploadListener;
    /**
     * 上传任务列表
     */
    private List<OSSAsyncTask> ossAsyncTasks = new ArrayList<>();
    OkHttpClient.Builder client;
    /**
     * 自动更新Token
     */
    private OSSCredentialProvider credetialProvider = new OSSFederationCredentialProvider() {
        @Override
        public OSSFederationToken getFederationToken() {
            OSSFederationToken authToken;
            String url = NetUrl.BASE_CLIENT_RES_URL + NetUrl.GET_OSSSTS;
            try {
                JSONObject obj = new JSONObject();
                obj.put("temporaryId", null != ServiceFactory.getInstance().getAccountService().getAccountId() ? ServiceFactory.getInstance().getAccountService().getAccountId() : String.valueOf(System.currentTimeMillis()));
                Request request = new Request.Builder().url(url).post(NetHttpApi.getRequestBody(obj, null)).build();
                Response response = client.build().newCall(request).execute();
                if (response.isSuccessful()) {
                    String strings = response.body().string();
                    JSONObject object = JSONObject.parseObject(strings);
                    int statusCode = object.getIntValue("code");
                    if (statusCode == 0) {
                        JSONObject jsonObj2 = object.getJSONObject("data");
                        String ak = jsonObj2.getString("AccessKeyId");
                        String sk = jsonObj2.getString("AccessKeySecret");
                        String token = jsonObj2.getString("SecurityToken");
                        String expiration = jsonObj2.getString("Expiration");
                        authToken = new OSSFederationToken(ak, sk, token, expiration);
                    } else {
                        String msg = object.getString("msg");
                        throw new ClientException("msg: " + msg);
                    }
                    return authToken;
                } else {
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    };

    public static OssUploadManager getInstance() {
        if (instance == null) {
            instance = new OssUploadManager();
        }
        return instance;
    }

    public OssUploadManager() {
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(20 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(20 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(9); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(3); // 失败后最大重试次数，默认2次
        client = new OkHttpClient.Builder();
        client.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS);
        oss = new OSSClient(BaseApplication.getInstance(), OssConstants.ENDPOINT, credetialProvider, conf);
    }

    /**
     * 添加上传任务
     *
     * @param paths
     * @param catalog  存放目录
     * @param listener
     */
    public void setDatas(final List<String> paths, String catalog, UploadListener listener) {
        this.uploadListener = listener;
        ossAsyncTasks.clear();
        number = 0;
        success.clear();
        failure.clear();
        for (String path : paths) {

            final File file = new File(path);
            /**
             * 阿里云上文件名称
             */
            String objectKey = catalog + "/" + UUID.randomUUID().toString() + file.getName().substring(file.getName().lastIndexOf("."));
            /**
             * 用户自定义参数
             */
            ObjectMetadata objectMetadata = new ObjectMetadata();
            try {
                //解决文件名，或路径带中文上传失败问题
                objectMetadata.addUserMetadata("filePath", URLEncoder.encode(file.getPath(), "UTF-8"));
                objectMetadata.addUserMetadata("fileName", URLEncoder.encode(file.getName(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            objectMetadata.addUserMetadata("objectKey", objectKey);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentEncoding("UTF-8");
            metadata.setContentType("application/octet-stream");
            try {
                // 设置Md5以便校验
                metadata.setContentMD5(BinaryUtil.calculateBase64Md5(path));
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
            PutObjectRequest put = new PutObjectRequest(OssConstants.BUCKET_NAME, objectKey, file.getPath());
            put.setMetadata(objectMetadata);
            put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
                @Override
                public void onProgress(PutObjectRequest putObjectRequest, long currentSize, long totalSize) {
                    //TODO   上传进度
                }
            });
            /**
             * 上传任务
             */
            OSSAsyncTask task;
            task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
                @Override
                public void onSuccess(PutObjectRequest putObjectRequest, PutObjectResult putObjectResult) {
                    number++;
                    success.put(putObjectRequest.getMetadata().getUserMetadata().get("fileName"), putObjectRequest.getObjectKey());
                    if (number == paths.size()) {
                        uploadListener.onUploadComplete(success, failure);
                    }
                }

                @Override
                public void onFailure(PutObjectRequest putObjectRequest, ClientException e, ServiceException e1) {
                    number++;
                    failure.add(putObjectRequest.getMetadata().getUserMetadata().get("filePath"));
                    if (number == paths.size()) {
                        uploadListener.onUploadComplete(success, failure);
                    }
                }
            });

            /**
             * 添加到上传记录
             */
            ossAsyncTasks.add(task);
        }
    }

    /**
     * 停止任务
     */
    public void cancleTasks() {
        if (null == ossAsyncTasks || ossAsyncTasks.size() == 0) {
            return;
        }
        for (OSSAsyncTask task : ossAsyncTasks) {
            if (task.isCompleted()) {

            } else {
                task.cancel();
            }
        }
    }

    /**
     * 拼接远程访问地址
     *
     * @param putObjectRequest
     * @return
     */
    private String formAliPath(PutObjectRequest putObjectRequest) {
        return "http://" + putObjectRequest.getBucketName() + "." + OssConstants.ENDPOINT + "/" + putObjectRequest.getObjectKey();
    }

}
