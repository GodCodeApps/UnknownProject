package com.lib_common.manager;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.lib_base.ServiceFactory;
import com.lib_common.base.BaseApplication;

import java.io.File;

/**
 * Created by Administrator on 2016/5/25.
 * <p>
 * 客户端目录管理总分为三大类：
 * 1.cacheFileRootPath;  //内部数据缓存，卸载之后丢失  （数据库，shere）
 * 2.externCacheFileRootPath;  //外部数据缓存,卸载之后丢失（图片缓存，个人用户数据）
 * 3.customExternFileRootPath；//外部自定义存储，卸载之后依然存在（log，公用模块目录，大型文件，app升级目录等）
 */
public class CacheDirectoryManager {

    private static final String TAG = CacheDirectoryManager.class.getSimpleName();

    private Context mContext;
    private static CacheDirectoryManager cacheDirectoryManager;

    public enum AppType {
        PHONE//手机端
    }

    private static String appPath;//三端目录


    /**********************系统内部缓存目录（/data/data/com.example.administrator.filemanager/files）********************/
    private static String cacheFileRootPath;  //内部数据缓存，卸载之后丢失  （数据库，shere）


    /**********************系统外部缓存目录（/storage/sdcard0/Android/data/）********************************************/
    private static String externCacheFileRootPath;  //外部数据缓存,卸载之后丢失（图片缓存，个人用户数据）


    private static String externCacheImagePath;// 图片缓存框架


    /**********************自定义外部存储(SD卡根目录)*********************************************************************/
    private static String customExternFileRootPath; //外部自定义存储，卸载之后依然存在（log，公用模块目录，大型文件，app升级目录等）


    private static String resourceDownloadPath;//资源下载公共目录
    private static String crashLogPath; //崩溃log日志目录
    private static String normalLogPath; //普通日志根目录


    //个人外部目录
    private static String userStorageImagePath;//用户个人图片外部存储
    private static String userStorageVideoPath;//用户个人视频外部存储
    private static String userStorageAudioPath;//用户个人音频外部存储

    private static String correctCachePath;
    private static String connectGifCachePath;//联机gif图路径

    /**
     * 检查文件是否存在
     *
     * @param path
     */
    private void checkFileExitis(String path) {
        File file = new File(path);
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
                file.mkdirs();
            }
        } else {
            file.mkdirs();
        }
    }

    public String getCacheFileRootPath() {
        if (cacheFileRootPath == null) {
            initAppStoragePath(mContext, AppType.PHONE);
        }
        checkFileExitis(cacheFileRootPath);
        return cacheFileRootPath;
    }


    public String getExternCacheFileRootPath() {
        if (externCacheFileRootPath == null) {
            initAppStoragePath(mContext, AppType.PHONE);
        }
        checkFileExitis(externCacheFileRootPath);
        return externCacheFileRootPath;
    }


    public String getCustomExternFileRootPath() {
        if (customExternFileRootPath == null) {
            initAppStoragePath(mContext, AppType.PHONE);
        }
        checkFileExitis(customExternFileRootPath);
        return customExternFileRootPath;
    }

    public String getCrashLogPath() {
        if (crashLogPath == null) {
            initAppStoragePath(mContext, AppType.PHONE);
        }
        checkFileExitis(crashLogPath);
        return crashLogPath;
    }

    public String getResourceDownloadPath() {
        if (resourceDownloadPath == null) {
            initAppStoragePath(mContext, AppType.PHONE);
        }
        checkFileExitis(resourceDownloadPath);
        return resourceDownloadPath;
    }

    public String getCorrectCachePath() {
        if (correctCachePath == null) {
            initAppStoragePath(mContext, AppType.PHONE);
        }
        checkFileExitis(correctCachePath);
        return correctCachePath;
    }

    public String getConnectGifCachePath() {
        if (connectGifCachePath == null) {
            initAppStoragePath(mContext, AppType.PHONE);
        }
        checkFileExitis(connectGifCachePath);
        return connectGifCachePath;
    }

    public String getUserStorageAudioPath() {
        if (userStorageAudioPath == null) {
            initAppStoragePath(mContext, AppType.PHONE);
        }
        checkFileExitis(userStorageAudioPath);
        return userStorageAudioPath;
    }

    public String getUserStorageImagePath() {
        if (userStorageImagePath == null) {
            initAppStoragePath(mContext, AppType.PHONE);
        }
        checkFileExitis(userStorageImagePath);
        return userStorageImagePath;
    }

    public String getUserStorageVideoPath() {
        if (userStorageVideoPath == null) {
            initAppStoragePath(mContext, AppType.PHONE);
        }
        checkFileExitis(userStorageVideoPath);
        return userStorageVideoPath;
    }

    public String getExternCacheImagePath() {
        if (externCacheImagePath == null) {
            initAppStoragePath(mContext, AppType.PHONE);
        }
        checkFileExitis(externCacheImagePath);
        return externCacheImagePath;
    }


    public String getNormalLogPath() {
        if (normalLogPath == null) {
            initAppStoragePath(mContext, AppType.PHONE);
        }
        checkFileExitis(normalLogPath);
        return normalLogPath;
    }


    public static CacheDirectoryManager getInstance() {

        synchronized (CacheDirectoryManager.class) {
            if (cacheDirectoryManager == null) {
                cacheDirectoryManager = new CacheDirectoryManager(BaseApplication.getInstance());
            }
            return cacheDirectoryManager;
        }
    }


    public static void setNormalLogPath(String normalLogPath) {
        CacheDirectoryManager.normalLogPath = normalLogPath;
    }


    private CacheDirectoryManager(Context context) {
        this.mContext = context;
    }


    /**
     * 初始化应用存储路径
     *
     * @param context
     */
    public synchronized void initAppStoragePath(Context context, AppType appType) {
        initRootPath(appType);
        initCommonCache(context);
        initUserFileDirectory(ServiceFactory.getInstance().getAccountService().getAccountId());
    }


    /**
     * 初始化缓存根目录
     *
     * @param appType
     */
    private void initRootPath(AppType appType) {
        initAppPath(appType);
        cacheFileRootPath = (mContext.getFilesDir() == null ? "" : mContext.getFilesDir().toString());
        externCacheFileRootPath = getExternCacheFileRootPath(mContext);
        customExternFileRootPath = getStorageDirs(mContext);
    }

    private void initAppPath(AppType appType) {
        switch (appType) {
            case PHONE:
                appPath = "user";
                break;
            default:
                appPath = "default";
                break;
        }
    }


    public static String getExternCacheFileRootPath(Context context) {
        // 获取Android程序在Sd上的保存目录约定 当程序卸载时，系统会自动删除。
        File f = context.getExternalFilesDir(null);
        // 如果约定目录不存在
        if (f == null) {
            // 获取外部存储目录即 SDCard
            return getStorageDirs(context);

        } else {
            String storageDirectory = f.getAbsolutePath();
            Log.i(TAG, "项目存储路径采用系统给的路径地址  storageDirectory:" + storageDirectory);
            return storageDirectory;
        }

    }

    public static String getStorageDirs(Context context) {
        // 获取外部存储目录即 SDCard
        String storageDirectory = Environment.getExternalStorageDirectory().toString();
        File fDir = new File(storageDirectory);
        // 如果sdcard目录不可用
        if (!fDir.canWrite()) {
            // 获取可用
            storageDirectory = getSDCardDir();
            if (storageDirectory != null) {
                storageDirectory = storageDirectory + File.separator + "com.diandian7.ddqcompetition" + File.separator + appPath;
                Log.i(TAG, "项目存储路径采用自动找寻可用存储空间的方式   storageDirectory:" + storageDirectory);
                return storageDirectory;

            } else {
                Log.e(TAG, "没有找到可用的存储路径  采用FilesDir");
                return context.getFilesDir().toString();
            }
        } else {
            storageDirectory = storageDirectory + File.separator + "com.diandian7.ddqcompetition" + File.separator + appPath;
            Log.i(TAG, "项目存储路径采用sdcard的地址   storageDirectory:" + storageDirectory);
            return storageDirectory;
        }
    }


    /**
     * 登录前app公用目录
     *
     * @param context
     */
    private void initCommonCache(Context context) {

        externCacheImagePath = externCacheFileRootPath + File.separator + "image" + File.separator;// 图片缓存地址
        makedirs(externCacheImagePath);

        resourceDownloadPath = customExternFileRootPath + File.separator + "download" + File.separator;// 下载地址
        makedirs(resourceDownloadPath);

        crashLogPath = customExternFileRootPath + File.separator + "error" + File.separator;// 错误日志路径
        makedirs(crashLogPath);


        normalLogPath = customExternFileRootPath + File.separator + "normal-Log" + File.separator;// 普通日志的输出
        makedirs(normalLogPath);

        correctCachePath = externCacheFileRootPath + File.separator + "correct_pic" + File.separator;
        makedirs(correctCachePath);
        connectGifCachePath = externCacheFileRootPath + File.separator + "gif" + File.separator;//联机git图路径
        makedirs(connectGifCachePath);
    }

    /**
     * 个人用户目录（登录成功之后调用）
     *
     * @param userName
     */
    public void initUserFileDirectory(String userName) {
        userStorageImagePath = customExternFileRootPath + File.separator + "image" + File.separator;//用户个人图片外部存储
        makedirs(userStorageImagePath);

        userStorageVideoPath = customExternFileRootPath + File.separator + "video" + File.separator;//用户个人视频外部存储
        makedirs(userStorageVideoPath);

        userStorageAudioPath = customExternFileRootPath + File.separator + "audio" + File.separator;//用户个人音频外部存储
        makedirs(userStorageAudioPath);


    }

    /**
     * 创建文件夹
     *
     * @param path
     */
    private void makedirs(String path) {

        if (path.contains("null")) {
            throw new NullPointerException();
        }

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }


    /**
     * 获取一个可用的存储路径（可能是内置的存储路径）
     *
     * @return 可用的存储路径
     */
    private static String getSDCardDir() {
        String pathDir = null;
        // 先获取内置sdcard路径
        File sdfile = Environment.getExternalStorageDirectory();
        // 获取内置sdcard的父路径
        File parentFile = sdfile.getParentFile();
        // 列出该父目录下的所有路径
        File[] listFiles = parentFile.listFiles();
        // 如果子路径可以写 就是拓展卡（包含内置的和外置的）

        long freeSizeMax = 0L;
        for (int i = 0; null != listFiles && listFiles.length > 0 && i < listFiles.length; i++) {
            if (listFiles[i].canWrite()) {
                // listFiles[i]就是SD卡路径
                String tempPathDir = listFiles[i].getAbsolutePath();
                long tempSize = getSDFreeSize(tempPathDir);
                if (tempSize > freeSizeMax) {
                    freeSizeMax = tempSize;
                    pathDir = tempPathDir;
                }
            }
        }
        return pathDir;
    }


    /**
     * 获取指定目录剩余空间
     *
     * @return
     * @author EX-LIJINHUA001
     * @date 2013-6-7
     */
    public static long getSDFreeSize(String filePath) {

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }

            android.os.StatFs statfs = new android.os.StatFs(filePath);
            long nBlocSize = statfs.getBlockSize(); // 获取SDCard上每个block的SIZE
            long nAvailaBlock = statfs.getAvailableBlocks(); // 获取可供程序使用的Block的数量
            long nSDFreeSize = nAvailaBlock * nBlocSize; // 计算 SDCard
            // 剩余大小B
            return nSDFreeSize;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.i(TAG, "httpFrame threadName:" + Thread.currentThread().getName()
                    + " getSDFreeSize  无法计算文件夹大小 folderPath:" + filePath);
        }

        return -1;
    }


}
