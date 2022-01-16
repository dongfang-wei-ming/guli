package com.example.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.order.entity.Order;
import com.example.order.service.OrderService;
import com.example.oss.config.exception.GuliException;
import com.example.utils.JwtUtils;
import com.example.utils.R;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-01-13
 */
@RestController
@RequestMapping("/eduorder/order")

public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("createOrder/{courseId}")
    public R createOrder(@PathVariable String courseId,
                         HttpServletRequest request){
        String id = JwtUtils.getMemberIdByJwtToken(request);
        String orderNo= orderService.createOrder(courseId,id);
        return R.success().data("orderId",orderNo);
    }
    @GetMapping("getOrderInfo/{orderNo}")
    public R getOrderInfo(@PathVariable String orderNo){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        return R.success().data("item",orderService.getOne(wrapper));
    }

    @SneakyThrows
    @GetMapping("isBuy/{courseId}/{memberId}")
    public boolean isBuy(@PathVariable String courseId,
                   @PathVariable String memberId){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.eq("member_id",memberId);
        wrapper.eq("status",1);
        Integer i= orderService.count(wrapper);
        if(i >= 1)return true;
        return false;
    }

}

