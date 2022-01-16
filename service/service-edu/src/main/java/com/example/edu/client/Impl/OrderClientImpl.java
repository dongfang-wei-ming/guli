package com.example.edu.client.Impl;

import com.example.edu.client.OrderClient;
import org.springframework.stereotype.Component;

/**
 * @author 叶刚诚
 * @create 2022-01-14-15:25
 */
@Component
public class OrderClientImpl implements OrderClient {
    @Override
    public boolean isBuy(String courseId, String memberId) {
        return false;
    }
}
