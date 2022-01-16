package com.example.msmservice.service;

import java.util.Map;

/**
 * @author 叶刚诚
 * @create 2022-01-10-16:35
 */
public interface MsmService {
    boolean send(Map<String, Object> map, String phone);
}
