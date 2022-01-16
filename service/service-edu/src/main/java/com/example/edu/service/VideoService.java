package com.example.edu.service;

import com.example.edu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-01-02
 */
public interface VideoService extends IService<Video> {

    void deleteByChapterId(String chapterId);

    void deleteByCourseId(String id);
}
