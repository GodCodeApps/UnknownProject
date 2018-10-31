package com.lib_common.entity;

/**
 * @author ZhongWeiZhi
 * @date 2018/8/16  10:33
 * @description oss   STS认证
 */
public class AliAuthRes {
    /**
     * 鉴权key
     */
    private String AccessKeyId;
    /**
     * 鉴权secret
     */
    private String AccessKeySecret;
    /**
     * 鉴权token
     */
    private String SecurityToken;
    /**
     * 鉴权时间戳
     */
    private String Expiration;

    public String getAccessKeyId() {
        return AccessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.AccessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return AccessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.AccessKeySecret = accessKeySecret;
    }

    public String getSecurityToken() {
        return SecurityToken;
    }

    public void setSecurityToken(String securityToken) {
        this.SecurityToken = securityToken;
    }

    public String getExpiration() {
        return Expiration;
    }

    public void setExpiration(String expiration) {
        this.Expiration = expiration;
    }
}
