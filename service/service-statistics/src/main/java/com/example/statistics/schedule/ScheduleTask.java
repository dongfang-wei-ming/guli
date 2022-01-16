package com.example.statistics.schedule;

import com.example.statistics.service.StatisticsDailyService;
import com.example.statistics.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 叶刚诚
 * @create 2022-01-14-17:55
 */
@Component
public class ScheduleTask {
    @Autowired
    private StatisticsDailyService staService;

    @Scheduled(cron = "0 0 1 * * ?")
    public void task1(){
        String s = DateUtil.formatDate(DateUtil.addDays(new Date(), -1));
        staService.getRegisterCount(s);
    }
}
