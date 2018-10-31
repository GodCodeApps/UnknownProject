package com.lib_common.base;

import android.os.Message;

/**
 * 弱引用handler消息回调
 * @author ZhongWeiZhi
 * @date 2017/9/30
 */
public interface WeakHandlerCallback {
    /**
     * 消息队列回调
     * @param msg
     * @return
     */
    boolean handleMessage(Message msg);
}
