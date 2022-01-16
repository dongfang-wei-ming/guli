package com.example.statistics.controller;


import com.example.statistics.client.UcenterClient;
import com.example.statistics.service.StatisticsDailyService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-01-14
 */
@RestController
@RequestMapping("/statistics/sta")

public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService dailyService;

    @GetMapping("getRegisterCount/{day}")
    public R getRegisterCount(@PathVariable String day){
        Integer count = dailyService.getRegisterCount(day);
        return R.success().data("count",count);
    }
    @GetMapping("searchForShow/{type}/{begin}/{end}")
    public R searchForShow(@PathVariable String type,
                           @PathVariable String begin,
                           @PathVariable String end){
        Map<String,Object> map = dailyService.getShowData(type,begin,end);
        return R.success().data(map);
    }

}

