package com.lib_base.bean;

/**
 * @author ZhongWeiZhi
 * @date 2018/8/18  15:24
 * @description
 */
public class LoginBean {
    private String desc;//封号描述
    private int type;////1-登录 2-注册
    private LoginInfoBean info;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public LoginInfoBean getInfo() {
        return info;
    }

    public void setInfo(LoginInfoBean info) {
        this.info = info;
    }
}
