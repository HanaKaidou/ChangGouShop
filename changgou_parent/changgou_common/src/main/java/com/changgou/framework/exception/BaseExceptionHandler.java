package com.changgou.framework.exception;

import com.changgou.framework.entity.Result;
import com.changgou.framework.entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 常恃豪
 * @version 1.0
 * @date 2020/4/12 11:51
 */
@ControllerAdvice
public class BaseExceptionHandler {

    /**
     * 异常处理
     * @ControllerAdvice注解，全局捕获异常类，只要作用在@RequestMapping上，所有的异常都会被捕获。
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result error(Exception e) {
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
