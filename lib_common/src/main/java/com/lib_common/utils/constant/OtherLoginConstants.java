package com.lib_common.utils.constant;

/**
 * @author ZhongWeiZhi
 * @date 2018/8/14  9:42
 * @description 第三方登录常量
 */
public class OtherLoginConstants {
    /**
     * 手机号注册
     */
    public static final int PHONE_LOGIN_TYPE = 1;
    /**
     * 微信App
     */
    public static final int WECHAT_APP_LOGIN_TYPE = 2;
    /**
     * 微信公众号
     */
    public static final int WECHAT_GONG_LOGIN_TYPE = 3;
    /**
     * 微信小程序
     */
    public static final int WECHAT_XIAO_LOGIN_TYPE = 4;
    /**
     * QQ
     */
    public static final int QQ_LOGIN_TYPE = 5;
    /**
     * 支付宝
     */
    public static final int ALIBABA_LOGIN_TYPE = 6;
    public static final String WECHAT_APP_ID = "wx9e32dddb1c159bf0";
    public static final String WECHAT_APP_SECRET = "6377d4facb95e73c0a2fab20dd3ffcc1";
    public static final String WECHAT_SCOPE = "snsapi_userinfo";
    public static final String QQ_APP_ID = "101502423";
    public static final String QQ_APP_SECRET = "b939570b6adbfcc75d8b4dd8849e0f4a";
    public static final String QQ_SCOPE = "get_user_info";
    /**
     * 当前 DEMO 应用的 APP_KEY，第三方应用应该使用自己的 APP_KEY 替换该 APP_KEY
     */
    public static final String WEIBO_APP_KEY = "42230719";
    public static final String WEIBO_APP_SECRET = "f8487ae37673a4370b7bb669f0655735";
    /**
     * 当前 DEMO 应用的回调页，第三方应用可以使用自己的回调页。
     * <p>
     * <p>
     * 注：关于授权回调页对移动客户端应用来说对用户是不可见的，所以定义为何种形式都将不影响，
     * 但是没有定义将无法使用 SDK 认证登录。
     * 建议使用默认回调页：https://api.weibo.com/oauth2/default.html
     * </p>
     */
    public static final String WEIBO_REDIRECT_URL = "http://www.sharesdk.cn";
    /**
     * Scope 是 OAuth2.0 授权机制中 authorize 接口的一个参数。通过 Scope，平台将开放更多的微博
     * 核心功能给开发者，同时也加强用户隐私保护，提升了用户体验，用户在新 OAuth2.0 授权页中有权利
     * 选择赋予应用的功能。
     * <p>
     * 我们通过新浪微博开放平台-->管理中心-->我的应用-->接口管理处，能看到我们目前已有哪些接口的
     * 使用权限，高级权限需要进行申请。
     * <p>
     * 目前 Scope 支持传入多个 Scope 权限，用逗号分隔。
     * <p>
     * 有关哪些 OpenAPI 需要权限申请，请查看：http://open.weibo.com/wiki/%E5%BE%AE%E5%8D%9AAPI
     * 关于 Scope 概念及注意事项，请查看：http://open.weibo.com/wiki/Scope
     */
    public static final String WEIBO_SCOPE =
            "email,direct_messages_read,direct_messages_write,"
                    + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                    + "follow_app_official_microblog," + "invitation_write";
}
