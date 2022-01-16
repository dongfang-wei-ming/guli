package com.example.edu.service;

import com.example.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.edu.entity.chapter.ChapterSelect;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-01-02
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterSelect> getChapterVideo(String courseId);

    boolean deleteChapter(String chapterId);

    void deleteByCourseId(String id);
}
