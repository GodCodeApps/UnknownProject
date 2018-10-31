package com.lib_common.interceptor;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.lib_base.ServiceFactory;
import com.lib_common.utils.KLog;
import com.lib_common.utils.PageJumpUtils;
import com.lib_common.utils.constant.RouteConstants;

/**
 * @author ZhongWeiZhi
 * @date 2018/9/13  10:45
 * @description 登录拦截
 */
@Interceptor(priority = 4)
public class LoginInterceptor implements IInterceptor {
    @Override
    public void process(final Postcard postcard, final InterceptorCallback callback) {
        //if (ServiceFactory.getInstance().getAccountService().isLogin() && ServiceFactory.getInstance().getAccountService().getLoginUser() != null) {
            callback.onContinue(postcard);
//        } else {//未登录
//            callback.onInterrupt(null);
//            PageJumpUtils.jumpPageNotInterceptor(RouteConstants.LOGINACTIVITY);
//       }
    }

    @Override
    public void init(Context context) {
        KLog.d("init", LoginInterceptor.class.getSimpleName() + " has been inited");
    }
}
