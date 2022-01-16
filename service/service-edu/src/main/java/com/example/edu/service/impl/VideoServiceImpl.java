package com.example.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.edu.client.VodClient;
import com.example.edu.entity.Video;
import com.example.edu.mapper.VideoMapper;
import com.example.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-01-02
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Autowired
    private VodClient vodClient;

    @Override
    public void deleteByChapterId(String chapterId) {
        QueryWrapper<Video> wrapper0 =new QueryWrapper<>();
        wrapper0.eq("chapter_id",chapterId);
        wrapper0.select("video_source_id");
        List<Video> videos = baseMapper.selectList(wrapper0);
        for(Video v : videos) {
            if(v.getVideoSourceId() != null){
                vodClient.delete(v.getVideoSourceId());
            }
        }
        UpdateWrapper<Video> wrapper = new UpdateWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        baseMapper.delete(wrapper);
    }

    @Override
    public void deleteByCourseId(String id) {
        //删除视屏
        QueryWrapper<Video> wrapper0 =new QueryWrapper<>();
        wrapper0.eq("course_id",id);
        wrapper0.select("video_source_id");
        List<Video> videos = baseMapper.selectList(wrapper0);

        List<String> list = new ArrayList<>();
        for(Video v : videos) list.add(v.getVideoSourceId());
        if(list.size() != 0) vodClient.deleteBatch(list);

        //删除数据库文件
        UpdateWrapper<Video> wrapper = new UpdateWrapper<>();
        wrapper.eq("course_id",id);
        baseMapper.delete(wrapper);
    }
}
