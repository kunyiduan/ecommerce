package com.kunyiduan.exception;

/**
 * 自定义异常
 *
 * @author achilles
 * @version 1.0
 */
public class LocalException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;
    private Object data;
    private ExceptionCode exceptionCode;

    public LocalException() {
        super();
    }

    public LocalException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public LocalException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public LocalException(int code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public LocalException(int code, String msg, Object data) {
        super(msg);
        this.msg = msg;
        this.code = code;
        this.data = data;

    }

    public LocalException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.msg = exceptionCode.getMessage();
        this.code = exceptionCode.getCode();
    }

    public LocalException(ExceptionCode exceptionCode, Throwable e) {
        super(exceptionCode.getMessage(), e);
        this.msg = exceptionCode.getMessage();
        this.code = exceptionCode.getCode();
    }

    public LocalException(String msg, int code, Throwable e) {
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


