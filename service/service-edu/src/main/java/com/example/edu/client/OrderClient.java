package com.example.edu.client;

import com.example.edu.client.Impl.OrderClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 叶刚诚
 * @create 2022-01-14-15:24
 */
@Component
@FeignClient(name = "service-order" ,fallback = OrderClientImpl.class)
public interface OrderClient {
    @GetMapping("/eduorder/order/isBuy/{courseId}/{memberId}")
    public boolean isBuy(@PathVariable("courseId") String courseId,
                         @PathVariable("memberId") String memberId);
}
