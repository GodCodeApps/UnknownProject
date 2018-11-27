package com.lib_common.service.base;

import com.google.gson.Gson;
import com.lib_common.inject.component.ApplicationComponent;
import com.lib_common.net.ClientAPI;

import javax.inject.Inject;

/**
 * Peng YanMing on 2018\11\27 0027
 */
public abstract class BaseManager {

    @Inject
    ClientAPI mApi;
    @Inject
    Gson mGson;

    public BaseManager() {
        ApplicationComponent.Instance.get().inject(this);
    }

    public ClientAPI getApi() {
        return mApi;
    }

    public Gson getGson() {
        return mGson;
    }
}
