package com.example.order.service;

import com.example.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-01-13
 */
public interface OrderService extends IService<Order> {

    String createOrder(String courseId, String id);
}
