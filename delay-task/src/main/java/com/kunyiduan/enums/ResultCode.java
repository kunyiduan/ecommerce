package com.kunyiduan.enums;

public enum  ResultCode {

    /* 成功状态码 */
    SUCCESS("A200", "成功");

    /**
     * 状态码
     */
    private String code;

    /**
     * 提示信息
     */
    private String message;

    private ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
