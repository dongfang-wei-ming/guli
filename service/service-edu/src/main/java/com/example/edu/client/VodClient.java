package com.example.edu.client;

import com.example.edu.client.Impl.VodClientImpl;
import com.example.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 叶刚诚
 * @create 2022-01-08-15:31
 */
@Component
@FeignClient(name = "service-vod" , fallback = VodClientImpl.class)
public interface VodClient {

    @DeleteMapping("/eduvod/video/{id}")
    public R delete(@PathVariable("id") String id);

    @DeleteMapping("/eduvod/video/delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
