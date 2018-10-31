package com.lib_common.utils.constant;

/**
 * author hechao
 * date 2018/9/21 0021
 */
public interface CommonEventConstants {

    class CommunityEvent {
        /**
         * 动态刷新
         */
        public final static int DYNAMIC_REFRESH = 100010;

        /**
         * 搜索
         */
        public final static int PLAY_SEARCH = 100011;

        /**
         * 动态删除
         */
        public final static int DYNAMIC_DELETE = 100013;
    }

    class MeEvent {
        /**
         * 选择优惠券
         */
        public final static int CHOOSE_COUPONS = 10011;
        /**
         * 退出登录
         */
        public final static int LOGOUT = 10012;
        /**
         * 登录
         */
        public final static int LOGIN = 10013;

        /**
         * 用户头像更新
         */
        public final static int AVATAR_UPDATE = 10014;

        /**
         * 个人详情-未认证 只有动态
         */
        public static final int JUST_DYNAMIC = 10015;
        /**
         * 个人详情-已认证 有动态也有评价
         */
        public static final int JUST_DYNAMIC_SCORE = 10016;

        /**
         * 是否禁止滑动SwipeRefreshLayout
         */
        public static final int ISENABLE_SCROLL = 10017;
        /**
         * 游戏身份示例
         */
        public static final int  SHOW_GAMEIDENTITY= 10018;
        /**
         * 游戏实力示例
         */
        public static final int SHOW_GAMEPOWER = 10019;

        /**
         * 手持证件照示例
         */
        public static final int SHOW_HAND_IDCARD = 10020;
        /**
         * 游戏切换更改图片示例
         */
        public static final int  GAME_CHANGE= 10021;
        /**
         * 游戏币充值成功
         */
        public static final int  GAME_RECHARGE_SUCCESS= 10022;
        /**
         * 现金充值成功
         */
        public static final int  CASH_RECHARGE_SUCCESS= 10023;
        /**
         * 设置交易密码成功
         */
        public static final int  SET_TRAN_PWD_SUCCESS= 10024;
        /**
         * 提现成功
         */
        public static final int  PUT_FORWARD_SUCCESS= 10025;
        /**
         * 支付选择优惠券
         */
        public static final int  PAY_CHOOSE_COUPON = 10026;
        /**
         * 游戏认证（提交成功待审核）
         */
        public static final int  GAME_AUTH_SUBMITED = 10027;
        /**
         * 绑定第三方（微信认证）
         */
        public static final int  THIRD_LOGIN = 10028;
        /**
         * 注册关闭页面
         */
        public static final int  REGISTER_SUCCESS_CLOSE_PAGE = 10029;
        /**
         * 绑定第三方（QQ认证）
         */
        public static final int  QQ_LOGIN = 10030;
    }
}
