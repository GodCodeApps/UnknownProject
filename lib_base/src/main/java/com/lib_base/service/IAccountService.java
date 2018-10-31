package com.lib_base.service;


import com.lib_base.bean.LoginInfoBean;

public interface IAccountService {

    /**
     * 是否已经登录
     *
     * @return
     */
    boolean isLogin();

    /**
     * 获取登录用户的 AccountId
     *
     * @return
     */
    String getAccountId();

    /**
     * 获取登录用户的 Token
     *
     * @return
     */
    String getToken();
    /**
     * 获取登录用户的 Token
     *
     * @return
     */
    LoginInfoBean getLoginUser();

    /**
     * 更换手机号
     * @param phone
     */
    void setPhone(String phone);
    /**
     * 更换头像
     * @param imageUrl
     */
    void setImageUrl(String imageUrl);
    /**
     * 更换昵称
     * @param nickname
     */
    void setNickname(String nickname);
}
