package com.example.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.statistics.client.UcenterClient;
import com.example.statistics.entity.StatisticsDaily;
import com.example.statistics.mapper.StatisticsDailyMapper;
import com.example.statistics.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-01-14
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public Integer getRegisterCount(String day) {
        UpdateWrapper<StatisticsDaily> wrapper = new UpdateWrapper<>();
        wrapper.eq("date_calculated",day);
        baseMapper.delete(wrapper);

        StatisticsDaily daily = new StatisticsDaily();
        Integer count = ucenterClient.getRegisterCount(day);
        daily = new StatisticsDaily();
        daily.setDateCalculated(day);
        daily.setRegisterNum(count);
        daily.setCourseNum(1);
        daily.setLoginNum(1);
        daily.setVideoViewNum(1);
        baseMapper.insert(daily);
        return count;
    }

    @Override
    public Map<String,Object> getShowData(String type, String begin, String end) {
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.between("date_calculated",begin,end);
        wrapper.select(type,"date_calculated");
        List<StatisticsDaily> staList = baseMapper.selectList(wrapper);
        List<Integer> numList = new ArrayList<>();
        List<String> dateList = new ArrayList<>();
        for(StatisticsDaily sta : staList){
            dateList.add(sta.getDateCalculated());
            switch (type){
                case "register_num":
                    numList.add(sta.getRegisterNum());
                    break;
                case "login_num":
                    numList.add(sta.getLoginNum());
                    break;
                case "video_view_num":
                    numList.add(sta.getVideoViewNum());
                    break;
                case "course_num":
                    numList.add(sta.getCourseNum());
                    break;
            }
        }
        Map map = new HashMap();
        map.put("numDataList",numList);
        map.put("date_calculatedList",dateList);
        return map;
    }
}
