package com.lib_base.bean;

import java.io.Serializable;

/**
 * @author ZhongWeiZhi
 * @date 2018/8/21  15:26
 * @description  第三方登录实体
 */
public class LoginRequsetBean implements Serializable{
    private static final long serialVersionUID = -3540885688448821969L;

    private String account;//	账户字段，手机注册为手机号，微信注册为-unionid，QQ注册为 openid
    private String extend;//	账户扩展字段，手机和QQ注册为空，微信注册为openid
    private int gender;//性别：1-男，2-女
    private String imageUrl;//头像
    private String nickName;//昵称
    private int type;//类型	注册类型：1-手机，2-微信App,3-微信公众号，4-微信小程序，5-QQ
    private String mobile;//手机号
    private String regCode;//	注册码，手机验证后返回（有效期5分钟）

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRegCode() {
        return regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
