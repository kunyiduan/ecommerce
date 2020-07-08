package com.kunyiduan.exception;

public enum ExceptionCode {

    USER_TELEPHONE_EXISTS(111,"手机号码已存在"),
    USER_TELEPHONE_NOT_EXISTS(210,"手机号码不存在"),
    USER_TELEPHONE_BOUND(212,"该手机号已绑定"),
    USER_TELEPHONE_ERROR(299,"手机号输入错误"),

    USER_PASSWORD_ERROR(210,"用户密码错误"),

    USER_MOBILECODE_ERROR(240,"手机验证码错误"),
    USER_MOBILECODE_EXPIRED(311,"手机验证码已过期"),

    USER_REQUEST_FREQUENCY_LIMIT(520,"请求频率超过限制"),
    USER_REQUEST_PARAM_ERROR(433,"请求参数错误"),

    USER_AUTHORIZATION_NOT_EXISTS(223,"用户未获得授权"),

    USER_LOGIN_ERROR(251,"用户登录异常"),

    TOKEN_EXPIRED(230,"token已过期"),
    TOKEN_FORMAT_ERROR(250,"token格式异常"),

    WECHAT_OPENID_NULL(178,"无法获取微信openid"),

    DB_DUPLICATEKEY(341,"主键冲突");

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

    private ExceptionCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
