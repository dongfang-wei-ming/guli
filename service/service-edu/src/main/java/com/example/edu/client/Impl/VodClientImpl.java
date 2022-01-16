package com.example.edu.client.Impl;

import com.example.edu.client.VodClient;
import com.example.utils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 叶刚诚
 * @create 2022-01-08-17:17
 */
@Component
public class VodClientImpl implements VodClient {
    @Override
    public R delete(String id) {
        return R.fail().message("删除视屏错误");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.fail().message("删除多个视屏错误");
    }
}
