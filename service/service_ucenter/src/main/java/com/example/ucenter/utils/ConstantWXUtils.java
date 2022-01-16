package com.example.ucenter.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 叶刚诚
 * @create 2022-01-11-18:52
 */
@Component
public class ConstantWXUtils implements InitializingBean {
    public static String APP_ID;
    public static String APP_SECRET;
    public static String REDIRECT_URL;

    @Value("${wx.open.appid}")
    private String id;
    @Value("${wx.open.appsecret}")
    private String appsecret;
    @Value("${wx.open.redirecturl}")
    private String url;
    @Override
    public void afterPropertiesSet() throws Exception {
        APP_ID = id;
        APP_SECRET =appsecret;
        REDIRECT_URL =url;
    }
}
