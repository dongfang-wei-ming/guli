package com.example.edu.client;

import com.example.edu.client.Impl.MemberClientImpl;
import com.example.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 叶刚诚
 * @create 2022-01-13-14:04
 */
@Component
@FeignClient(name = "service-ucenter" , fallback = MemberClientImpl.class)
public interface MemberClient {

    @GetMapping("/ucenter/member/getUserInfo/{id}")
    public R getUserInfo(@PathVariable("id") String id);

}
