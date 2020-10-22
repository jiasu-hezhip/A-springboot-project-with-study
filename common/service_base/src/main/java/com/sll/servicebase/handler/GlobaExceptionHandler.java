package com.sll.servicebase.handler;

import com.sll.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobaExceptionHandler {

    @ExceptionHandler(Exception.class)//这里写特定的异常就只针对特定异常进行处理
    @ResponseBody                     //也可以用自己自定义的异常类，继承runtimeexception
    public R error(Exception e){
        e.printStackTrace();
        log.error(e.getMessage());
        return R.error().message("全局异常处理");
    }

}
