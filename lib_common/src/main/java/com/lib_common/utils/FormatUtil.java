package com.lib_common.utils;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/10/7.
 */

public class FormatUtil {

    /**
     * 判断是否是正确的手机号码
     *
     * @param mobiles
     * @return true正确的手机号码，否则是错误的手机号码
     */
    public static boolean isMobileNO(String mobiles) {
        String s = FormatUtil.trimStr(mobiles);
        if (null != s && s.length() >= 11) {
            //Pattern p = Pattern.compile("^(0|86|17951)?(13[0-9]|15[0-9]|17[0-9]|18[0-9]|19[0-9]|14[57])[0-9]{8}$");
            Pattern p = Pattern.compile("^1[0-9]{10}$");
            Matcher m = p.matcher(mobiles);
            return m.matches();
        } else {
            return false;
        }
    }
    /**
     * @return 清空字符串空格
     */
    public static String trimStr(String str) {
        if (!FormatUtil.isTextEmpty(str)) {
            return str.trim().replace(" ", "");
        }
        return "";
    }

    /**
     * 判断字符序列、字符串是V1/User/ModifyPassword否为空
     *
     * @param charSequence
     * @return
     */
    public static boolean isTextEmpty(CharSequence charSequence) {
        if (charSequence == null || charSequence.toString().trim().length() == 0 || "null".equals(charSequence)) {
            return true;
        }
        return false;
    }

    /**
     * 判断密码是否符合要求
     * @param str
     * @return
     */
    public static boolean checkPassword(String str) {
        String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";//其他需要，直接修改正则表达式就好
        return str.matches(regex);
    }

    /**
     * 判断字符串是不是以字母开头
     */
    public static Boolean checkStartWithLetter(String str){
        if(TextUtils.isEmpty(str)){
            return false;
        }
        //判断 新密码是否符合要求了
        Pattern pattern = Pattern.compile("[a-zA-Z].{5,15}");//可以包含空字符
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    //将bitmap转换为byte[]格式
    public static byte[] bmpToByteArray(final Bitmap bitmap, final boolean needRecycle){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
        if(needRecycle){
            bitmap.recycle();
        }
        byte[] result = output.toByteArray();
        try{
            output.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * list插入分隔符
     * @param stringList
     * @return
     */
    public static String listToString(List<Integer> stringList){
        if (stringList == null) {
            return null;
        }
        StringBuilder result=new StringBuilder();
        boolean flag=false;
        for (Integer string : stringList) {
            if (flag) {
                result.append(","); // 分隔符
            }else {
                flag=true;
            }
            result.append(string);
        }
        return result.toString();
    }

    /**
     * 去除空格符、换行符
     *
     * @param src
     * @return
     */
    public static String replaceBlank(String src) {
        String dest = "";
        if (src != null) {
            Pattern pattern = Pattern.compile("\t|\r|\n|\\s*");
            Matcher matcher = pattern.matcher(src);
            dest = matcher.replaceAll("");
        }
        return dest;
    }

    public static String convertUnicode(String ori) {
        char aChar;
        int len = ori.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = ori.charAt(x++);
            if (aChar == '\\') {
                aChar = ori.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = ori.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);

        }
        return outBuffer.toString();
    }

    /**
     * 对key做升序
     * @param json
     * @return
     */
    public static String getSortList(JSONObject json)
    {
        StringBuilder builder=new StringBuilder();
        List<String> list1=new ArrayList<String>();
        list1.addAll(json.keySet());
        Collections.sort(list1);
        for(int i=0;i<list1.size();i++)
        {
            char ch=' ';
            if(i<list1.size()-1)
            {
                ch='&';
            }
            builder.append(list1.get(i)+"="+json.get(list1.get(i)).toString()+ch);
        }

        return builder.toString().trim();
    }

    /**
     * @param str 待转换字符串
     * @return 转换后字符串
     * @throws UnsupportedEncodingException exception
     * @Description 将字符串中的emoji表情转换成可以在utf-8字符集数据库中保存的格式（表情占4个字节，需要utf8mb4字符集）
     */
    public static String emojiConvert1(String str) {
        String patternString = "([\\x{10000}-\\x{10ffff}\ud800-\udfff])";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            try {
                matcher.appendReplacement(
                        sb,
                        "[["
                                + URLEncoder.encode(matcher.group(1),
                                "UTF-8") + "]]");
            } catch (UnsupportedEncodingException e) {
                Log.e("ee", e.toString());
            }
        }
        matcher.appendTail(sb);
        Log.e("ee", "emojiConvert " + str + " to " + sb.toString()
                + ", len：" + sb.length());
        return str;
    }
    /**
     * @param str 转换后的字符串
     * @return 转换前的字符串
     * @throws UnsupportedEncodingException exception
     * @Description 还原utf8数据库中保存的含转换后emoji表情的字符串
     */
    public static String emojiRecovery2(String str){
        String patternString = "\\[\\[(.*?)\\]\\]";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(str);

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            try {
                matcher.appendReplacement(sb,
                        URLDecoder.decode(matcher.group(1), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                Log.e("ee", e.toString());
            }
        }
        matcher.appendTail(sb);
        Log.e("ee", "emojiRecovery " + str + " to " + sb.toString());
        return str;
    }

    public static boolean isEmojiCharacter(char codePoint) {
        return !((codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD) || ((codePoint >= 0x20) && codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }
}
