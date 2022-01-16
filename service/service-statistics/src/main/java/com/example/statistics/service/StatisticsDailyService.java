package com.example.statistics.service;

import com.example.statistics.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-01-14
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    Integer getRegisterCount(String day);

    Map<String,Object> getShowData(String type, String begin, String end);
}
