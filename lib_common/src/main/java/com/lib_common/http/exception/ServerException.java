package com.lib_common.http.exception;


import com.lib_common.entity.BaseBean;

/**
 * author hechao
 * date 2018/9/25 0025
 */
public class ServerException extends Exception {

    private BaseBean mBaseBean;

    public ServerException(BaseBean baseBean) {
        super(baseBean.getMsg());
        mBaseBean = baseBean;
    }

    public BaseBean getBaseBean() {
        return mBaseBean;
    }
}
