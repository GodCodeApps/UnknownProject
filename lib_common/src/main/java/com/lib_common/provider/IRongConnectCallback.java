package com.lib_common.provider;

/**
 * Peng YanMing on 2018\8\22 0022
 */
public interface IRongConnectCallback {
    void onTokenIncorrect();

    void onSuccess(String userid);

    void onError(int errorCode);
}
