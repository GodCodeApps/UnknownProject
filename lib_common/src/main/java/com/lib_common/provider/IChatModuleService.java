package com.lib_common.provider;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * Peng YanMing on 2018\8\22 0022
 */
public interface IChatModuleService extends IProvider {
    void getConnect(String token, IRongConnectCallback rongConnectCallback);

    void setUserInfo(String userId, String userName, String imageUrl);
    void logOut();
}
