package com.example.edu.client.Impl;

import com.example.edu.client.MemberClient;
import com.example.utils.R;
import org.springframework.stereotype.Component;

/**
 * @author 叶刚诚
 * @create 2022-01-13-14:04
 */
@Component
public class MemberClientImpl implements MemberClient {
    @Override
    public R getUserInfo(String id) {
        return R.fail().message("未知错误");
    }
}
