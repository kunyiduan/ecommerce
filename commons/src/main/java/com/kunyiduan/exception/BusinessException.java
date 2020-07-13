package com.kunyiduan.exception;

import com.kunyiduan.enums.ResultCode;

/**
 * 自定义异常
 *
 * @author achilles
 * @version 1.0
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;
    private Object data;
    private ResultCode resultCode;

    public BusinessException() {
        super();
    }

    public BusinessException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BusinessException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public BusinessException(int code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public BusinessException(int code, String msg, Object data) {
        super(msg);
        this.msg = msg;
        this.code = code;
        this.data = data;

    }

    public BusinessException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.msg = resultCode.getMessage();
        this.code = resultCode.getCode();
    }

    public BusinessException(ResultCode resultCode, Throwable e) {
        super(resultCode.getMessage(), e);
        this.msg = resultCode.getMessage();
        this.code = resultCode.getCode();
    }

    public BusinessException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}


