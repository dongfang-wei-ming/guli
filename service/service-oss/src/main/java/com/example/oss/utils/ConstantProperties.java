package com.example.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 叶刚诚
 * @create 2022-01-01-13:21
 */
@Component
public class ConstantProperties implements InitializingBean {

    @Value("${aliyun.oss.file.endpoint}")
    public String endpoint;

    @Value("${aliyun.oss.file.keyid}")
    public String keyId;

    @Value("${aliyun.oss.file.keysecret}")
    public String keySecret;

    @Value("${aliyun.oss.file.bucketname}")
    public String bucketName;

    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endpoint;
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }
}
