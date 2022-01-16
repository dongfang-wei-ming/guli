package com.example.vod.controller;

import com.aliyun.oss.ClientException;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.example.oss.config.exception.GuliException;
import com.example.utils.R;
import com.example.vod.service.VodService;
import com.example.vod.utils.ConstantVodUtils;
import com.example.vod.utils.InitVodCilent;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.annotation.PreDestroy;
import java.util.List;

/**
 * @author 叶刚诚
 * @create 2022-01-07-16:23
 */
@RestController
@RequestMapping("/eduvod/video")

public class VodController {

    @Autowired
    private VodService vodService;

    @PostMapping("uploadVideo")
    public R uploadVideo(@RequestBody MultipartFile file){
        String videoId = vodService.uploadVideo(file);
        return R.success().data("videoId",videoId).data("name",file.getName());
    }


    @DeleteMapping("{id}")
    public R delete(@PathVariable String id){
        vodService.deleteVideo(id);
        return R.success();
    }
    @DeleteMapping("delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList){
        vodService.removeMoreVideo(videoIdList);
        return R.success();
    }
    @SneakyThrows
    @GetMapping("getPlayAuth/{id}")
    public R getPlayAuth(@PathVariable String id){
        try {
            DefaultAcsClient client
                    = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KEY_ID
                    , ConstantVodUtils.ACCESS_KEY_SECRET);
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(id);
            GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
            response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            return R.success().data("playAuth",playAuth);
        } catch (Exception e) {
            throw  new GuliException(20001,"获取视频失败");
        }
    }
}
