package com.lib_common.http;




import com.lib_common.utils.KLog;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Author: ZhongWeiZhi
 * Date: 2017/4/12 18:34
 * Description: okHttp拦截器，用来打印请求报文
 */
public class LogInterceptor implements Interceptor {

    private static final String TAG = "LogInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        // 请求
        StringBuilder requestMessage = new StringBuilder("--> ");
        Request request = chain.request();
        String contentType = request.body().contentType().toString();
        if(!contentType.contains("application/json")){
            return chain.proceed(request);
        }
        requestMessage
                .append("tag:").append(request.tag())
                .append("\t")
                .append(request.method())
                .append(" ")
                .append(request.url());
        RequestBody requestBody = request.body();
        if (requestBody != null) {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            requestMessage
                    .append("\n")
                    .append(format(convertUnicode(buffer.readUtf8())));
        }
        KLog.d(TAG, requestMessage.toString());

        // 响应
        StringBuilder responseMessage = new StringBuilder("<-- ");
        long startNs = System.nanoTime();
        Response response;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            responseMessage
                    .append("tag:").append(request.tag())
                    .append("\tHTTP FAILED: ").append(e);
            KLog.d(TAG, responseMessage.toString());
            throw e;
        }
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
        MediaType mediaType = response.body().contentType();
        responseMessage
                .append("tag:").append(response.request().tag())
                .append("\t")
                .append(response.code())
                .append(" ")
                .append(response.message())
                .append(" ")
                .append(response.request().url())
                .append(" (").append(tookMs).append("ms)");

        String responseBody = response.body().string();
        if (response.code() == 200) {
            responseMessage
                    .append("\n")
                    .append(format(convertUnicode(responseBody)));
        }

        if (responseMessage.toString().length() > 4000) {
            KLog.d(TAG, "sb.length = " + responseMessage.toString().length());
            int chunkCount = responseMessage.toString().length() / 4000;     // integer division
            for (int i = 0; i <= chunkCount; i++) {
                int max = 4000 * (i + 1);
                if (max >= responseMessage.toString().length()) {
                    KLog.d(TAG, "chunk " + i + " of " + chunkCount + ":" + responseMessage.toString().substring(4000 * i));
                } else {
                    KLog.d(TAG, "chunk " + i + " of " + chunkCount + ":" + responseMessage.toString().substring(4000 * i, max));
                }
            }
        } else {
            KLog.d(TAG, responseMessage.toString());
        }

        return response.newBuilder()
                .body(ResponseBody.create(mediaType, responseBody))
                .build();
    }

    private String format(String jsonStr) {
        int level = 0;
        StringBuilder jsonFormatStr = new StringBuilder();
        for (int i = 0; i < jsonStr.length(); i++) {
            char c = jsonStr.charAt(i);
            if (level > 0 && '\n' == jsonFormatStr.charAt(jsonFormatStr.length() - 1)) {
                jsonFormatStr.append(getLevelStr(level));
            }
            switch (c) {
                case '{':
                case '[':
                    jsonFormatStr.append(c).append("\n");
                    level++;
                    break;
                case ',':
                    jsonFormatStr.append(c).append("\n");
                    break;
                case '}':
                case ']':
                    jsonFormatStr.append("\n");
                    level--;
                    jsonFormatStr.append(getLevelStr(level));
                    jsonFormatStr.append(c);
                    break;
                default:
                    jsonFormatStr.append(c);
                    break;
            }
        }

        return jsonFormatStr.toString();

    }

    private String getLevelStr(int level) {
        StringBuilder levelStr = new StringBuilder();
        for (int levelI = 0; levelI < level; levelI++) {
            levelStr.append("\t");
        }
        return levelStr.toString();
    }

    private String convertUnicode(String ori) {
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
}
