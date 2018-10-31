package com.lib_base.bean;

import java.io.Serializable;

/**
 * @author ZhongWeiZhi
 * @date 2018/8/18  15:26
 * @description
 */
public class LoginInfoBean implements Serializable{

    private static final long serialVersionUID = -6426531482314645019L;
    private int id;//用户ID
    private String imtoken;//IM的注册token
    private String nickname;//昵称
    private String imageurl;//头像
    private String birthday;//生日
    private String token;//登陆令牌token
    private int gender;//性别：1-男，2-女
    private int role;//角色：1-未认证，2-已认证（游戏）
    private String regCode;//注册校验码，后面提交注册信息一起提交
    private String mobile;//手机号

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImtoken() {
        return imtoken;
    }

    public void setImtoken(String imtoken) {
        this.imtoken = imtoken;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getRegCode() {
        return regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }
}
