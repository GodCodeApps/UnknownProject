package com.lib_common.entity;

/**
 * @author ZhongWeiZhi
 * @date 2018/8/28  19:25
 * @description
 */
public class BaseResultBean {
    private boolean result;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public BaseResultBean(boolean result) {
        this.result = result;
    }
}
