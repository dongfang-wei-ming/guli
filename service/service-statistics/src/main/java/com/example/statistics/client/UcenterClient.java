package com.example.statistics.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 叶刚诚
 * @create 2022-01-14-16:57
 */
@Component
@FeignClient(name = "service-ucenter")
public interface UcenterClient {
    @GetMapping("/ucenter/member/getRegisterCount/{day}")
    public Integer getRegisterCount(@PathVariable("day") String day);
}
