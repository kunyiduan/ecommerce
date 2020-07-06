package com.kunyiduan.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;

/**
 * 响应dto
 * @author daoqidelv
 * @createdate 2017年10月15日
 */
@Data
public class ResponseDto<T> {

    /**
     * 状态码
     */
    private int code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 提示信息
     */
    private long sysTime = System.currentTimeMillis();

    @ApiModelProperty("参数data体")
    @Valid
    private T data;

    public ResponseDto(int code , String msg, T t){
        this.code = code ;
        this.msg = msg;
        this.data = t;
    };
    public ResponseDto(){

    };

    public ResponseDto(T t){
        this.code = 200 ;
        this.msg = "请求成功";
        this.data = t;
    };

    public ResponseDto(int code , T t){
        this.code = code ;
        this.msg = "请求成功";
        this.data = t;
    };

    public ResponseDto success (){
        return new ResponseDto(200,"请求成功",null);
    };

    public ResponseDto success (T t){
        return new ResponseDto(200,"请求成功",t);
    };

    public ResponseDto error(){
        return new ResponseDto(500,"服务器异常",null);
    };
    public ResponseDto error(String msg){
        return new ResponseDto(500,msg,null);
    };

    public ResponseDto error (int code , String msg){
        return new ResponseDto(code,msg,null);
    };
    public ResponseDto error (int code , String msg, T t){
        return new ResponseDto(code,msg,t);
    };

}
