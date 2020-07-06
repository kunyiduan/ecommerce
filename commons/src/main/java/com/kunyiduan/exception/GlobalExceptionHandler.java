package com.kunyiduan.exception;

import com.kunyiduan.bean.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.UnexpectedTypeException;

/**
 * 异常处理器
 * @version 1.0
 */
@RestControllerAdvice
@Slf4j



public class GlobalExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseDto handleDuplicateKeyException(DuplicateKeyException e) {
        log.error(e.getMessage(), e);
        return new ResponseDto().error("数据库中已存在该记录");
    }

    @ExceptionHandler(Exception.class)
    public ResponseDto handleException(Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseDto().error();
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseDto handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error(e.getMessage(), e);
        String msg = String.format("参数不对:%s", e.getMessage());
        return new ResponseDto().error(203, msg);
    }

    @ExceptionHandler(BindException.class)
    public ResponseDto bindException(BindException e) {
        log.error(e.getMessage(), e);
        String msg = String.format("参数不能为空:%s", e.getAllErrors().get(0).getDefaultMessage());
        return new ResponseDto().error(201, msg);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseDto handleMissingServletRequestParameterException(UnexpectedTypeException e) {
        log.error(e.getMessage(), e);
        String msg = String.format("参数不能为空:%s", e.getMessage());
        return new ResponseDto().error(203, msg);
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(GlobalException.class)
    public ResponseDto handleRRException(GlobalException e) {
        return new ResponseDto().error(e.getCode(), e.getMessage(), e.getData());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseDto handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseDto().error(400, "路径不存在，请检查路径是否正确");
    }

}
