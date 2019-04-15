package com.example.demo.handler;

import com.example.demo.bean.Result;
import com.example.demo.exception.ServiceException;
import com.example.demo.util.ResHelper;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @project: demo
 * @description: 统一异常处理类
 * @author: zhaoyujie
 * @create: 2019-04-15 08:37
 */
@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    public Result<Object> handleServiceException(ServiceException e){
        e.printStackTrace();
        return ResHelper.error(e.getMessage());
    }
}
