package com.example.oss.config.handler;


import com.example.oss.config.exception.GuliException;
import com.example.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 叶刚诚
 * @create 2021-12-27-22:43
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public com.example.utils.R error(Exception e){
        e.printStackTrace();
        return R.fail().message("全局处理");
    }
    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public com.example.utils.R diyError(GuliException e){
        e.printStackTrace();
        log.error(e.getMessage());
        return R.fail().code(e.getCode()).message(e.getMessage());
    }
}
