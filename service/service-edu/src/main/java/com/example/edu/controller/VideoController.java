package com.example.edu.controller;


import com.example.edu.client.VodClient;
import com.example.edu.entity.Video;
import com.example.edu.service.VideoService;
import com.example.oss.config.exception.GuliException;
import com.example.utils.R;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-01-02
 */
@RestController
@RequestMapping("/eduservice/video")

public class VideoController {
    @Autowired
    private VodClient vodClient;
    @Autowired
    private VideoService videoService;

    @GetMapping("getVideo/{videoId}")
    public R getVideo(@PathVariable String videoId){
        Video byId = videoService.getById(videoId);
        return R.success().data("video",byId);
    }
    @PostMapping("addVideo")
    public R addVideo(@RequestBody Video video){
        if(videoService.save(video))return R.success();
        return R.fail();
    }

    @SneakyThrows
    @DeleteMapping("{videoId}")
    public R deleteVideo(@PathVariable String videoId){
        Video video = videoService.getById(videoId);
        //删除视屏
        if(video.getVideoSourceId() != null) {
            R r = vodClient.delete(video.getVideoSourceId());
            if( !r.getSuccess() ) throw new GuliException(20001,"删除视屏失败,熔断");
        }
        //删除小节
        if(videoService.removeById(videoId))return R.success();
        return R.fail();
    }
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody Video video){
        if(videoService.updateById(video))return R.success();
        return R.fail();
    }

}

