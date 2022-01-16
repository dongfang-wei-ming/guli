package com.example.order.client;

import com.example.order.client.Impl.CourseClientImpl;
import com.example.order.client.Impl.UserClientImpl;
import com.example.utils.orderVo.CourseWebVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author 叶刚诚
 * @create 2022-01-13-16:09
 */
@Component
@FeignClient(name = "service-edu",fallback = CourseClientImpl.class)
public interface CourseClient {
    @PostMapping("/eduservice/front/course/getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable("id") String id);
}
