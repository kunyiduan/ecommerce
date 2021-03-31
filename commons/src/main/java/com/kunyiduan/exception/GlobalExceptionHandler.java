package com.kunyiduan.exception;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.kunyiduan.enums.ResultCode;
import com.kunyiduan.response.ErrorResult;
import com.netflix.client.ClientException;
import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.crypto.BadPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.UnexpectedTypeException;

import java.util.List;

import static com.kunyiduan.enums.ResultCode.ENCRYPTION_ERROR;

/**
 * 全局异常处理器
 *
 * @author achilles
 * @version 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    private String handle(List<FieldError> fieldErrors) {
        StringBuilder sb = new StringBuilder();
        for (FieldError obj : fieldErrors) {
            sb.append(obj.getField());
            sb.append("=[");
            sb.append(obj.getDefaultMessage());
            sb.append("]  ");
        }
        return sb.toString();
    }

    /**
     * 处理运行时异常
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ErrorResult handleThrowable(Throwable e, HttpServletRequest request) {
        ErrorResult error = ErrorResult.fail(ResultCode.SYSTEM_ERROR, e);
        log.error("URL:{} ,系统异常: ", request.getRequestURI(), e);
        return error;
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BusinessException.class)
    public ErrorResult handleBusinessException(BusinessException e, HttpServletRequest request) {
        ErrorResult error = ErrorResult.builder().status(e.getCode())
                .message(e.getMessage())
                .exception(e.getClass().getName())
                .build();
        log.warn("URL:{} ,业务异常:{}", request.getRequestURI(), error);
        return error;
    }

    /**
     * validator 统一异常封装
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String msgs = this.handle(e.getBindingResult().getFieldErrors());
        ErrorResult error = ErrorResult.fail(ResultCode.PARAM_IS_INVALID, e, msgs);
        log.warn("URL:{} ,参数校验异常:{}", request.getRequestURI(), msgs);
        return error;
    }

    /**
     * Assert的异常统一封装
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
        ErrorResult error = ErrorResult.builder().status(4000)
                .message(e.getMessage())
                .exception(e.getClass().getName())
                .build();
        log.warn("URL:{} ,业务校验异常:{}", request.getRequestURI(), e);
        return error;
    }

//    /**
//     * 没有明细异常时，500+
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(Exception.class)
//    public ErrorResult handleException(Exception e) {
//        log.error(e.getMessage(), e);
//        return new ResponseDto().error();
//    }

    /**
     * 数据库主键冲突
     *
     * @param e
     * @return
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public ErrorResult duplicateKeyException(DuplicateKeyException e, HttpServletRequest request) {
        String msgs = e.getMessage();
        ErrorResult error = ErrorResult.fail(ResultCode.DB_DUPLICATEKEY, e, e.getMessage());
        log.warn("URL:{} ,参数校验异常:{}", request.getRequestURI(), msgs);
        return error;
    }

    /**
     * 捕获Aes加密时，如果获取到的加密字符串错误时，无法解密的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BadPaddingException.class)
    public ErrorResult badPaddingException(BadPaddingException e, HttpServletRequest request) {
        String msgs = e.getMessage();
        ErrorResult error = ErrorResult.fail(ResultCode.ENCRYPTION_ERROR, e, e.getMessage());
        log.warn("URL:{} ,参数校验异常:{}", request.getRequestURI(), msgs);
        return error;
    }

    /**
     * JWT解码异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = JWTDecodeException.class)
    public ErrorResult handleJWTDecodeException(JWTDecodeException e, HttpServletRequest request) {
        String msgs = e.getMessage();
        ErrorResult error = ErrorResult.fail(ResultCode.TOKEN_FORMAT_ERROR, e, e.getMessage());
        log.warn("URL:{} ,参数校验异常:{}", request.getRequestURI(), msgs);
        return error;
    }

}
