package com.example.order.service.impl;

import com.example.order.client.CourseClient;
import com.example.order.client.UserClient;
import com.example.order.entity.Order;
import com.example.order.mapper.OrderMapper;
import com.example.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.order.utils.OrderNoUtil;
import com.example.utils.orderVo.CourseWebVoOrder;
import com.example.utils.orderVo.MemberOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-01-13
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private CourseClient courseClient;
    @Autowired
    private UserClient userClient;


    @Override
    public String createOrder(String courseId, String id) {
        CourseWebVoOrder courseInfoOrder = courseClient.getCourseInfoOrder(courseId);
        MemberOrder userInfoOrder = userClient.getUserInfoOrder(id);
        Order order = new Order();

        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId); //课程id
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName(courseInfoOrder.getTeacherName());
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(id);
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());
        order.setStatus(0);  //订单状态（0：未支付 1：已支付）
        order.setPayType(1);  //支付类型 ，微信1
        baseMapper.insert(order);
        return order.getOrderNo();
    }
}
