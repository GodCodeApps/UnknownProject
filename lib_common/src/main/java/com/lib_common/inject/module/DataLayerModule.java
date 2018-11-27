package com.lib_common.inject.module;

import com.lib_common.service.NewManager;
import com.lib_common.service.VideoServiceManager;
import com.lib_common.service.base.DataLayer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * DataLayerModule
 *
 * @author Peng YanMing 2017/4/28
 */
@Module
public class DataLayerModule {
    @Singleton
    @Provides
    public VideoServiceManager provideVideoServiceManager() {
        return new VideoServiceManager();
    }

    @Singleton
    @Provides
    public NewManager provideNewManager() {
        return new NewManager();
    }

    @Singleton
    @Provides
    public DataLayer provideDataLayer(VideoServiceManager VideoServiceManager, NewManager doubanManager) {
        return new DataLayer(VideoServiceManager, doubanManager);
    }
}
