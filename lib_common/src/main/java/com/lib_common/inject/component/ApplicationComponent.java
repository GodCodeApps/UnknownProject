package com.lib_common.inject.component;

import android.app.Application;
import android.support.annotation.NonNull;


import com.lib_common.base.BaseViewModel;
import com.lib_common.base.RxBaseActivity;
import com.lib_common.base.RxBaseFragment;
import com.lib_common.inject.module.ApplicationModule;
import com.lib_common.service.base.BaseManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * ApplicationComponent
 *
 * @author Peng YanMing 2017/4/28
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(RxBaseActivity activity);
    void inject(RxBaseFragment fragment);
//    void inject(BaseDialogFragment dialogFragment);
    void inject(BaseManager manager);
    void inject(BaseViewModel viewModel);
    // 可以获取 ApplicationModule 及其 includes 的所有 Module 提供的对象（方法名随意）
    Application getApplication();

    class Instance {

        private static ApplicationComponent sComponent;

        public static void init(@NonNull ApplicationComponent component) {
            sComponent = component;
        }

        public static ApplicationComponent get() {
            return sComponent;
        }
    }
}
