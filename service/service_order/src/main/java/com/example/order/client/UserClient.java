package com.example.order.client;

import com.example.order.client.Impl.UserClientImpl;
import com.example.utils.orderVo.MemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author 叶刚诚
 * @create 2022-01-13-16:09
 */
@Component
@FeignClient(name = "service-ucenter",fallback = UserClientImpl.class)
public interface UserClient {
    @PostMapping("/ucenter/member/getUserInfoOrder/{id}")
    public MemberOrder getUserInfoOrder(@PathVariable("id") String id);
}
