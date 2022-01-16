package com.example.msmservice.controller;

import com.example.msmservice.service.MsmService;
import com.example.oss.config.exception.GuliException;
import com.example.utils.R;
import com.example.utils.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 叶刚诚
 * @create 2022-01-10-16:34
 */
@RestController
@RequestMapping("edumsm/msm")

public class MsmController {
    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @GetMapping("send/{phone}")
    public R sendmsm(@PathVariable String phone) throws GuliException {

        String s = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(s)) return R.success();
        redisTemplate.opsForValue().set(phone,"9999", 5, TimeUnit.MINUTES);
        return R.success();
    }
}
