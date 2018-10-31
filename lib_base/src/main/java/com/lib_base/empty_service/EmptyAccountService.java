package com.lib_base.empty_service;


import com.lib_base.bean.LoginInfoBean;
import com.lib_base.service.IAccountService;

public class EmptyAccountService implements IAccountService {
    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public String getAccountId() {
        return null;
    }

    @Override
    public String getToken() {
        return null;
    }

    @Override
    public LoginInfoBean getLoginUser() {
        return null;
    }

    @Override
    public void setPhone(String phone) {

    }

    @Override
    public void setImageUrl(String imageUrl) {

    }

    @Override
    public void setNickname(String nickname) {

    }

}
