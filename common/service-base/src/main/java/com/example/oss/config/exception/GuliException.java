package com.example.oss.config.exception;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 叶刚诚
 * @create 2021-12-28-15:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends Exception{
    @ApiModelProperty(value = "异常状态码")
    private  Integer code = 500;
    @ApiModelProperty(value = "异常消息")
    private String message = "异常发生";
}
