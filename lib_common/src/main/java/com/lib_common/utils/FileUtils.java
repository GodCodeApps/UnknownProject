package com.lib_common.utils;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 文件处理相关工具类
 * <ul>
 * 读或写文件相关
 * <li>{@link #readFile(String)} 读取文件内容</li>
 * <li>{@link #readFileToList(String, String)} 读取string list文件列表，一元素占一行</li>
 * <li>{@link #writeFile(String, String, boolean)} 写文件</li>
 * <li>{@link #writeFile(String, String)} 写文件</li>
 * <li>{@link #writeFile(String, List, boolean)} 写文件</li>
 * <li>{@link #writeFile(String, List)} 写文件</li>
 * <li>{@link #writeFile(String, InputStream)} 写文件</li>
 * <li>{@link #writeFile(String, InputStream, boolean)} 写文件</li>
 * <li>{@link #writeFile(File, InputStream)} 写文件</li>
 * <li>{@link #writeFile(File, InputStream, boolean)} 写文件</li>
 * </ul>
 * <ul>
 * 操作文件相关
 * <li>{@link #moveFile(File, File)} or {@link #moveFile(String, String)} 移动文件</li>
 * <li>{@link #copyFile(String, String)} 复制文件</li>
 * <li>{@link #getFileExtension(String)} 从文件地址中获取文件的后缀</li>
 * <li>{@link #getFileName(String)} 从文件地址里获取文件名，包含后缀</li>
 * <li>{@link #getFileNameWithoutExtension(String)} 从文件地址里获取文件名，不包含后缀</li>
 * <li>{@link #getFileSize(String)} 获取文件大小</li>
 * <li>{@link #deleteFile(String)} 删除文件或者目录</li>
 * <li>{@link #isFileExist(String)} 判断文件是否存在</li>
 * <li>{@link #isFolderExist(String)} 判断目录是否存在</li>
 * <li>{@link #makeDirs(String)} 创建此文件包含的所有目录</li>
 * </ul>
 * @author LiJiaJian
 * @date 2016/5/20 15:39
 */

public class FileUtils {

    public final static String FILE_EXTENSION_SEPARATOR = ".";

    private FileUtils() {
        throw new AssertionError();
    }

    /**
     * 读取文件内容
     * @param filePath 文件路径
     * @return 如果文件不存在返回null；否则返回文件内容
     * @throws RuntimeException
     */
    public static String readFile(String filePath) {
        String fileContent = "";
        File file = new File(filePath);
        if (file == null || !file.isFile()) {
            return null;
        }

        BufferedReader reader = null;
        try {
            InputStreamReader is = new InputStreamReader(new FileInputStream(file));
            reader = new BufferedReader(is);
            String line = null;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                fileContent += line + " ";
            }
            reader.close();
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileContent;
    }

    /**
     * 读取string list文件列表，一元素占一行
     * @param filePath
     * @param charsetName {@link java.nio.charset.Charset </code>charset<code>}
     * @return 如果文件不存在返回null；否则返回文件内容列表
     * @throws RuntimeException
     */
    public static List<String> readFileToList(String filePath, String charsetName) {
        File file = new File(filePath);
        List<String> fileContent = new ArrayList<String>();
        if (file == null || !file.isFile()) {
            return null;
        }

        BufferedReader reader = null;
        try {
            InputStreamReader is = new InputStreamReader(new FileInputStream(file), charsetName);
            reader = new BufferedReader(is);
            String line = null;
            while ((line = reader.readLine()) != null) {
                fileContent.add(line);
            }
            reader.close();
            return fileContent;
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }

    /**
     * 写文件
     * @param filePath 文件路径
     * @param content 写入内容
     * @param append 如果是true，则在文件末尾追加内容，否则清空文件内容重新写入
     * @return 如果传入内同为空则返回false，其他情况true
     * @throws RuntimeException
     */
    public static boolean writeFile(String filePath, String content, boolean append) {
        if (TextUtils.isEmpty(content)) {
            return false;
        }

        FileWriter fileWriter = null;
        try {
            makeDirs(filePath);
            fileWriter = new FileWriter(filePath, append);
            fileWriter.write(content);
            fileWriter.close();
            return true;
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }

    /**
     * 写文件
     * @param filePath 文件路径
     * @param contentList 写入内容列表
     * @param append 如果是true，则在文件末尾追加内容，否则清空文件内容重新写入
     * @return 如果传入内同为空则返回false，其他情况true
     * @throws RuntimeException
     */
    public static boolean writeFile(String filePath, List<String> contentList, boolean append) {
        if (contentList == null || contentList.size() == 0) {
            return false;
        }

        FileWriter fileWriter = null;
        try {
            makeDirs(filePath);
            fileWriter = new FileWriter(filePath, append);
            int i = 0;
            for (String line : contentList) {
                if (i++ > 0) {
                    fileWriter.write("\r\n");
                }
                fileWriter.write(line);
            }
            fileWriter.close();
            return true;
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }

    /**
     * 写文件，默认清空文件重新写入
     *
     * @param filePath 文件路径
     * @param content 写入内容
     * @return 如果传入内同为空则返回false，其他情况true
     */
    public static boolean writeFile(String filePath, String content) {
        return writeFile(filePath, content, false);
    }

    /**
     * 写文件，默认清空文件重新写入
     *
     * @param filePath 文件路径
     * @param contentList 写入内容列表
     * @return 如果传入内同为空则返回false，其他情况true
     */
    public static boolean writeFile(String filePath, List<String> contentList) {
        return writeFile(filePath, contentList, false);
    }

    /**
     * 写文件，输入流内容默认清空文件重新写入
     *
     * @param filePath 文件路径
     * @param stream 内容输入流
     * @return 如果传入内同为空则返回false，其他情况true
     * @see {@link #writeFile(String, InputStream, boolean)}
     */
    public static boolean writeFile(String filePath, InputStream stream) {
        return writeFile(filePath, stream, false);
    }

    /**
     * 写文件
     *
     * @param filePath 文件路径
     * @param stream 内容输入流
     * @param append 如果是true，则在文件末尾追加内容，否则清空文件内容重新写入
     * @return return true
     * @throws RuntimeException
     */
    public static boolean writeFile(String filePath, InputStream stream, boolean append) {
        return writeFile(filePath != null ? new File(filePath) : null, stream, append);
    }

    /**
     * 写文件，输入流内容默认清空文件重新写入
     *
     * @param file 要写入的文件
     * @param stream 内容输入流
     * @return
     * @see {@link #writeFile(File, InputStream, boolean)}
     */
    public static boolean writeFile(File file, InputStream stream) {
        return writeFile(file, stream, false);
    }

    /**
     * 写文件
     *
     * @param file 写入目标文件
     * @param stream 内容输入流
     * @param append 如果是true，则在文件末尾追加内容，否则清空文件内容重新写入
     * @return return true
     * @throws RuntimeException
     */
    public static boolean writeFile(File file, InputStream stream, boolean append) {
        OutputStream o = null;
        try {
            makeDirs(file.getAbsolutePath());
            o = new FileOutputStream(file, append);
            byte data[] = new byte[1024];
            int length = -1;
            while ((length = stream.read(data)) != -1) {
                o.write(data, 0, length);
            }
            o.flush();
            return true;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException occurred. ", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            if (o != null) {
                try {
                    o.close();
                    stream.close();
                } catch (IOException e) {
                    throw new RuntimeException("IOException occurred. ", e);
                }
            }
        }
    }

    /**
     * 移动文件
     *
     * @param sourceFilePath 源文件地址
     * @param destFilePath 移动目标地址
     */
    public static void moveFile(String sourceFilePath, String destFilePath) {
        if (TextUtils.isEmpty(sourceFilePath) || TextUtils.isEmpty(destFilePath)) {
            throw new RuntimeException("Both sourceFilePath and destFilePath cannot be null.");
        }
        moveFile(new File(sourceFilePath), new File(destFilePath));
    }

    /**
     * 移动文件
     *
     * @param srcFile 源文件
     * @param destFile 移动目标文件
     */
    public static void moveFile(File srcFile, File destFile) {
        boolean rename = srcFile.renameTo(destFile);
        if (!rename) {
            copyFile(srcFile.getAbsolutePath(), destFile.getAbsolutePath());
            deleteFile(srcFile.getAbsolutePath());
        }
    }

    /**
     * 复制文件
     *
     * @param sourceFilePath 源文件地址
     * @param destFilePath 移动目标地址
     * @return
     * @throws RuntimeException
     */
    public static boolean copyFile(String sourceFilePath, String destFilePath) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(sourceFilePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException occurred. ", e);
        }
        return writeFile(destFilePath, inputStream);
    }


    /**
     * 从文件地址里获取文件名，不包含后缀
     * <pre>
     *      getFileNameWithoutExtension(null)               =   null
     *      getFileNameWithoutExtension("")                 =   ""
     *      getFileNameWithoutExtension("   ")              =   "   "
     *      getFileNameWithoutExtension("abc")              =   "abc"
     *      getFileNameWithoutExtension("a.mp3")            =   "a"
     *      getFileNameWithoutExtension("a.b.rmvb")         =   "a.b"
     *      getFileNameWithoutExtension("c:\\")              =   ""
     *      getFileNameWithoutExtension("c:\\a")             =   "a"
     *      getFileNameWithoutExtension("c:\\a.b")           =   "a"
     *      getFileNameWithoutExtension("c:a.txt\\a")        =   "a"
     *      getFileNameWithoutExtension("/home/admin")      =   "admin"
     *      getFileNameWithoutExtension("/home/admin/a.txt/b.mp3")  =   "b"
     * </pre>
     *
     * @param filePath
     * @return 文件名
     * @see
     */
    public static String getFileNameWithoutExtension(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return filePath;
        }

        int extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        int filePosi = filePath.lastIndexOf(File.separator);
        if (filePosi == -1) {
            return (extenPosi == -1 ? filePath : filePath.substring(0, extenPosi));
        }
        if (extenPosi == -1) {
            return filePath.substring(filePosi + 1);
        }
        return (filePosi < extenPosi ? filePath.substring(filePosi + 1, extenPosi) : filePath.substring(filePosi + 1));
    }

    /**
     * 从文件地址里获取文件名，包含后缀
     *
     * <pre>
     *      getFileName(null)               =   null
     *      getFileName("")                 =   ""
     *      getFileName("   ")              =   "   "
     *      getFileName("a.mp3")            =   "a.mp3"
     *      getFileName("a.b.rmvb")         =   "a.b.rmvb"
     *      getFileName("abc")              =   "abc"
     *      getFileName("c:\\")              =   ""
     *      getFileName("c:\\a")             =   "a"
     *      getFileName("c:\\a.b")           =   "a.b"
     *      getFileName("c:a.txt\\a")        =   "a"
     *      getFileName("/home/admin")      =   "admin"
     *      getFileName("/home/admin/a.txt/b.mp3")  =   "b.mp3"
     * </pre>
     *
     * @param filePath
     * @return file name from path, include suffix
     */
    public static String getFileName(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return filePath;
        }

        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? filePath : filePath.substring(filePosi + 1);
    }

    /**
     * 获取文件所在的文件夹路径
     *
     * <pre>
     *      getFolderName(null)               =   null
     *      getFolderName("")                 =   ""
     *      getFolderName("   ")              =   ""
     *      getFolderName("a.mp3")            =   ""
     *      getFolderName("a.b.rmvb")         =   ""
     *      getFolderName("abc")              =   ""
     *      getFolderName("c:\\")              =   "c:"
     *      getFolderName("c:\\a")             =   "c:"
     *      getFolderName("c:\\a.b")           =   "c:"
     *      getFolderName("c:a.txt\\a")        =   "c:a.txt"
     *      getFolderName("c:a\\b\\c\\d.txt")    =   "c:a\\b\\c"
     *      getFolderName("/home/admin")      =   "/home"
     *      getFolderName("/home/admin/a.txt/b.mp3")  =   "/home/admin/a.txt"
     * </pre>
     *
     * @param filePath 文件路径
     * @return
     */
    public static String getFolderName(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return filePath;
        }

        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? "" : filePath.substring(0, filePosi);
    }

    /**
     * 从文件地址中获取文件的后缀
     *
     * <pre>
     *      getFileExtension(null)               =   ""
     *      getFileExtension("")                 =   ""
     *      getFileExtension("   ")              =   "   "
     *      getFileExtension("a.mp3")            =   "mp3"
     *      getFileExtension("a.b.rmvb")         =   "rmvb"
     *      getFileExtension("abc")              =   ""
     *      getFileExtension("c:\\")              =   ""
     *      getFileExtension("c:\\a")             =   ""
     *      getFileExtension("c:\\a.b")           =   "b"
     *      getFileExtension("c:a.txt\\a")        =   ""
     *      getFileExtension("/home/admin")      =   ""
     *      getFileExtension("/home/admin/a.txt/b")  =   ""
     *      getFileExtension("/home/admin/a.txt/b.mp3")  =   "mp3"
     * </pre>
     *
     * @param filePath 文件路径
     * @return
     */
    public static String getFileExtension(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            return filePath;
        }

        int extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        int filePosi = filePath.lastIndexOf(File.separator);
        if (extenPosi == -1) {
            return "";
        }
        return (filePosi >= extenPosi) ? "" : filePath.substring(extenPosi + 1);
    }

    /**
     * 创建此文件包含的所有目录 <br/>
     * <br/>
     * <ul>
     * <strong>注意事项：</strong>
     * <li>makeDirs("C:\\Users\\aaa") 只能创建到Users文件夹</li>
     * <li>makeFolder("C:\\Users\\aaa\\") 能创建到aaa文件夹</li>
     * </ul>
     *
     * @param filePath 文件路径
     * @return
     *         <ul>
     *         <li>如果 {@link FileUtils#getFolderName(String)} 返回null则结果返回false</li>
     *         <li>如果目标地址已经存在则返回true</li>
     *         </ul>
     */
    public static boolean makeDirs(String filePath) {
        String folderName = getFolderName(filePath);
        if (TextUtils.isEmpty(folderName)) {
            return false;
        }

        File folder = new File(folderName);
        return (folder.exists() && folder.isDirectory()) ? true : folder.mkdirs();
    }

    /**
     * 判断文件是否存在
     * @param filePath 文件地址
     * @return
     */
    public static boolean isFileExist(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            return false;
        }

        File file = new File(filePath);
        return (file.exists() && file.isFile());
    }

    /**
     * 判断目录是否存在
     *
     * @param directoryPath 目录地址
     * @return
     */
    public static boolean isFolderExist(String directoryPath) {
        if (StringUtils.isBlank(directoryPath)) {
            return false;
        }

        File dire = new File(directoryPath);
        return (dire.exists() && dire.isDirectory());
    }

    /**
     * 删除文件或者目录
     * <ul>
     * <li>如果路径是null或者空返回true</li>
     * <li>如果路径不存在返回true</li>
     * <li>如果路径存在递归删除返回true</li>
     * <ul>
     *
     * @param path 路径
     * @return
     */
    public static boolean deleteFile(String path) {
        if (StringUtils.isBlank(path)) {
            return true;
        }

        File file = new File(path);
        if (!file.exists()) {
            return true;
        }
        if (file.isFile()) {
            return file.delete();
        }
        if (!file.isDirectory()) {
            return false;
        }
        for (File f : file.listFiles()) {
            if (f.isFile()) {
                f.delete();
            } else if (f.isDirectory()) {
                deleteFile(f.getAbsolutePath());
            }
        }
        return file.delete();
    }

    /**
     * 获取文件大小
     * <ul>
     * <li>果路径是null或者空返回-1</li>
     * <li>如果路径存在并且它是一个文件则返回文件大小否则返回-1</li>
     * <ul>
     *
     * @param path 路径
     * @return
     */
    public static long getFileSize(String path) {
        if (StringUtils.isBlank(path)) {
            return -1;
        }

        File file = new File(path);
        return (file.exists() && file.isFile() ? file.length() : -1);
    }


    /**
     * 获取目录文件下所有文件
     * @param path
     * @return
     */
    public static List<File> getFileList(String path){
        File file = new File(path);
        if (!file.exists()){
            return null;
        }
        File[] files = file.listFiles();
        List<File> fileList = new ArrayList<File>();
        for (File f : files) {
            fileList.add(f);
        }
        return fileList;

    }

    public static class CompratorByLastModified implements Comparator<File>
    {
        public int compare(File f1, File f2) {
            long diff = f1.lastModified()-f2.lastModified();
            if(diff>0)
                return 1;
            else if(diff==0)
                return 0;
            else
                return -1;
        }
        public boolean equals(Object obj){
            return true;
        }
    }
    /**
     * 获取APP 存储的路径
     *
     * @param context
     * @return
     */
    public static String getAppStorageDir(Context context) {
        // 获取Android程序在Sd上的保存目录约定 当程序卸载时，系统会自动删除。
        File f = context.getExternalFilesDir(null);
        // 如果约定目录不存在
        if (f == null) {
            // 获取外部存储目录即 SDCard
            return getStorageDir(context);
        } else {
            String storageDirectory = f.getAbsolutePath();
//            UetLog.i(TAG, "项目存储路径采用系统给的路径地址  storageDirectory:" + storageDirectory);
            return storageDirectory;
        }

    }
    /**
     * 获取可用的sdcard路径
     *
     * @param context
     * @return
     */
    public static String getStorageDir(Context context) {
        return getStorageDir(context, true);
    }

    /**
     * 获取可用的sdcard路径
     *
     * @param context
     * @return
     */
    public static String getStorageDir(Context context, boolean isAllowUseCache) {
        // 获取外部存储目录即 SDCard
        String storageDirectory = Environment.getExternalStorageDirectory().toString();
        File fDir = new File(storageDirectory);
        // 如果sdcard目录不可用
        if (!fDir.canWrite()) {
            // 获取可用
            storageDirectory = getSDCardDir();
            if (storageDirectory != null) {
                storageDirectory = storageDirectory + File.separator + "uet" + File.separator + "teacherpad";
//                UetLog.i(TAG, "项目存储路径采用自动找寻可用存储空间的方式   storageDirectory:" + storageDirectory);
                return storageDirectory;

            } else {
                if (isAllowUseCache) {
//                    UetLog.e(TAG, "没有找到可用的存储路径  采用cachedir");
                    return context.getCacheDir().toString();
                } else {
                    return null;
                }
            }
        } else {
            storageDirectory = storageDirectory + File.separator + "uet" + File.separator + "teacherpad";
//            UetLog.i(TAG, "项目存储路径采用sdcard的地址   storageDirectory:" + storageDirectory);
            return storageDirectory;
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
        for (int i = 0; i < listFiles.length; i++) {
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
//            UetLog.i(TAG, "httpFrame threadName:" + Thread.currentThread().getName()
//                    + " getSDFreeSize  无法计算文件夹大小 folderPath:" + filePath);
        }

        return -1;
    }

    /**
     * get file md5
     *
     * @param file
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String getFileMD5(File file) throws NoSuchAlgorithmException, IOException {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest;
        FileInputStream in;
        byte buffer[] = new byte[1024];
        int len;
        digest = MessageDigest.getInstance("MD5");
        in = new FileInputStream(file);
        while ((len = in.read(buffer, 0, 1024)) != -1) {
            digest.update(buffer, 0, len);
        }
        in.close();
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }

    /**
     * 根据Uri返回文件绝对路径
     * 兼容了file:///开头的 和 content://开头的情况
     */
    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        }
        else if (ContentResolver.SCHEME_FILE.equalsIgnoreCase(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equalsIgnoreCase(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * 检查文件是否存在
     */
    public static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }
}
