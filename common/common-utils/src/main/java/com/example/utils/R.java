package com.example.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Predicate;

/**
 * @author 叶刚诚
 * @create 2021-12-27-20:40
 */
@Data
public class R {
    private Boolean success;
    private Integer code;
    private String message;
    private Map<String,Object> data=new HashMap<>();
    private R(){};
    public static R success(){
        R r=new R();
        r.setCode(resultCode.SUCCESS);
        r.setSuccess(true);
        r.setMessage("成功");
        return r;
    }
    public static R fail(){
        R r=new R();
        r.setCode(resultCode.FAIL);
        r.setSuccess(false);
        r.setMessage("失败");
        return r;
    }
    public R data(String key,Object value){
        this.data.put(key,value);
        return this;
    }
    public R data(Map<String,Object> map){
        this.data=map;
        return this;
    }
    public R code(Integer code){
        this.code=code;
        return this;
    }
    public R message(String msg){
        this.message=msg;
        return this;
    }

}
